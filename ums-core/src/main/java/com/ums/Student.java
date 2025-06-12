// src/main/java/com/ums/Student.java
package com.ums;

/**
 * Concrete implementation of the User interface for a Student.
 */
public class Student implements User {
    private String studentId;
    private String name;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Student (ID: " + studentId + ", Name: " + name + ")");
    }

    @Override
    public String getUserType() {
        return "Student";
    }

    // Getters (you can add setters if needed)
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }
}