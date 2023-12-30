package com.courseoutlinemanager.courseoutline;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.common.customexception.AlreadyExistException;
import com.courseoutlinemanager.common.customexception.NotFoundException;

public class CourseOutlineManager {
    private ArrayList<CourseOutline> courseOutlineList = new ArrayList<>();

    public void addCourseOutline(CourseOutline newCourseOutline) throws AlreadyExistException {
        for (CourseOutline outLine : courseOutlineList) {
            if (outLine.equals(newCourseOutline)) {
                throw new AlreadyExistException(newCourseOutline.toString() + " already existed");
            }
        }
        courseOutlineList.add(newCourseOutline);
    }

    public void deleteCourseOutline(CourseOutline deleteCourseOutline) throws NotFoundException {
        boolean removed = courseOutlineList.removeIf(outline -> outline.equals(deleteCourseOutline));
        if (!removed) {
            throw new NotFoundException("Course outline not found");
        }
    }

    public ArrayList<CourseOutline> findCourseOutline(String nameorCode) {
        return this.courseOutlineList.stream()
                .filter(outLine -> ProcessString.equalsByAlphabet(outLine.getCourse().getCourseName(), nameorCode)
                        || ProcessString.equalsByAlphabet(outLine.getCourse().getCourseCode(), nameorCode))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<CourseOutline> getCourseOutlineList() {
        return courseOutlineList;
    }
}
