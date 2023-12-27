package com.courseoutlinemanager.course.coursecondition;

import com.courseoutlinemanager.course.Course;

public class PrerequisiteCourse extends CourseCondition {
    private static final int MAX_COURSES = 3;

    public PrerequisiteCourse(Course course) {
        super(course);
    }

    @Override
    public int getMAX_COURSES() {
        return MAX_COURSES;
    }
    
    @Override
    public String getTypeName() {
        return "Prerequisite Course";
    }
}
