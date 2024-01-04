package com.courseoutlinemanager.course.coursecondition;

public class PrerequisiteCourses extends CourseCondition {
    private static final int MAX_COURSES = 3;

    private String name;


    public PrerequisiteCourses() {

    }
    public PrerequisiteCourses(String name) {
        this.name = name;
    }

    @Override
    public int getMAX_COURSES() {
        return MAX_COURSES;
    }
    
    @Override
    public String getTypeName() {
        return "Prerequisite Courses";
    }
}
