package com.courseoutlinemanager.course.coursecondition;

import com.courseoutlinemanager.course.Course;

public abstract class CourseCondition {
    private Course course;

    public abstract String getTypeName();

    public abstract int getMAX_COURSES();

    public CourseCondition(Course course) {
        this.course = course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return this.course;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
			return true;
		if(object == null || this.getClass() != object.getClass())
            return false;
        CourseCondition courseCondition = (CourseCondition) object;
        return this.getCourse() == courseCondition.getCourse();
    }
}
