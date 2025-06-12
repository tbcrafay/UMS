// src/main/java/com/ums/Notification.java
package com.ums;

/**
 * Abstraction: Defines the abstraction's interface and maintains a reference to an object
 * of type NotificationSender (the Implementor).
 */
public abstract class Notification {
    protected NotificationSender sender; // The bridge to the implementation

    // Constructor to set the implementation (sender)
    public Notification(NotificationSender sender) {
        this.sender = sender;
    }

    // Method to change the implementation at runtime if needed
    public void setSender(NotificationSender sender) {
        this.sender = sender;
    }

    /**
     * Abstract method to send the notification.
     * The actual sending mechanism is delegated to the 'sender' object.
     * @param recipient The recipient of the notification.
     * @param message The message content.
     */
    public abstract void send(String recipient, String message);
}