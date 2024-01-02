package com.courseoutlinemanager.lecturer;

import com.courseoutlinemanager.common.customexception.*;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.common.ProcessString;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LecturerManager {
    private ArrayList<Lecturer> lecturerList;

    public LecturerManager() {
        this.lecturerList = new ArrayList<>();
    }

    public ArrayList<Lecturer> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerList(ArrayList<Lecturer> lecturerList) {
        this.lecturerList = lecturerList;
    }

    public int indexOfLecturer(Lecturer lect) {
        return this.lecturerList.indexOf(lect);
    }

    public void addLecturer(Lecturer lecturer) {
        lecturerList.add(lecturer);
    }

    public Lecturer createLecturer(String name) {
        Lecturer newLecturer = new Lecturer(name);
        while (this.containsLecturer(newLecturer)) {
            newLecturer = new Lecturer(name);
        }
        return newLecturer;
    }

    public Lecturer createLecturer(String name, String id) throws AlreadyExistException {
        Lecturer newLecturer = new Lecturer(name, id);
        int checkExistIndex = this.indexOfLecturer(newLecturer);
        if (checkExistIndex != -1)
            throw new AlreadyExistException("The id of these lecturers is duplicated: "
                    + this.lecturerList.get(checkExistIndex) + " - " + newLecturer + ".");
        return newLecturer;
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
    public Lecturer getLecturerById(String id) throws NotFoundException {
        for (Lecturer lecturer : lecturerList) {
            if (ProcessString.equalsByAlphabet(lecturer.getId(), id))
                return lecturer;
        }
        throw new NotFoundException("Coundn't find lecturer with id:" + id + ".");
    }

    public Lecturer findLecturer(String codeOrName) throws NotFoundException {
        for (Lecturer lecturer : lecturerList) {
            if (ProcessString.equalsByAlphabet(lecturer.getId(), codeOrName)
                    || ProcessString.equalsByAlphabet(lecturer.getName(), codeOrName))
                return lecturer;
        }
        throw new NotFoundException("Coundn't find lecturer with id:" + codeOrName + ".");
    }

    public ArrayList<CourseOutline> numberOfOutlinewithCredits(String codeOrNameLecturer, double credits)
            throws NotFoundException {
        Lecturer lecturer = findLecturer(codeOrNameLecturer);
        ArrayList<CourseOutline> filteredOutlines = new ArrayList<>();

        if (lecturer != null) {
            filteredOutlines = lecturer.getCourseOutlineList().stream()
                    .filter(outline -> outline.getCourse().getCourseCredits() == credits)
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            throw new NotFoundException("Coundn't find lecturer with id:" + codeOrNameLecturer + ".");

        }
        return filteredOutlines;
    }

    public void sortCourseOutlines(String codeOrName) throws NotFoundException {
        Lecturer lecturer = findLecturer(codeOrName);
        lecturer.sortCourseOutline();
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
        return this.getLecturerById(id).getCourseOutlineList();
    }

}
