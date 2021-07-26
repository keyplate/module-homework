package com.navigator.lib.io;

import com.navigator.lib.entities.City;
import com.navigator.lib.entities.Problem;
import com.navigator.lib.entities.Route;
import com.navigator.lib.entities.Solution;

import java.util.List;

public interface Source {

    List<City> getLocations();

    List<Problem> getProblems();

    List<Route> getRoutes();

    void addSolutions(List<Solution> solutions);
}
