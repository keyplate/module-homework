package com.navigator.app;

import com.navigator.lib.io.DBSource;
import com.navigator.lib.io.Source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        try(Connection con = DriverManager.getConnection("" +
                "jdbc:postgresql://localhost:5432/city_map", "admin", "admin")) {
            Class.forName("org.postgresql.Driver");
          Source dBSource = new DBSource(con);
          Navigator navigator = new Navigator(dBSource);
          navigator.start();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver class wasn't found", e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
