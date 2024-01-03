package com.courseoutlinemanager.common.input;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.common.customexception.AlreadyExistException;
import com.courseoutlinemanager.common.customexception.NotFoundException;
import com.courseoutlinemanager.common.customexception.OutOfCapacityException;
import com.courseoutlinemanager.common.customexception.WrongFormatException;
import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.CourseManager;
import com.courseoutlinemanager.course.knowledgeblock.KnowledgeBlock;


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FileInput {
    private static Scanner fileScanner;

    /**
     * Get the course by reading file
     * 
     * @throws FileNotFoundException
     * @throws WrongFormatException
     */
    public static Course readCourse(File input, CourseManager cM)
            throws FileNotFoundException, WrongFormatException {
        fileScanner = new Scanner(input);
        Course newCourse = new Course();
        String[] temp;
        String currLine;
        while (fileScanner.hasNextLine()) {
            currLine = fileScanner.nextLine();
            temp = currLine.split(":");

            if (ProcessString.equalsByAlphabet(temp[0], "CourseCode")) {
                newCourse.setCourseCode(temp[1].trim());
                try {
                    Course tempCourse = cM.getCourse(newCourse);
                    System.out.println("Found the existed course. Auto change " + newCourse + " to " + tempCourse);
                    newCourse = tempCourse;
                } catch (NotFoundException ignored) {
                }
            } else if (ProcessString.equalsByAlphabet(temp[0], "CourseName")) {
                newCourse.setCourseName(temp[1].trim());
            } else if (ProcessString.equalsByAlphabet(temp[0], "KnowledgeBlock")) {
                newCourse.setKnowledgeBlock(KnowledgeBlock.valueOf(temp[1].trim().toUpperCase()));
            } else if (ProcessString.equalsByAlphabet(temp[0], "Credits")) {
                newCourse.setCourseCredits(Integer.parseInt(temp[1].trim()));
            } else if (ProcessString.equalsByAlphabet(temp[0], "Description")) {
                newCourse.setCourseDescription(temp[1].trim());
            } else if (ProcessString.equalsByAlphabet(temp[0], "PrerequisiteCourses")
                    || ProcessString.equalsByAlphabet(temp[0], "previousCourses")) {
                // typeOfRequirement will store the type of requirements(previousCourse,
                // PrerequisiteCourse, ...)
                String typeOfRequirements = temp[0];
                // temp[] would contains courseStrings
                temp = temp[1].split("[,;]+");
                // This loop add requirements to newCourse step-by-step
                for (String courseString : temp) {
                    // split courseString to get courseCode and courseName
                    String[] nameAndCode = courseString.trim().split("[()_-]+");
                    // tempCourse is the course that is gonna be added to requirement
                    Course tempCourse = new Course(nameAndCode[1], nameAndCode[0]);

                    boolean isAddableCourse = false;
                    // Check if the tempCourse already existed in cM.courseList
                    try {
                        // if the tempCourse exist then change the reference of tempCourse to the
                        // existed course
                        tempCourse = cM.getCourse(tempCourse);
                        isAddableCourse = false;
                    } catch (NotFoundException e) {
                        isAddableCourse = true;
                    }

                    // add it to requirements
                    try {
                        newCourse.addCourseToRequirementList(typeOfRequirements, tempCourse);
                    } catch (OutOfCapacityException | NotFoundException | AlreadyExistException e) {
                        System.out.println(e.getMessage() + " in " + newCourse + "{" + input.getName() + "}");
                        isAddableCourse = false;
                    }

                    if (isAddableCourse)
                        cM.addCourse(tempCourse);
                }
            } else
                throw new WrongFormatException("The input format of " + input.getName() + " is wrong.");
        }

        if (!cM.containsCourse(newCourse))
            cM.addCourse(newCourse);
        return newCourse;
    }

    public static void readCourseFolder(String inputPath, CourseManager cM) {
        File[] courseFiles = new File(inputPath).listFiles();
        for (File i : courseFiles) {
            try{
                readCourse(i, cM);
            } catch (FileNotFoundException | WrongFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
