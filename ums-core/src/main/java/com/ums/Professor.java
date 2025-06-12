// src/main/java/com/ums/Professor.java
package com.ums;

/**
 * Concrete implementation of the User interface for a Professor.
 */
public class Professor implements User {
    private String professorId;
    private String name;
    private String department;

    public Professor(String professorId, String name, String department) {
        this.professorId = professorId;
        this.name = name;
        this.department = department;
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Professor (ID: " + professorId + ", Name: " + name + ", Dept: " + department + ")");
    }

    @Override
    public String getUserType() {
        return "Professor";
    }

    // Getters
    public String getProfessorId() {
        return professorId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}