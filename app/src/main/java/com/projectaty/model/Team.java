package com.projectaty.model;

public class Team {
    private int teamID;
    private int projectID;
    private int memberID;

    public Team(){

    }

    public Team(int teamID, int projectID, int memberID) {
        this.teamID = teamID;
        this.projectID = projectID;
        this.memberID = memberID;
    }

    /*
    Getters & setters
     */

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }
}
