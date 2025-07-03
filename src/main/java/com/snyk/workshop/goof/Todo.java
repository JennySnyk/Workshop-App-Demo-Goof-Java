package com.snyk.workshop.goof;

public class Todo {
    private static long counter = 0;
    private long id;
    private String text;
    private boolean completed;

    public Todo(String text) {
        this.id = ++counter;
        this.text = text;
        this.completed = false;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
