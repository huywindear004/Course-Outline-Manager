package com.courseoutlinemanager.common.input;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.common.customexception.NotFoundException;
import com.courseoutlinemanager.common.customexception.OutOfCapacityException;
import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.CourseManager;
import com.courseoutlinemanager.course.knowledgeblock.KnowledgeBlockManager;
import com.courseoutlinemanager.educationalsystem.EducationalSystem;
import com.courseoutlinemanager.educationalsystem.EducationalSystemManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FileInput {
    private static Scanner fileScanner;

    /**
     * Get the course by reading file
     * @throws FileNotFoundException
     * @throws NotFoundException
     * If couldn't find educationalSystem or knowledgeBlock
     */
    public static Course getCourse(File input, EducationalSystemManager eSM, CourseManager cM, KnowledgeBlockManager kBM)
            throws FileNotFoundException, NotFoundException {
        fileScanner = new Scanner(input);
        Course newCourse = new Course();
        String[] temp;
        String currLine;
        while (fileScanner.hasNextLine()) {
            currLine = fileScanner.nextLine();
            temp = currLine.split(":");
            if (ProcessString.equalsByAlphabet(temp[0], "CourseCode")) {
                newCourse.setCourseCode(temp[1].trim());
            } else if (ProcessString.equalsByAlphabet(temp[0], "CourseName")) {
                newCourse.setCourseName(temp[1].trim());
            } else if (ProcessString.equalsByAlphabet(temp[0], "KnowledgeBlock")) {
                newCourse.setKnowledgeBlock(kBM.getKnowledgeBlock(temp[1]));
            } else if (ProcessString.equalsByAlphabet(temp[0], "Credits")) {
                newCourse.setCourseCredits(Integer.parseInt(temp[1].trim()));
            } else if (ProcessString.equalsByAlphabet(temp[0], "Description")) {
                newCourse.setCourseDescription(temp[1].trim());
            } else if (ProcessString.equalsByAlphabet(temp[0], "PrerequisiteCourse")
                    || ProcessString.equalsByAlphabet(temp[0], "previousCourse")) {
                //typeOfRequirement will store the type of requirements(previousCourse, PrerequisiteCourse, ...)
                String typeOfRequirements = temp[0];
                // temp[] would contains courseStrings
                temp = temp[1].trim().split("[,;]+");
                //This loop add requirements to newCourse step-by-step
                for (String courseString : temp) {
                    // split courseString to get courseCode and courseName
                    String[] nameAndCode = courseString.split("[()_-]+");
                    Course tempCourse = new Course(nameAndCode[0], nameAndCode[1]);
                    // Check if the tempCourse already existed in cM.courseList
                    try {
                        // if the tempCourse exist then change the reference of tempCourse to the existed course
                        tempCourse = cM.getCourse(tempCourse);
                    } catch (NotFoundException e) {
                        // if the course doensn't exist then add it to cM.courseList
                        cM.addCourse(tempCourse);
                    }
                    //add it to requirements
                    try {
                        newCourse.addCourseRequirements(typeOfRequirements, tempCourse);
                    } catch (OutOfCapacityException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return newCourse;
    }
    
}
