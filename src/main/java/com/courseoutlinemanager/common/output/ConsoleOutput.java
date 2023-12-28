package com.courseoutlinemanager.common.output;

import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.coursecondition.CourseCondition;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.assessment.Assessment;


import static com.courseoutlinemanager.common.ProcessString.*;


public class ConsoleOutput {
    private static final int WIDTH = 100;
    private static final int INDENT_SPACE = 4;

    public static void printCourseOutline(CourseOutline courseOutline) { 
        System.out.println(printLabel("Open University", " ", WIDTH)); 
        System.out.println(printLabel("---------------", " ", WIDTH)); 
        System.out.println(printLabel("Course Outline", "=", WIDTH)); 
        System.out.println(printLine("I. General information", 0, WIDTH , INDENT_SPACE)); 
        System.out.println(printLine("1. Course Name: " + courseOutline.getCourse().getCourseName(), 1, WIDTH , INDENT_SPACE)); 
        System.out.println(printLine("2. Course Code: " + courseOutline.getCourse().getCourseCode(), 1, WIDTH , INDENT_SPACE)); 
        System.out.println(printLine("3. Belongs to the knowledge/skills block: " + courseOutline.getCourse().getKnowledgeBlock().getTypeName(), 1, WIDTH , INDENT_SPACE));
        System.out.println(printLine("4. Number of credits: " + courseOutline.getCourse().getCourseCredits(), 1, WIDTH , INDENT_SPACE)); 
        System.out.println(printLine("5. In charge of the topic: ", 1, WIDTH , INDENT_SPACE)); 
        System.out.println(printLine("a. Lecturer " + courseOutline.getCompiler().getName(), 2, WIDTH , INDENT_SPACE)); 
        System.out.println(printLine("b. Id " + courseOutline.getCompiler().getId(), 2, WIDTH , INDENT_SPACE)); 
        System.out.println(printLine("II. Information about the subject", 0, WIDTH , INDENT_SPACE));
        System.out.println(printLine("1. Subject description: " + courseOutline.getCourse().getCourseDescription(),  1,WIDTH , INDENT_SPACE));
        System.out.println(printLine("2. Requirements:", 1, WIDTH , INDENT_SPACE));
        
        for (CourseCondition requirements : courseOutline.getCourse().getRequirements()) {
            System.out.println(printLine("- " + requirements.getTypeName() + ":", 2,WIDTH , INDENT_SPACE));
            for (Course i : requirements.getCourses()) {
               System.out.println( printTable(i.getCourseName(), i.getCourseCode(), 3, WIDTH , INDENT_SPACE));
            }
        }
        System.out.println(printLine("3. Subject objectives:", 1,WIDTH , INDENT_SPACE));
        for (String i : courseOutline.getCourseObjectives()) {
            System.out.println(printLine("- " + i, 2,WIDTH , INDENT_SPACE));
        }
        System.out.println(printLine("4. Subject outcome standards: ", 1,WIDTH , INDENT_SPACE));
        for (String i : courseOutline.getLearningOutcomes()) {
            System.out.println(printLine("- " + i, 2,WIDTH , INDENT_SPACE));
        }
        System.out.println(printLine("5. Subject content:", 1,WIDTH , INDENT_SPACE));
        for (String i : courseOutline.getCourseContent()) {
            System.out.println(printLine("- " + i, 2,WIDTH , INDENT_SPACE));
        }
        System.out.println(printLine("6. Student assessment: ", 1,WIDTH , INDENT_SPACE));
        System.out.println(printTable("Assessing type", "Assessing method", "Weight",2,WIDTH,INDENT_SPACE));
        for (Assessment i : courseOutline.getGrades()) {
            System.out.println(
                    printTable(i.getAssessingType().toString(), i.getAssessingMethod().toString(), String.valueOf(i.getWeight()), 3, WIDTH, INDENT_SPACE)); 
        }
        System.out.println( printLabel("", "=", WIDTH));
    }

    // public static void printcourse() {
    //     printLabel("Open University", " ");
    //     printLabel("---------------", " ");
    //     printLabel("Course Outline", "=");
    //     printLine("I.General information", 0);
    //     printLine("1.Course Name: " + "OOP", 1);
    //     printLine("2.Course Code: " + "ITEC7392", 1);
    //     printLine("3.Belongs to the knowledge/skills block: " + "BASIC", 1);
    //     printLine("4.Number of credits: " + 3, 1);
    //     printLine("5.In charge of the topic: ", 1);
    //     printLine("a.Lecture " + "Pham Dinh Duong", 2);
    //     printLine("a.Id " + "749494", 2);
    //     printLine("II.Information about the subject", 0);
    //     printLine("1.Subject description: ", 1);
    //     printLine("2.Subject conditions: ", 1);

    //     // for (CourseCondition requirements :
    //     // courseOutline.getCourse().getRequirements()) {
    //     // printLine("-" + requirements.getTypeName() + ":", 2);
    //     // for (Course i : requirements.getCourses()) {
    //     // printTable(i.getCourseName(), i.getCourseCode(), 3);
    //     // }
    //     // }
    //     printLine("3.Subject objectives: ", 1);
    //     // for (String i : courseOutline.getCourseObjectives()) {
    //     printLine("qua mon", 2);
    //     // }
    //     printLine("4.Subject outcome standards: ", 1);
    //     // for (String i : courseOutline.getLearningOutcomes()) {
    //     printLine("qua mon", 2);
    //     // }

    //     printLine("5.Subject content: ", 1);
    //     // for (String i : courseOutline.getCourseContent()) {
    //     printLine("qua mon", 2);
    //     // }
    //     printLine("5.Subject evaluation: ", 1);

    //     printLabel("", "=");
    // }

    public static void printMainMenu() {
        System.out.println(printLabel("MAIN MENU", "=", WIDTH)); 
        System.out.println(printLine("1. Create a course outline.", 0,WIDTH , INDENT_SPACE)); 
        System.out.println(printLine("2. Change information of a course outline.", 0,WIDTH , INDENT_SPACE)); 
        System.out.println(printLine("3. Find course.", 0,WIDTH , INDENT_SPACE)); 
        System.out.println();
        printLine("4. Find all courses which this course is part of it's requirements.", 0, WIDTH, INDENT_SPACE);
        // sort in decending order by the number of credits, if the number of credits is the same
        // sort in ascending order by the course code
        System.out.println(printLine("5. Sort course outline list.", 0,WIDTH , INDENT_SPACE)); 
        System.out.println(printLine("6. Find the course outlines of a lecturer by Id.", 0,WIDTH , INDENT_SPACE)); 
        System.out.println(printLine("7. Export a full course outline.", 0,WIDTH , INDENT_SPACE)); 
        System.out.println(printLine("8. Show statistic on the number of course outline by its number of credits", 0,WIDTH , INDENT_SPACE)); 
        System.out.println(printLine("0. Exit.", 0,WIDTH , INDENT_SPACE)); 
        System.out.println(printLabel("", "=", WIDTH)); 
    }  
}
