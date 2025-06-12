// src/main/java/com/ums/User.java
package com.ums;

/**
 * The User interface defines the common behavior for all types of users
 * in the University Management System.
 */
public interface User {
    void displayRole(); // Method to display the role of the user
    String getUserType(); // Method to get the type of the user
    // We can add more common methods here later, like getUserId(), getName(), etc.
} 