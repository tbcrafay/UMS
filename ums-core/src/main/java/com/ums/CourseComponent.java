// src/main/java/com/ums/CourseComponent.java
package com.ums;

/**
 * Leaf: Represents an individual course in the academic hierarchy.
 * It implements the DepartmentComponent interface.
 */
public class CourseComponent implements DepartmentComponent {
    private Course course; // The actual Course object (built by Builder Pattern)

    public CourseComponent(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null for CourseComponent.");
        }
        this.course = course;
    }

    @Override
    public void displayDetails(String indent) {
        System.out.println(indent + "- Course: " + course.getTitle() + " (" + course.getCourseCode() + ")");
        System.out.println(indent + "  Credits: " + course.getCredits());
        if (course.getPrerequisites() != null && !course.getPrerequisites().isEmpty()) {
            System.out.println(indent + "  Prerequisites: " + String.join(", ", course.getPrerequisites()));
        }
        System.out.println(indent + "  Type: " + (course.isElective() ? "Elective" : "Mandatory"));
    }

    @Override
    public int getTotalCredits() {
        return course.getCredits();
    }

    // You can add a getter for the underlying Course object if needed
    public Course getCourse() {
        return course;
    }
}