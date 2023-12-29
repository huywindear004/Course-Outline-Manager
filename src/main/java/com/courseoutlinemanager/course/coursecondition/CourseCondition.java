package com.courseoutlinemanager.course.coursecondition;

import com.courseoutlinemanager.common.customexception.NotFoundException;
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

    private Course getCourseIfItExist(Course course) throws NotFoundException{
        for (Course c : courses) {
            if (course.equals(c))
                return c;
        }
        throw new NotFoundException();
    }
    /**
     * Add course to list. If the toBeAddedCourse is already in the requirement, then 
     * assign the existedCourse to the toBeAddedCourse.
     * @param course
     *               Given course
     * @throws OutOfCapacityException
     *                                If the list is enough of elements.
     */
    public void addCourse(Course course) throws OutOfCapacityException{
        try{course = this.getCourseIfItExist(course);
        } catch (NotFoundException ignored) {}
        if (this.courses.size() >= this.getMAX_COURSES())
            throw new OutOfCapacityException(String.format("The number of courses of %s is enough!",getTypeName()));
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
