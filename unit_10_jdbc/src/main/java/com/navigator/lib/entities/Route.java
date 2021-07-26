package com.navigator.lib.entities;

public class Route {
    private int id;
    private int formId;
    private int toId;
    private int cost;

    public Route(int id, int formId, int toId, int cost) {
        this.id = id;
        this.formId = formId;
        this.toId = toId;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
