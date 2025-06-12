// src/main/java/com/ums/GradingSystem.java
package com.ums;

/**
 * Target Interface: This is the interface that the UMS client code expects
 * to interact with for grading operations.
 */
public interface GradingSystem {
    /**
     * Retrieves the grade for a specific student in a specific course.
     * @param studentId The ID of the student.
     * @param courseCode The code of the course.
     * @return The grade as a String (e.g., "A", "B+", "75%").
     */
    String getStudentGrade(String studentId, String courseCode);

    /**
     * Updates the grade for a specific student in a specific course.
     * @param studentId The ID of the student.
     * @param courseCode The code of the course.
     * @param newGrade The new grade to set.
     * @return true if the grade was successfully updated, false otherwise.
     */
    boolean updateStudentGrade(String studentId, String courseCode, String newGrade);
}