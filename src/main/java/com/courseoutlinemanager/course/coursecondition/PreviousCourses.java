package com.courseoutlinemanager.course.coursecondition;

public class PreviousCourses extends CourseCondition {
    private static final int MAX_COURSES = 3;

    @Override
    public int getMAX_COURSES() {
        return MAX_COURSES;
    }

    @Override
    public String getTypeName() {
        return "Previous Course";
    }
}
        