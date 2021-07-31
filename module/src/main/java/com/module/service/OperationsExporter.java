package com.module.service;

import com.module.entities.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;

public class OperationsExporter {
    private final Connection connection;
    private final static Logger OperationExporterLogger = LoggerFactory.getLogger(OperationsExporter.class);

    public OperationsExporter(Connection connection) {
        this.connection = connection;
    }

    public void export(Account account) {
        OperationExporterLogger.debug("Export to csv file initiated by user with account id " + account.getId());
        StringBuilder sb = new StringBuilder();
        sb.append("operation_id,date,category,value\n");
        try (PreparedStatement statement =
                     connection.prepareStatement("select i.id, operation_time, operation_sum from income_category as i " +
                             "                             inner join operation on (i.operation_id = operation.id) " +
                             "                             where account_id = ? order by operation_time limit 10")) {
            statement.setLong(1, account.getId());
            ResultSet rs = statement.executeQuery();
            getValuesFromResultSet(sb, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try (PreparedStatement statement =
                     connection.prepareStatement("select i.id, operation_time, operation_sum from expenses_category as i " +
                             "                             inner join operation on (i.operation_id = operation.id) " +
                             "                             where account_id = ? order by operation_time limit 10")) {
            statement.setLong(1, account.getId());
            ResultSet rs = statement.executeQuery();
            getValuesFromResultSet(sb, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        writeToFile(sb.toString());
        OperationExporterLogger.debug("Export completed by user with account id = " + account.getId());
    }

    private void getValuesFromResultSet(StringBuilder sb, ResultSet rs) throws SQLException {
        while (rs.next()) {
            long id = rs.getLong("id");
            LocalDateTime time = LocalDateTime.from(rs.getTimestamp("operation_time").toLocalDateTime());
            Long value = rs.getLong("operation_sum");
            String category = "";
            if (value > 0) category = "income";
            else category = "expense";

            sb.append("" + id + "," + time.getYear() + "-" + time.getMonthValue()
                    + "-" + time.getDayOfMonth() + "," + category + "," + value + "\n");

        }
    }

    private void writeToFile(String str) {
        try {
            FileWriter fw = new FileWriter("output.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
