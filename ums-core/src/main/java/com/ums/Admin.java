// src/main/java/com/ums/Admin.java
package com.ums;

/**
 * Concrete implementation of the User interface for an Administrator.
 */
public class Admin implements User {
    private String adminId;
    private String name;

    public Admin(String adminId, String name) {
        this.adminId = adminId;
        this.name = name;
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Administrator (ID: " + adminId + ", Name: " + name + ")");
    }

    @Override
    public String getUserType() {
        return "Admin";
    }

    // Getters
    public String getAdminId() {
        return adminId;
    }

    public String getName() {
        return name;
    }
}