// src/main/java/com/ums/EmailSender.java
package com.ums;

/**
 * Concrete Implementor: Implements the NotificationSender interface for email.
 */
public class EmailSender implements NotificationSender {
    @Override
    public void sendMessage(String recipient, String message) {
        System.out.println("Sending Email to: " + recipient);
        System.out.println("Subject: UMS Notification");
        System.out.println("Body: " + message);
        System.out.println("--- Email Sent ---");
    }
}