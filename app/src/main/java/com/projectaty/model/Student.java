package com.projectaty.model;

public class Student {
    private int studentID;
    private String username;
    private String password;
    private String email;

    public Student(){

    }

    public Student(int studentID, String username, String password, String email) {
        this.studentID = studentID;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    /*
    Getters & Setters
     */

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}