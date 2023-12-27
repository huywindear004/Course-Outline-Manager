package com.courseoutlinemanager.course.coursecondition;

import com.courseoutlinemanager.course.Course;

public class PreviousCourse extends CourseCondition {
    private static final int MAX_COURSES = 3;

    public PreviousCourse(Course course) {
        super(course);
    }

    @Override
    public int getMAX_COURSES() {
        return MAX_COURSES;
    }

    @Override
    public String getTypeName() {
        return "Previous Course";
    }
}
        