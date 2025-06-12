// src/main/java/com/ums/SmsSender.java
package com.ums;

/**
 * Concrete Implementor: Implements the NotificationSender interface for SMS.
 */
public class SmsSender implements NotificationSender {
    @Override
    public void sendMessage(String recipient, String message) {
        System.out.println("Sending SMS to: " + recipient);
        System.out.println("Message: " + message);
        System.out.println("--- SMS Sent ---");
    }
}