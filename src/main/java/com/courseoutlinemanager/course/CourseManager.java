package com.courseoutlinemanager.course;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.common.customexception.NotFoundException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CourseManager {
    private ArrayList<Course> courseList = new ArrayList<>();

    public ArrayList<Course> findCourse(String nameOrCode) {
        return this.courseList.stream()
                .filter(course -> ProcessString.containsByAlphabet(course.getCourseCode(), nameOrCode)
                        || ProcessString.containsByAlphabet(course.getCourseName(), nameOrCode))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Course getCourseIfItExist(Course course) throws NotFoundException{
        for(Course c : courseList){
            if(course.equals(c))
                return c;
        }
        throw new NotFoundException(String.format("Couldn't find %s(%s)!",course.getCourseName(),course.getCourseCode()));
    }

    public boolean containsCourse(Course course) {
        return this.courseList.contains(course);
    }

    public void addCourse(Course newCourse) {
        courseList.add(newCourse);
    }

    public boolean deleteCourse(Course deleteCourse) {
        return courseList.remove(deleteCourse);
    }

    public boolean deleteCourse(String courseName, String courseCode) {
        Course toDelete = new Course(courseCode, courseName);
        return courseList.remove(toDelete);
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }
}
