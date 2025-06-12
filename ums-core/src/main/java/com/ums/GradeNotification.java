// src/main/java/com/ums/GradeNotification.java
package com.ums;

/**
 * Refined Abstraction: Extends the Notification abstraction for a specific type of notification (Grade).
 * It uses the inherited sender to perform the actual delivery.
 */
public class GradeNotification extends Notification {

    public GradeNotification(NotificationSender sender) {
        super(sender);
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("\n--- Grade Notification ---");
        // Specific logic for preparing grade notification message if needed
        String fullMessage = "Grade Update: " + message;
        sender.sendMessage(recipient, fullMessage); // Delegating to the sender implementation
    }

    public void notifyGrade(String studentId, String courseCode, String grade) {
        String message = String.format("Your grade for %s is %s.", courseCode, grade);
        send(studentId, message); // The `send` method will use the set sender
    }
}