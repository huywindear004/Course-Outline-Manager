package com.courseoutlinemanager.educationalsystem;

import java.util.ArrayList;

public class EducationalSystemManager {
    private ArrayList<EducationalSystem> listEducationalSystem;

    public EducationalSystemManager() {
        listEducationalSystem = new ArrayList<>();
    }

    public void displayEducationalSystems() {
        for (EducationalSystem system : listEducationalSystem) {
            System.out.println(system.toString()); // Assuming EducationalSystem has a toString() method
        }
    }
}
