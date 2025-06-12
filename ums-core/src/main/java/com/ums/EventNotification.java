// src/main/java/com/ums/EventNotification.java
package com.ums;

/**
 * Refined Abstraction: Extends the Notification abstraction for a specific type of notification (Event).
 * It uses the inherited sender to perform the actual delivery.
 */
public class EventNotification extends Notification {

    public EventNotification(NotificationSender sender) {
        super(sender);
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("\n--- Event Notification ---");
        // Specific logic for preparing event notification message if needed
        String fullMessage = "Upcoming Event: " + message;
        sender.sendMessage(recipient, fullMessage); // Delegating to the sender implementation
    }

    public void notifyEvent(String recipientId, String eventName, String eventDate) {
        String message = String.format("'%s' is scheduled for %s. Don't miss it!", eventName, eventDate);
        send(recipientId, message); // The `send` method will use the set sender
    }
}