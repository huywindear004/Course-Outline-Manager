package com.courseoutlinemanager.courseoutline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.courseoutlinemanager.common.ProcessString;

public class CourseOutlineManager {
    private ArrayList<CourseOutline> courseOutlineList;

    public CourseOutlineManager() {
        courseOutlineList = new ArrayList<>();
    }

    public boolean addCourseOutline(CourseOutline newCourseOutline) {
        return courseOutlineList.add(newCourseOutline);
    }

    public int indexOfCourseOutline(CourseOutline outline) {
        return this.courseOutlineList.indexOf(outline);
    }

    public boolean containsCourseOutline(CourseOutline outline) {
        return this.courseOutlineList.contains(outline);
    }

    public boolean deleteCourseOutline(CourseOutline deleteCourseOutline) {
        return courseOutlineList.removeIf(outline -> outline.equals(deleteCourseOutline));
    }

    public ArrayList<CourseOutline> findCourseOutline(String nameorCode) {
        return this.courseOutlineList.stream()
                .filter(outLine -> ProcessString.containsByAlphabet(outLine.getCourse().getCourseName(), nameorCode)
                        || ProcessString.containsByAlphabet(outLine.getCourse().getCourseCode(), nameorCode))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<CourseOutline> findCourseOutlineListByIdLecturer(String codeLecturer) {
        return this.courseOutlineList.stream()
                .filter(outLine -> ProcessString.containsByAlphabet(outLine.getCompiler().getName(), codeLecturer))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<CourseOutline> numberOfOutlinewithCredits(double credits) {
        return this.courseOutlineList.stream()
                .filter(outline -> outline.getCourse().getCourseCredits() == credits)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<CourseOutline> getCourseOutlineList() {
        return courseOutlineList;
    }

    public ArrayList<CourseOutline> sortedCourseOutlines() {
        Comparator<CourseOutline> comparator = new Comparator<CourseOutline>() {
            @Override
            public int compare(CourseOutline outline1, CourseOutline outline2) {

                double credit1 = outline1.getCourse().getCourseCredits();
                double credit2 = outline2.getCourse().getCourseCredits();

                int creditComparison = Double.compare(credit2, credit1);

                if (creditComparison == 0) {
                    return outline1.getCourse().getCourseCode().compareTo(outline2.getCourse().getCourseCode());
                }
                return creditComparison;
            }
        };
        Collections.sort(this.courseOutlineList, comparator);
        return this.courseOutlineList;
    }
}
