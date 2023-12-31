package com.courseoutlinemanager.lecturer;

import com.courseoutlinemanager.common.customexception.*;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.common.ProcessString;


import java.util.ArrayList;

public class LecturerManager {
    private ArrayList<Lecturer> lecturers;

    public LecturerManager() {
        this.lecturers = new ArrayList<>();
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public Lecturer getLecturer(Lecturer lect) throws NotFoundException{
        int index = this.lecturers.indexOf(lect);
        if(index == -1)
            throw new NotFoundException("Couldn't find " + lect.toString());
        return this.lecturers.get(index);
    }

    /**
     * Add lecturer to the list
     * @param lecturer
     * Lecturer needs to be added
     * @throws AlreadyExistException
     * If that lecturer already in the list
     */
    public void addLecturer(Lecturer lecturer){
        lecturers.add(lecturer);
    }

    public void removeLecturer(Lecturer lecturer) {
        lecturers.remove(lecturer);
    }

    public boolean containsLecturer(Lecturer lecturer) {
        return this.lecturers.contains(lecturer);
    }

    /**
     * Find lecturer by their id
     * @param id
     * Id of lecturer needs to be found
     * @throws NotFoundException
     * If cannot find the lecturer with the specified id
     */
    public Lecturer findLecturerById(String id) throws NotFoundException {
        for (Lecturer lecturer : lecturers) {
            if (ProcessString.equalsByAlphabet(lecturer.getId(), id)) {
                return lecturer;
            }
        }
        throw new NotFoundException("Coundn't find lecturer with id:" + id + ".");
    }

    /**
     * Find lecturer by the given id and return their courseOutline list
     * @param id
     * Id of lecturer needs to be found
     * @throws NotFoundException
     * If cannot find the lecturer with the specified id
     * @return 
     * Outline list if found the lecturer
     */
    public ArrayList<CourseOutline> getCourseOutlinesByLecId(String id) throws NotFoundException {
        return this.findLecturerById(id).getCourseOutlines();
    }

}
