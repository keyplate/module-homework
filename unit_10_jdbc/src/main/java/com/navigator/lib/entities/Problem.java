package com.navigator.lib.entities;

public class Problem {
    private int id;
    private int formId;
    private int toId;

    public Problem(int id, int formId, int toId) {
        this.id = id;
        this.formId = formId;
        this.toId = toId;
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
}
