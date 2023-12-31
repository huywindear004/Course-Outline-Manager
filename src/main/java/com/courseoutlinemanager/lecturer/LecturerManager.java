package com.courseoutlinemanager.lecturer;

import com.courseoutlinemanager.common.customexception.*;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.common.ProcessString;

import java.util.ArrayList;

public class LecturerManager {
    private ArrayList<Lecturer> lecturerList;

    public LecturerManager() {
        this.lecturerList = new ArrayList<>();
    }

    public ArrayList<Lecturer> getLecturerListlecturerList() {
        return lecturerList;
    }

    public void setLecturerListlecturerList(ArrayList<Lecturer> lecturerList) {
        this.lecturerList = lecturerList;
    }

    public Lecturer getLecturer(Lecturer lect) throws NotFoundException {
        int index = this.lecturerList.indexOf(lect);
        if (index == -1)
            throw new NotFoundException("Couldn't find " + lect.toString());
        return this.lecturerList.get(index);
    }

    public void addLecturer(Lecturer lecturer) {
        lecturerList.add(lecturer);
    }

    public void removeLecturer(Lecturer lecturer) {
        lecturerList.remove(lecturer);
    }

    public boolean containsLecturer(Lecturer lecturer) {
        return this.lecturerList.contains(lecturer);
    }

    /**
     * Find lecturer by their id
     * 
     * @param id
     *           Id of lecturer needs to be found
     * @throws NotFoundException
     *                           If cannot find the lecturer with the specified id
     */
    public Lecturer findLecturerById(String id) throws NotFoundException {
        for (Lecturer lecturer : lecturerList) {
            if (ProcessString.equalsByAlphabet(lecturer.getId(), id)) {
                return lecturer;
            }
        }
        throw new NotFoundException("Coundn't find lecturer with id:" + id + ".");
    }

    /**
     * Find lecturer by the given id and return their courseOutline list
     * 
     * @param id
     *           Id of lecturer needs to be found
     * @throws NotFoundException
     *                           If cannot find the lecturer with the specified id
     * @return
     *         Outline list if found the lecturer
     */
    public ArrayList<CourseOutline> getCourseOutlinesByLecId(String id) throws NotFoundException {
        return this.findLecturerById(id).getCourseOutlineList();
    }

}
