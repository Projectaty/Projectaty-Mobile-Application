package com.projectaty.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Project {
    private int projectID;
    private String title;
    private String description;
    private LocalDate deadline;
    private String privacySetting;
    private int creatorID;

    public Project(){

    }

    public Project(int projectID, String title, String description, LocalDate deadline, String privacySetting, int creatorID) {
        this.projectID = projectID;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.privacySetting = privacySetting;
        this.creatorID = creatorID;
    }

    /*
        Getters & Setters
         */
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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getPrivacySetting() {
        return privacySetting;
    }

    public void setPrivacySetting(String privacySetting) {
        this.privacySetting = privacySetting;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }
}