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

    public Course getCourse(Course toFind) throws NotFoundException{
        int index = this.courseList.indexOf(toFind);
        if(index == -1)
            throw new NotFoundException("Couldn't find " + toFind.toString());
        return this.courseList.get(index);
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


    public ArrayList<Course> getCourseList() {
        return courseList;
    }
}
