package com.courseoutlinemanager.course.coursecondition;

import com.courseoutlinemanager.common.customexception.AlreadyExistException;
import com.courseoutlinemanager.common.customexception.NotFoundException;
import com.courseoutlinemanager.common.customexception.OutOfCapacityException;
import com.courseoutlinemanager.course.Course;

import java.util.ArrayList;

public abstract class CourseCondition {
    private ArrayList<Course> courseList;

    public abstract String getTypeName();

    public abstract int getMAX_COURSES();

    public CourseCondition() {
        courseList = new ArrayList<>();
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
        this.courseList = courses;
    }

    private Course getCourseIfItExist(Course toFind) throws NotFoundException {
        int index = this.courseList.indexOf(toFind);
        if (index == -1)
            throw new NotFoundException("Couldn't find " + toFind.toString());
        return this.courseList.get(index);
    }

    /**
     * Add course to list. If the toBeAddedCourse is already in the requirement,
     * then assign the existedCourse to the toBeAddedCourse.
     * 
     * @param course
     *               Given course
     * @throws OutOfCapacityException
     *                                If the list is enough of elements.
     */
    public void addCourse(Course toBeAddedCourse) throws OutOfCapacityException, AlreadyExistException {
        if (this.size() >= this.getMAX_COURSES())
            throw new OutOfCapacityException(String.format("The number of courses of %s is enough!", this.getTypeName()));

        try {
            toBeAddedCourse = this.getCourseIfItExist(toBeAddedCourse);
            
            // if it already existed - throw AlreadyExistException
            throw new AlreadyExistException(toBeAddedCourse.toString() + " already existed in " + this.getTypeName());
            
        // if it didn't exist - add it to the list
        } catch (NotFoundException e) {
            this.courseList.add(toBeAddedCourse);
        }
    }

    public boolean removeCourse(Course toBeRemovedCourse) {
        return this.courseList.remove(toBeRemovedCourse);
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

    public int size() {
        return this.courseList.size();
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
        return this.courseList.contains(course);
    }
    
    public Course findCourseById(String courseId) {
        for (Course course : this.courseList) {
            if (course.getCourseCode().equals(courseId)) {
                return course;
            }
        }
        return null; 
    }

    public ArrayList<Course> getCourseList() {
        return this.courseList;
    }

    public boolean isEmpty(){
        return this.courseList.isEmpty();
    }
}
