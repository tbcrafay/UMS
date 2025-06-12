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
            unknownUser.displayRole();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        // --- Part 3: Demonstrate Builder Pattern (Course Creation) ---
        System.out.println("\n\n=== Demonstrating Builder Pattern ===");

        System.out.println("\nBuilding a simple mandatory course:");
        Course fundamentalsCourse = new Course.CourseBuilder("CS101", "Programming Fundamentals", 3, "Computer Science")
                                        .build();
        System.out.println(fundamentalsCourse);

        System.out.println("\nBuilding a complex elective course with prerequisites and description:");
        Course advancedDatabaseCourse = new Course.CourseBuilder("CS401", "Advanced Database Systems", 4, "Computer Science")
                                            .withPrerequisites(Arrays.asList("CS101", "CS203"))
                                            .asElective(true)
                                            .withDescription("Deep dive into NoSQL, distributed databases, and advanced SQL.")
                                            .build();
        System.out.println(advancedDatabaseCourse);

        System.out.println("\nBuilding another mandatory course with only a description:");
        Course calculusCourse = new Course.CourseBuilder("MA101", "Calculus I", 3, "Mathematics")
                                    .withDescription("Introduction to differential and integral calculus.")
                                    .build();
        System.out.println(calculusCourse);

        // --- Part 4: Demonstrate Adapter Pattern (External Grading System Integration) ---
        System.out.println("\n\n=== Demonstrating Adapter Pattern ===");

        ExternalGradingSystemAPI externalAPI = new ExternalGradingSystemAPI();
        GradingSystem umsGradingSystem = new GradingSystemAdapter(externalAPI);

        System.out.println("\nUMS requesting grade for S001 in CS101 (via Adapter):");
        String gradeAliceCS101 = umsGradingSystem.getStudentGrade("S001", "CS101");
        System.out.println("Result: Alice's Grade in CS101: " + gradeAliceCS101);

        System.out.println("\nUMS trying to update grade for S001 in CS101 to 'A+' (via Adapter):");
        boolean updateSuccess = umsGradingSystem.updateStudentGrade("S001", "CS101", "A");
        System.out.println("Update successful: " + updateSuccess);

        System.out.println("\nUMS re-requesting grade for S001 in CS101 to confirm update:");
        gradeAliceCS101 = umsGradingSystem.getStudentGrade("S001", "CS101");
        System.out.println("Result: Alice's NEW Grade in CS101: " + gradeAliceCS101);

        System.out.println("\nUMS requesting grade for non-existent student/course (via Adapter):");
        String gradeNonExistent = umsGradingSystem.getStudentGrade("S999", "CS101");
        System.out.println("Result: Non-existent Grade: " + gradeNonExistent);

        // --- Part 5: Demonstrate Composite Pattern (Department Hierarchy) ---
        System.out.println("\n\n=== Demonstrating Composite Pattern ===");

        System.out.println("\nCreating CourseComponents...");
        Course programming101 = new Course.CourseBuilder("PROG101", "Intro to Programming", 3, "Computer Science")
                                    .withDescription("Foundational programming concepts.")
                                    .build();
        CourseComponent comp101 = new CourseComponent(programming101);

        Course dataStructures = new Course.CourseBuilder("CS203", "Data Structures & Algorithms", 4, "Computer Science")
                                    .withPrerequisites(Arrays.asList("PROG101"))
                                    .build();
        CourseComponent comp203 = new CourseComponent(dataStructures);

        Course operatingSystems = new Course.CourseBuilder("CS305", "Operating Systems", 3, "Computer Science")
                                        .withPrerequisites(Arrays.asList("CS203"))
                                        .asElective(false)
                                        .build();
        CourseComponent comp305 = new CourseComponent(operatingSystems);

        Course discreteMath = new Course.CourseBuilder("MATH201", "Discrete Mathematics", 3, "Mathematics")
                                    .build();
        CourseComponent math201 = new CourseComponent(discreteMath);

        System.out.println("Creating Departments (Composites)...");
        Department csDepartment = new Department("Computer Science Department");
        Department mathDepartment = new Department("Mathematics Department");
        Department engineeringFaculty = new Department("Faculty of Engineering");

        System.out.println("Building the academic hierarchy...");
        csDepartment.addComponent(comp101);
        csDepartment.addComponent(comp203);
        csDepartment.addComponent(comp305);

        mathDepartment.addComponent(math201);

        engineeringFaculty.addComponent(csDepartment);
        engineeringFaculty.addComponent(mathDepartment);

        System.out.println("\nDisplaying details of individual CourseComponent:");
        comp101.displayDetails("  ");
        System.out.println("  Total Credits for " + comp101.getCourse().getTitle() + ": " + comp101.getTotalCredits());

        System.out.println("\nDisplaying details of Computer Science Department (Composite):");
        csDepartment.displayDetails("");
        System.out.println("Total Credits for " + csDepartment.getName() + ": " + csDepartment.getTotalCredits());

        System.out.println("\nDisplaying details of Faculty of Engineering (Composite with nested Composites):");
        engineeringFaculty.displayDetails("");
        System.out.println("Total Credits for " + engineeringFaculty.getName() + ": " + engineeringFaculty.getTotalCredits());

        // --- Part 6: Demonstrate Bridge Pattern (Flexible Notifications) ---
        System.out.println("\n\n=== Demonstrating Bridge Pattern ===");

        // Create different Implementors (senders)
        NotificationSender emailSender = new EmailSender();
        NotificationSender smsSender = new SmsSender();
        NotificationSender portalSender = new PortalNotificationSender();

        // Create different Abstractions (notification types), initialized with a sender
        System.out.println("\nSending a Grade Notification via Email:");
        GradeNotification gradeEmailNotification = new GradeNotification(emailSender);
        gradeEmailNotification.notifyGrade("alice@example.com", "CS101", "A-");

        System.out.println("\nSending an Event Notification via SMS:");
        EventNotification eventSmsNotification = new EventNotification(smsSender);
        eventSmsNotification.notifyEvent("+1234567890", "Orientation Day", "August 20, 2025");

        System.out.println("\nSending a Grade Notification via Portal:");
        // Reusing the GradeNotification, but changing its sender to portalSender
        gradeEmailNotification.setSender(portalSender); // Dynamically change implementation
        gradeEmailNotification.notifyGrade("S001", "CS401", "B+"); // Note: recipient here is user ID for portal

        System.out.println("\nSending an Event Notification via Email (demonstrating flexibility):");
        // Reusing the EventNotification, but changing its sender to emailSender
        eventSmsNotification.setSender(emailSender); // Dynamically change implementation
        eventSmsNotification.notifyEvent("faculty@example.com", "Faculty Meeting", "June 25, 2025");


        System.out.println("\n--- Demo Finished ---");
    }
}