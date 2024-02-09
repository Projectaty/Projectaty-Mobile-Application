package com.projectaty.model;

public class Team {
    private int teamID;
    private String description;
    private String teamName;
    private boolean isPrivate;

    public Team() {

    }

    public Team(int teamID, String teamName, String description, boolean isPrivate) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.description = description;
        this.isPrivate = isPrivate;
    }

    /*
    Getters & setters
     */

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
