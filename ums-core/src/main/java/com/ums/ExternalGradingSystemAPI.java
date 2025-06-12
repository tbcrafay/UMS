// src/main/java/com/ums/ExternalGradingSystemAPI.java
package com.ums;

import java.util.HashMap;
import java.util.Map;

/**
 * Adaptee: This class represents an existing external grading system
 * with an incompatible interface for our UMS.
 * It uses a different method signature and internal data structure.
 */
public class ExternalGradingSystemAPI {

    // Simulates an external database or service storing grades
    // Key format: studentExternalID_courseExternalID
    private Map<String, String> externalGrades = new HashMap<>();

    public ExternalGradingSystemAPI() {
        // Populate with some dummy data for demonstration
        externalGrades.put("ext_s001_ext_c101", "88"); // Alice in Prog Fundamentals
        externalGrades.put("ext_s001_ext_c401", "92"); // Alice in Adv DB
        externalGrades.put("ext_s002_ext_c101", "75"); // Bob in Prog Fundamentals
    }

    /**
     * Fetches a score from the external system.
     * Note the different parameter names and return type (integer score).
     * @param studentExternalID The external ID of the student.
     * @param courseExternalID The external ID of the course.
     * @return The numerical score (0-100), or -1 if not found.
     */
    public int fetchScore(String studentExternalID, String courseExternalID) {
        String key = studentExternalID + "_" + courseExternalID;
        System.out.println("External API: Fetching score for key: " + key);
        return Integer.parseInt(externalGrades.getOrDefault(key, "-1"));
    }

    /**
     * Sets a score in the external system.
     * @param studentExternalID The external ID of the student.
     * @param courseExternalID The external ID of the course.
     * @param score The numerical score to set.
     * @return true if updated, false if error.
     */
    public boolean setScore(String studentExternalID, String courseExternalID, int score) {
        if (score < 0 || score > 100) {
            System.out.println("External API: Invalid score: " + score);
            return false;
        }
        String key = studentExternalID + "_" + courseExternalID;
        externalGrades.put(key, String.valueOf(score));
        System.out.println("External API: Set score for key " + key + " to " + score);
        return true;
    }

    // Helper to convert internal UMS IDs to external IDs (simple mapping for demo)
    public String getExternalStudentId(String umsStudentId) {
        if ("S001".equals(umsStudentId)) return "ext_s001";
        if ("S002".equals(umsStudentId)) return "ext_s002";
        return null; // Or throw exception
    }

    public String getExternalCourseId(String umsCourseCode) {
        if ("CS101".equals(umsCourseCode)) return "ext_c101";
        if ("CS401".equals(umsCourseCode)) return "ext_c401";
        return null; // Or throw exception
    }
}