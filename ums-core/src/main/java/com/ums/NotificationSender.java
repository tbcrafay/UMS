// src/main/java/com/ums/NotificationSender.java
package com.ums;

/**
 * Implementor Interface: Defines the interface for implementation classes.
 * This part will vary independently from the abstraction.
 * It's responsible for sending the actual message.
 */
public interface NotificationSender {
    /**
     * Sends a message to a recipient.
     * @param recipient The address/identifier of the recipient (e.g., email address, phone number).
     * @param message The content of the message.
     */
    void sendMessage(String recipient, String message);
}