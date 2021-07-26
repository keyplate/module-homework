package com.navigator.lib.io;

import com.navigator.lib.entities.City;
import com.navigator.lib.entities.Problem;
import com.navigator.lib.entities.Route;
import com.navigator.lib.entities.Solution;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBSource implements Source {
    private Connection connection;


    public DBSource(Connection con) {
        this.connection = con;
    }

    @Override
    public List<City> getLocations() {
        List<City> cities = new ArrayList();
        try (PreparedStatement statement = connection.prepareStatement("select * from locations")) {
            ResultSet cityTable = statement.executeQuery();
            while (cityTable.next()) {
                int id = cityTable.getInt("id");
                String cityName = cityTable.getString("name");
                cities.add(new City(id, cityName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    @Override
    public List<Problem> getProblems() {
        List<Problem> problems = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from problems where " +
                "id not in (select id from solutions)")) {
            ResultSet problemTable = statement.executeQuery();
            while (problemTable.next()) {
                int id = problemTable.getInt("id");
                int fromId = problemTable.getInt("from_id");
                int toId = problemTable.getInt("to_id");
                problems.add(new Problem(id, fromId, toId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return problems;
    }

    @Override
    public List<Route> getRoutes() {
        List<Route> routes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from routes")) {
            ResultSet routesTable = statement.executeQuery();
            while (routesTable.next()) {
                int id = routesTable.getInt("id");
                int fromId = routesTable.getInt("from_id");
                int toId = routesTable.getInt("to_id");
                int cost = routesTable.getInt("cost");
                routes.add(new Route(id, fromId, toId, cost));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return routes;
    }

    @Override
    public void addSolutions(List<Solution> solutions) {
        try (PreparedStatement statement = connection.prepareStatement("insert into solutions values (?, ?)")) {
            for (Solution solution : solutions) {
                statement.setInt(1, solution.getId());
                statement.setInt(2, solution.getCost());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
