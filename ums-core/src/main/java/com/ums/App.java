// package com.ums;

// /**
//  * Hello world!
//  *
//  */
// public class App 
// {
//     public static void main( String[] args )
//     {
//         System.out.println( "Hello World!" );
//     }
// }

// src/main/java/com/ums/App.java
// src/main/java/com/ums/App.java
// src/main/java/com/ums/App.java
package com.ums;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays; // For easily creating lists of prerequisites

public class App {
    public static void main(String[] args) {
        System.out.println("--- Starting University Management System Demo ---");

        // --- Part 1: Demonstrate Singleton Pattern (DatabaseConnectionManager) ---
        System.out.println("\n=== Demonstrating Singleton Pattern ===");
        System.out.println("Attempting to get first instance of DatabaseConnectionManager...");
        DatabaseConnectionManager instance1 = DatabaseConnectionManager.getInstance();
        System.out.println("Instance 1 Hash Code: " + instance1.hashCode());

        System.out.println("Attempting to get second instance of DatabaseConnectionManager...");
        DatabaseConnectionManager instance2 = DatabaseConnectionManager.getInstance();
        System.out.println("Instance 2 Hash Code: " + instance2.hashCode());

        if (instance1 == instance2) {
            System.out.println("SUCCESS: Both instances are the same! Singleton pattern is working correctly.");
        } else {
            System.out.println("FAILURE: Instances are different. Singleton pattern might not be working.");
        }

        System.out.println("\nAttempting to connect to the database using Singleton instance...");
        try {
            Connection conn = instance1.getConnection();
            if (conn != null) {
                System.out.println("Database connection obtained successfully: " + conn.getMetaData().getURL());
            } else {
                System.err.println("Failed to obtain database connection.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception during database connection: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (instance1 != null) {
                instance1.closeConnection();
            }
        }

        // --- Part 2: Demonstrate Factory Pattern (User Creation) ---
        System.out.println("\n\n=== Demonstrating Factory Pattern ===");

        System.out.println("\nCreating a Student user:");
        User studentUser = UserFactory.createUser("Student", "S001", "Alice Smith");
        studentUser.displayRole();
        System.out.println("User Type: " + studentUser.getUserType());

        System.out.println("\nCreating a Professor user:");
        User professorUser = UserFactory.createUser("Professor", "P005", "Dr. John Doe", "Computer Science");
        professorUser.displayRole();
        System.out.println("User Type: " + professorUser.getUserType());

        System.out.println("\nCreating an Admin user:");
        User adminUser = UserFactory.createUser("Admin", "A001", "Super Admin");
        adminUser.displayRole();
        System.out.println("User Type: " + adminUser.getUserType());

        System.out.println("\nAttempting to create an unknown user type:");
        try {
            User unknownUser = UserFactory.createUser("Guest", "G001", "Guest User");
            unknownUser.displayRole(); // This line won't be reached
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        // --- Part 3: Demonstrate Builder Pattern (Course Creation) ---
        System.out.println("\n\n=== Demonstrating Builder Pattern ===");

        // Create a simple mandatory course with only required fields
        System.out.println("\nBuilding a simple mandatory course:");
        Course fundamentalsCourse = new Course.CourseBuilder("CS101", "Programming Fundamentals", 3, "Computer Science")
                                        .build();
        System.out.println(fundamentalsCourse);

        // Create a complex elective course with all optional fields
        System.out.println("\nBuilding a complex elective course with prerequisites and description:");
        Course advancedDatabaseCourse = new Course.CourseBuilder("CS401", "Advanced Database Systems", 4, "Computer Science")
                                            .withPrerequisites(Arrays.asList("CS101", "CS203"))
                                            .asElective(true)
                                            .withDescription("Deep dive into NoSQL, distributed databases, and advanced SQL.")
                                            .build();
        System.out.println(advancedDatabaseCourse);

        // Create another mandatory course with only some optional fields
        System.out.println("\nBuilding another mandatory course with only a description:");
        Course calculusCourse = new Course.CourseBuilder("MA101", "Calculus I", 3, "Mathematics")
                                    .withDescription("Introduction to differential and integral calculus.")
                                    .build();
        System.out.println(calculusCourse);


        System.out.println("\n--- Demo Finished ---");
    } 
}