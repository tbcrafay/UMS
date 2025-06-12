// src/main/java/com/ums/Department.java
package com.ums;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite: Represents a department or a faculty that can contain
 * other DepartmentComponents (both individual courses and sub-departments).
 * It implements the DepartmentComponent interface.
 */
public class Department implements DepartmentComponent {
    private String name;
    private List<DepartmentComponent> components;

    public Department(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    /**
     * Adds a DepartmentComponent (CourseComponent or another Department) to this composite.
     * @param component The component to add.
     */
    public void addComponent(DepartmentComponent component) {
        components.add(component);
    }

    /**
     * Removes a DepartmentComponent from this composite.
     * @param component The component to remove.
     */
    public void removeComponent(DepartmentComponent component) {
        components.remove(component);
    }

    @Override
    public void displayDetails(String indent) {
        System.out.println(indent + "+ " + name + " (Total Credits: " + getTotalCredits() + ")");
        // Recursively display details of children components
        for (DepartmentComponent component : components) {
            component.displayDetails(indent + "  "); // Increase indent for children
        }
    }

    @Override
    public int getTotalCredits() {
        int totalCredits = 0;
        // Recursively sum credits from all children components
        for (DepartmentComponent component : components) {
            totalCredits += component.getTotalCredits();
        }
        return totalCredits;
    }

    public String getName() {
        return name;
    }
}