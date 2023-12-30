package com.courseoutlinemanager.educationalsystem;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.common.customexception.NotFoundException;

import java.util.ArrayList;

public class EducationalSystemManager {
    private ArrayList<EducationalSystem> educationalSystemList;

    public EducationalSystemManager() {
        educationalSystemList = new ArrayList<>();
        educationalSystemList.add(new FormalEducation());
        educationalSystemList.add(new TransferEducation());
    }

    /**
     * Get the eduational system
     * 
     * @param type
     *             The name of the type of educational system
     * @throws NotFoundException
     *                           If couldn't find the required type of ES
     * @return
     *         Educational system if it's found
     */
    public EducationalSystem getEducationalSystem(String type) throws NotFoundException {
        for (EducationalSystem e : educationalSystemList) {
            if (ProcessString.equalsByAlphabet(type, e.getTypeName()))
                return e;
        }
        throw new NotFoundException("Couldn't find " + type);
    }

    public ArrayList<EducationalSystem> getEducationalSystemList() {
        return this.educationalSystemList;
    }
}
