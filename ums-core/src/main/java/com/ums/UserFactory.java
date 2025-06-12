// src/main/java/com/ums/UserFactory.java
package com.ums;

/**
 * The UserFactory class is responsible for creating different types of User objects
 * based on the provided user type. This implements the Factory Pattern.
 */
public class UserFactory {

    /**
     * Creates and returns a User object based on the specified user type.
     *
     * @param userType The type of user to create (e.g., "Student", "Professor", "Admin").
     * @param id The ID for the user.
     * @param name The name of the user.
     * @param additionalInfo Optional additional information like department for Professor.
     * Pass null if not applicable for the user type.
     * @return An instance of a concrete User class (Student, Professor, or Admin).
     * @throws IllegalArgumentException If an unknown user type is provided.
     */
    public static User createUser(String userType, String id, String name, String additionalInfo) {
        if (userType == null || userType.trim().isEmpty()) {
            throw new IllegalArgumentException("User type cannot be null or empty.");
        }

        switch (userType.toLowerCase()) {
            case "student":
                // For Student, additionalInfo is not used or can be adapted for major/program
                return new Student(id, name);
            case "professor":
                // For Professor, additionalInfo is expected to be the department
                return new Professor(id, name, additionalInfo);
            case "admin":
                // For Admin, additionalInfo is not used
                return new Admin(id, name);
            default:
                throw new IllegalArgumentException("Unknown user type: " + userType);
        }
    }

    /**
     * Overloaded method for creating users that don't require additional info (like Student, Admin).
     * @param userType The type of user to create.
     * @param id The ID for the user.
     * @param name The name of the user.
     * @return An instance of a concrete User class.
     * @throws IllegalArgumentException If an unknown user type is provided or if additionalInfo is expected but not provided.
     */
    public static User createUser(String userType, String id, String name) {
        // Call the more general method with null for additionalInfo
        return createUser(userType, id, name, null);
    }
}