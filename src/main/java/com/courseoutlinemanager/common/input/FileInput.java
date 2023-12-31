package com.courseoutlinemanager.common.input;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.common.customexception.AlreadyExistException;
import com.courseoutlinemanager.common.customexception.NotFoundException;
import com.courseoutlinemanager.common.customexception.OutOfCapacityException;
import com.courseoutlinemanager.common.customexception.WrongFormatException;
import com.courseoutlinemanager.common.output.ConsoleOutput;
import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.CourseManager;
import com.courseoutlinemanager.course.knowledgeblock.KnowledgeBlock;

import static com.courseoutlinemanager.common.ProcessString.printLabel;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FileInput {
    private static Scanner fileScanner;

    /**
     * Get the course by reading file
     * 
     * @throws FileNotFoundException
     * @throws NotFoundException
     *                               If couldn't find educationalSystem or
     *                               knowledgeBlock
     * @throws WrongFormatException
     */
    public static Course getCourse(File input, CourseManager cM)
            throws FileNotFoundException, NotFoundException, WrongFormatException {
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
                } catch (NotFoundException ignored) {}
            } else if (ProcessString.equalsByAlphabet(temp[0], "CourseName")) {
                newCourse.setCourseName(temp[1].trim());
            } else if (ProcessString.equalsByAlphabet(temp[0], "KnowledgeBlock")) {
                newCourse.setKnowledgeBlock(KnowledgeBlock.valueOf(temp[1].trim().toUpperCase()));
            } else if (ProcessString.equalsByAlphabet(temp[0], "Credits")) {
                newCourse.setCourseCredits(Integer.parseInt(temp[1].trim()));
            } else if (ProcessString.equalsByAlphabet(temp[0], "Description")) {
                newCourse.setCourseDescription(temp[1].trim());
            } else if (ProcessString.equalsByAlphabet(temp[0], "PrerequisiteCourse")
                    || ProcessString.equalsByAlphabet(temp[0], "previousCourse")) {
                // typeOfRequirement will store the type of requirements(previousCourse, PrerequisiteCourse, ...)
                String typeOfRequirements = temp[0];
                // temp[] would contains courseStrings
                temp = temp[1].split("[,;]+");
                // This loop add requirements to newCourse step-by-step
                for (String courseString : temp) {
                    // split courseString to get courseCode and courseName
                    String[] nameAndCode = courseString.trim().split("[()_-]+");
                    //tempCourse is the course that is gonna be added to requirement
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
                    } catch (OutOfCapacityException | AlreadyExistException e) {
                        System.out.println(e.getMessage() + " in " + newCourse + "{" + input.getName() + "}");
                        isAddableCourse = false;
                    }

                    if (isAddableCourse)
                        cM.addCourse(tempCourse);
                }
            } else
                throw new WrongFormatException("The input format of " + input.getName() + " is wrong.");
        }
        
        if(!cM.containsCourse(newCourse))
            cM.addCourse(newCourse);
        return newCourse;
    }

    

    public static void main(String[] args) throws FileNotFoundException, NotFoundException {
        CourseManager cM = new CourseManager();

        String path = "./input/courses/";
        System.out.print("\033[H\033[2J");
        System.out.flush();
        File[] inputs = new File(path).listFiles();
        for (File i : inputs) {
            try {
                ConsoleOutput.printCourse(getCourse(i, cM));
            } catch (FileNotFoundException | NotFoundException | WrongFormatException e) {
                System.out.println(printLabel(e.getMessage(), "!", 90));
            }
        }
        System.out.println();
        System.out.println();


        for(Course i : cM.getCourseList()){
            System.out.println(i.toString());
        }
    }
}
