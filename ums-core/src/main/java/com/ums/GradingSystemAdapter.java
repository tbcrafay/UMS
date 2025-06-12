// src/main/java/com/ums/GradingSystemAdapter.java
package com.ums;

/**
 * Adapter: This class adapts the ExternalGradingSystemAPI (Adaptee)
 * to the GradingSystem interface (Target).
 * It converts calls from the UMS's expected interface to the external system's interface.
 */
public class GradingSystemAdapter implements GradingSystem {

    private ExternalGradingSystemAPI externalAPI;

    public GradingSystemAdapter(ExternalGradingSystemAPI externalAPI) {
        this.externalAPI = externalAPI;
    }

    /**
     * Adapts the call to get student grade from UMS format to external API format.
     * Converts numerical score (from external) to letter grade (for UMS).
     */
    @Override
    public String getStudentGrade(String studentId, String courseCode) {
        // Map UMS IDs to external system IDs
        String externalStudentId = externalAPI.getExternalStudentId(studentId);
        String externalCourseId = externalAPI.getExternalCourseId(courseCode);

        if (externalStudentId == null || externalCourseId == null) {
            System.err.println("Adapter: Could not map UMS IDs to external IDs for " + studentId + ", " + courseCode);
            return "N/A";
        }

        int score = externalAPI.fetchScore(externalStudentId, externalCourseId);
        return convertScoreToLetterGrade(score);
    }

    /**
     * Adapts the call to update student grade from UMS format to external API format.
     * Converts letter grade (from UMS) to numerical score (for external).
     */
    @Override
    public boolean updateStudentGrade(String studentId, String courseCode, String newGrade) {
        // Map UMS IDs to external system IDs
        String externalStudentId = externalAPI.getExternalStudentId(studentId);
        String externalCourseId = externalAPI.getExternalCourseId(courseCode);

        if (externalStudentId == null || externalCourseId == null) {
            System.err.println("Adapter: Could not map UMS IDs to external IDs for " + studentId + ", " + courseCode);
            return false;
        }

        int score = convertLetterGradeToScore(newGrade);
        if (score == -1) {
            System.err.println("Adapter: Invalid grade format for conversion: " + newGrade);
            return false;
        }
        return externalAPI.setScore(externalStudentId, externalCourseId, score);
    }

    /**
     * Helper method to convert numerical score to a simple letter grade.
     * This is part of the adaptation logic.
     */
    private String convertScoreToLetterGrade(int score) {
        if (score == -1) return "N/A";
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    /**
     * Helper method to convert a simple letter grade to a numerical score.
     * This is part of the adaptation logic.
     */
    private int convertLetterGradeToScore(String grade) {
        if (grade == null) return -1;
        switch (grade.toUpperCase()) {
            case "A": return 95;
            case "B": return 85;
            case "C": return 75;
            case "D": return 65;
            case "F": return 55; // Placeholder
            default: return -1; // Invalid grade
        }
    }
}