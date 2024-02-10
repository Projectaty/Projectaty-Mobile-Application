package com.projectaty.model;

import java.time.LocalDate;

public class Task {
    private int taskID;
    private int projectID;
    private String title;
    private String description;
    private String status;
    private int assignedTo;
    private LocalDate date;

    public Task(){

    }
    public Task( int projectID, String title, String description, String status, int assignedTo,
                LocalDate date) {
        this.projectID = projectID;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.date = date;
    }

    public Task(int taskID, int projectID, String title, String description, String status, int assignedTo,
    LocalDate date) {
        this.taskID = taskID;
        this.projectID = projectID;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.date = date;
    }

    /*
    Getters & setters
     */
    public int getTaskID() {
        return taskID;
    }
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
    public int getProjectID() {
        return projectID;
    }
    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }
    public String getStatus() {
        return status;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}