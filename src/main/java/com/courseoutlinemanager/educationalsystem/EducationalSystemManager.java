package com.courseoutlinemanager.educationalsystem;

import java.util.ArrayList;

public class EducationalSystemManager {
    private ArrayList<EducationalSystem> listEducationalSystem;

    public EducationalSystemManager() {
        this.listEducationalSystem = new ArrayList<>();
    }

    public void addEducationalSystem(EducationalSystem educationalSystem) {
        this.listEducationalSystem.add(educationalSystem);
    }

    public void removeEducationalSystem(EducationalSystem educationalSystem) {
        this.listEducationalSystem.remove(educationalSystem);
    }

    public void displayEducationalSystems() {
        for (EducationalSystem system : listEducationalSystem) {
            System.out.println(system.toString()); // Assuming EducationalSystem has a toString() method
        }
    }
}
