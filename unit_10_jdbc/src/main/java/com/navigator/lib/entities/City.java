package com.navigator.lib.entities;

import java.util.*;

public class City {
    private int id;
    private String cityName;
    private List<City> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    Map<City, Integer> adjacentCities = new HashMap<>();

    public void addDestination(City destination, int distance) {
        adjacentCities.put(destination, distance);
    }

    public City(int id, String name) {
        this.id = id;
        this.cityName = name;
    }

    public City(String name) {
        this.cityName = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<City> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<City> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<City, Integer> getAdjacentCities() {
        return adjacentCities;
    }

    public void setAdjacentCities(Map<City, Integer> adjacentCities) {
        this.adjacentCities = adjacentCities;
    }

    public int getId() {
        return id;
    }
}

