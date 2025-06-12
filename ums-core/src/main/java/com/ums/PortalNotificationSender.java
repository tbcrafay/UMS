// src/main/java/com/ums/PortalNotificationSender.java
package com.ums;

/**
 * Concrete Implementor: Implements the NotificationSender interface for internal portal notifications.
 */
public class PortalNotificationSender implements NotificationSender {
    @Override
    public void sendMessage(String recipient, String message) {
        System.out.println("Posting to UMS Portal for User ID: " + recipient);
        System.out.println("Portal Message: " + message);
        System.out.println("--- Portal Notification Posted ---");
    }
}