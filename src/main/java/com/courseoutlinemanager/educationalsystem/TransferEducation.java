package com.courseoutlinemanager.educationalsystem;


public class TransferEducation extends EducationalSystem {
    private static final int MAX_OUTLINE_PER_COURSE = 1;

    @Override
    public int getMaxOutlinePerCourse() {
        return MAX_OUTLINE_PER_COURSE;
    }
    
    @Override
    public String getTypeName() {
        return "Transfer Education";
    }
}
