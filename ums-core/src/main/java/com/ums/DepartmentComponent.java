// src/main/java/com/ums/DepartmentComponent.java
package com.ums;

/**
 * Component Interface: Defines the common interface for both individual courses (Leaf)
 * and departments/faculties (Composite) in the academic hierarchy.
 */
public interface DepartmentComponent {
    /**
     * Displays details of the component.
     * @param indent Level of indentation for hierarchical display.
     */
    void displayDetails(String indent);

    /**
     * Returns the total credits represented by this component.
     * For a single course, it's its credits. For a department, it's the sum of its children's credits.
     * @return Total credits.
     */
    int getTotalCredits();
}