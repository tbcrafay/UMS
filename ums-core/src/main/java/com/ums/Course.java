// src/main/java/com/ums/Course.java
package com.ums;

import java.util.List;
import java.util.Collections; // For unmodifiable list

/**
 * Represents a Course in the University Management System.
 * This is a complex object built using the Builder Pattern.
 */
public class Course {
    private final String courseCode;
    private final String title;
    private final int credits;
    private final String department;
    private final List<String> prerequisites; // Optional
    private final boolean isElective; // Optional
    private final String description; // Optional

    // Private constructor, only accessible by the CourseBuilder
    private Course(CourseBuilder builder) {
        this.courseCode = builder.courseCode;
        this.title = builder.title;
        this.credits = builder.credits;
        this.department = builder.department;
        // Make prerequisites list unmodifiable to ensure immutability
        this.prerequisites = builder.prerequisites != null ?
                             Collections.unmodifiableList(builder.prerequisites) : Collections.emptyList();
        this.isElective = builder.isElective;
        this.description = builder.description;
    }

    // Getters for all fields
    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public String getDepartment() {
        return department;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public boolean isElective() {
        return isElective;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Course{" +
               "courseCode='" + courseCode + '\'' +
               ", title='" + title + '\'' +
               ", credits=" + credits +
               ", department='" + department + '\'' +
               ", prerequisites=" + prerequisites +
               ", isElective=" + isElective +
               ", description='" + (description != null ? description : "N/A") + '\'' +
               '}';
    }

    // Static nested Builder class
    public static class CourseBuilder {
        // Required parameters
        private final String courseCode;
        private final String title;
        private final int credits;
        private final String department;

        // Optional parameters - initialized to default values or null
        private List<String> prerequisites;
        private boolean isElective = false; // Default to mandatory
        private String description;

        // Constructor for required parameters
        public CourseBuilder(String courseCode, String title, int credits, String department) {
            this.courseCode = courseCode;
            this.title = title;
            this.credits = credits;
            this.department = department;
        }

        // Setter methods for optional parameters (return 'this' for chaining)
        public CourseBuilder withPrerequisites(List<String> prerequisites) {
            this.prerequisites = prerequisites;
            return this;
        }

        public CourseBuilder asElective(boolean isElective) {
            this.isElective = isElective;
            return this;
        }

        public CourseBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        // Build method to create the Course object
        public Course build() {
            return new Course(this);
        }
    }
}