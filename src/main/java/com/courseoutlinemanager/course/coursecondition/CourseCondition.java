package com.courseoutlinemanager.course.coursecondition;

import com.courseoutlinemanager.common.customexception.AlreadyExistException;
import com.courseoutlinemanager.common.customexception.OutOfCapacityException;
import com.courseoutlinemanager.course.Course;

import java.util.ArrayList;

public abstract class CourseCondition {
    private ArrayList<Course> courses;

    public abstract String getTypeName();

    public abstract int getMAX_COURSES();

    public CourseCondition() {
        courses = new ArrayList<>();
    }

    /**
     * Assign given courses.
     * 
     * @param courses
     *                Given courses
     * @throws OutOfCapacityException
     *                                If it's size is larger than the capacity
     */
    public CourseCondition(ArrayList<Course> courses) throws OutOfCapacityException {
        if (courses.size() > this.getMAX_COURSES())
            throw new OutOfCapacityException();
        this.courses = courses;
    }

    /**
     * Add course to list.
     * 
     * @param course
     *               Given course
     * @throws OutOfCapacityException
     *                                If the list is enough of elements.
     * @throws AlreadyExistException
     *                                If there is the equivalent course in the list
     */
    public void addCourse(Course course) throws OutOfCapacityException, AlreadyExistException {
        if (this.courses.contains(course))
            throw new AlreadyExistException();
        if (this.courses.size() >= this.getMAX_COURSES())
            throw new OutOfCapacityException();
        this.courses.add(course);
    }

    /**
     * Remove the specified course. If the given course isn't in this list then the
     * list wouldn't be changed and return false
     * 
     * @param course
     *               To delete course
     * @return
     *         True if the given course is present
     */
    public boolean removeCourse(Course course) {
        return this.courses.remove(course);
    }

    public int size() {
        return this.courses.size();
    }

    /**
     * Return true if this list contains the specified course. False if vice versa
     * 
     * @param course
     *               To find course
     * @return
     *         True if this list contains the specified course
     */
    public boolean contains(Course course) {
        return this.courses.contains(course);
    }

    public ArrayList<Course> getCourses() {
        return this.courses;
    }
}
