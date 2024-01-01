package com.courseoutlinemanager.courseoutline;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.courseoutlinemanager.common.ProcessString;

public class CourseOutlineManager {
    private ArrayList<CourseOutline> courseOutlineList;

    public CourseOutlineManager() {
        courseOutlineList = new ArrayList<>();
    }

    public boolean addCourseOutline(CourseOutline newCourseOutline){
        return courseOutlineList.add(newCourseOutline);
    }

    public int indexOfCourseOutline(CourseOutline outline) {
        return this.courseOutlineList.indexOf(outline);
    }

    public boolean containsCourseOutline(CourseOutline outline) {
        return this.courseOutlineList.contains(outline);
    }

    public boolean deleteCourseOutline(CourseOutline deleteCourseOutline){
        return courseOutlineList.removeIf(outline -> outline.equals(deleteCourseOutline));
    }

    public ArrayList<CourseOutline> findCourseOutline(String nameorCode) {
        return this.courseOutlineList.stream()
                .filter(outLine -> ProcessString.containsByAlphabet(outLine.getCourse().getCourseName(), nameorCode)
                        || ProcessString.containsByAlphabet(outLine.getCourse().getCourseCode(), nameorCode))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<CourseOutline> getCourseOutlineList() {
        return courseOutlineList;
    }
}
