package com.navigator.app;

import com.navigator.lib.entities.City;
import com.navigator.lib.entities.Problem;
import com.navigator.lib.entities.Route;
import com.navigator.lib.entities.Solution;
import com.navigator.lib.io.Source;
import com.navigator.lib.pathfinder.Graph;
import com.navigator.lib.pathfinder.ShortestPathFinder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Navigator {
    private Source source;
    private Graph cityMap = new Graph();
    private HashMap<Integer, City> listOfCities = new HashMap();

    public Navigator(Source src) {
        this.source = src;
    }

    public void start() {
        initCities();
        initRoutes();
        addCitiesToGraph();
        solve();
    }

    private void initCities() {
        List<City> temp = source.getLocations();
        for (City city : temp) {
            listOfCities.put(city.getId(), city);
        }
    }

    private void initRoutes() {
        List<Route> routes = source.getRoutes();
        for (Route route : routes) {
            City from = listOfCities.get(route.getFormId());
            City to = listOfCities.get(route.getToId());
            from.addDestination(to, route.getCost());
        }
    }

    private void solve() {
        List<Problem> problems = source.getProblems();
        List<Solution> solutions = new ArrayList();
        for (Problem problem : problems) {
            ShortestPathFinder.calculateShortestPathFromSource(cityMap, listOfCities.get(problem.getFormId()));
            solutions.add(new Solution(problem.getId(), listOfCities.get(problem.getToId()).getDistance()));
        }
        source.addSolutions(solutions);
    }

    private void addCitiesToGraph() {
        for (int i = 0; i < listOfCities.size(); i++) {
            cityMap.addNode(listOfCities.get(i));
        }
    }
}
