package com.courseoutlinemanager.common.output;

import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.coursecondition.CourseCondition;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.assessment.Assessment;
import com.courseoutlinemanager.common.input.ConsoleInput;

import static com.courseoutlinemanager.common.ProcessString.*;

import java.util.ArrayList;

public class ConsoleOutput {
    private static final int WIDTH = 100;
    private static final int INDENT_SPACE = 4;

    public static int getWidth() {
        return WIDTH;
    }

    public static int getIndentSpace() {
        return INDENT_SPACE;
    }

    public static void printCourse(Course course) {
        System.out.println(printLabel("COURSE INFORMATION", "=", WIDTH));
        System.out.println(printLine("1. Course Name: " + course.getCourseName(), 1, WIDTH, INDENT_SPACE));
        System.out.println(printLine("2. Course Code: " + course.getCourseCode(), 1, WIDTH, INDENT_SPACE));
        System.out.println(printLine("3. Knowledge block: " + course.getKnowledgeBlock(), 1, WIDTH, INDENT_SPACE));
        System.out.println(printLine("4. Credits: " + course.getCourseCredits(), 1, WIDTH, INDENT_SPACE));
        System.out.println(printLine("5. Course Code: " + course.getCourseDescription(), 1, WIDTH, INDENT_SPACE));

        System.out.println(printLabel("", "=", WIDTH));
    }

    public static void printCourseOutline(CourseOutline courseOutline) {
        System.out.println(printLabel("OPEN UNIVERSITY", " ", WIDTH));
        System.out.println(printLabel("---------------", " ", WIDTH));
        System.out.println(printLabel("Course Outline", "=", WIDTH));
        System.out.println(printLine("I. General information", 0, WIDTH, INDENT_SPACE));
        System.out.println(
                printLine("1. Course Name: " + courseOutline.getCourse().getCourseName(), 1, WIDTH,
                        INDENT_SPACE));
        System.out.println(
                printLine("2. Course Code: " + courseOutline.getCourse().getCourseCode(), 1, WIDTH,
                        INDENT_SPACE));
        System.out.println(printLine("3. Belongs to the knowledge/skills block:) "
                + courseOutline.getCourse().getKnowledgeBlock().toString(), 1, WIDTH, INDENT_SPACE));
        System.out.println(printLine(
                "4. Number of credits: " + courseOutline.getCourse().getCourseCredits(), 1, WIDTH, INDENT_SPACE));
        System.out.println(printLine("5. In charge of the topic: ", 1, WIDTH, INDENT_SPACE));
        System.out.println(
                printLine("a. Lecturer " + courseOutline.getCompiler().getName(), 2, WIDTH, INDENT_SPACE));
        System.out.println(
                printLine("b. Id " + courseOutline.getCompiler().getId(), 2, WIDTH, INDENT_SPACE));
        System.out.println(printLine("II. Information about the subject", 0, WIDTH, INDENT_SPACE));
        System.out.println(
                printLine("1. Subject description: " + courseOutline.getCourse().getCourseDescription(), 1,
                        WIDTH, INDENT_SPACE));
        System.out.println(printLine("2. Requirements:", 1, WIDTH, INDENT_SPACE));

        for (CourseCondition requirements : courseOutline.getCourse().getRequirementList()) {
            System.out.println(
                    printLine("- " + requirements.getTypeName() + ":", 2, WIDTH, INDENT_SPACE));
            for (Course i : requirements.getCourseList()) {
                System.out.println(printTable(i.getCourseName(), i.getCourseCode(), 3, WIDTH, INDENT_SPACE));
            }
        }
        System.out.println(printLine("3. Subject objectives:", 1, WIDTH, INDENT_SPACE));
        for (String i : courseOutline.getCourseObjectiveList()) {
            System.out.println(printLine("- " + i, 2, WIDTH, INDENT_SPACE));
        }
        System.out.println(printLine("4. Subject outcome standards: ", 1, WIDTH, INDENT_SPACE));
        for (String i : courseOutline.getLearningOutcomeList()) {
            System.out.println(printLine("- " + i, 2, WIDTH, INDENT_SPACE));
        }
        System.out.println(printLine("5. Subject content:", 1, WIDTH, INDENT_SPACE));
        for (String i : courseOutline.getCourseContent()) {
            System.out.println(printLine("- " + i, 2, WIDTH, INDENT_SPACE));
        }
        System.out.println(printLine("6. Student assessment: ", 1, WIDTH, INDENT_SPACE));
        System.out.println(printTable("Assessing type", "Assessing method", "Weight", 2, WIDTH, INDENT_SPACE));
        for (Assessment i : courseOutline.getGradeList()) {
            System.out.println(
                    printTable(i.getAssessingType().toString(), i.getAssessingMethod().toString(),
                            String.valueOf(i.getWeight()), 3, WIDTH, INDENT_SPACE));
        }
        System.out.println(printLabel("", "=", WIDTH));
    }

    public static void printMainMenu() {
        String[] text = {
                "1. Create a course outline.",
                "2. Read custom input data.",
                "3. Edit information of a course outline.",
                "4. Find course.",
                "5. Find all courses which this course is part of it's requirements.",
                "6. Sort course outline list.",
                "7. Find the course outlines of a lecturer by Id.",
                "8. Export a full course outline.",
                "9. Show statistic on the number of course outline by its number of credits"
        };
        printChoiceMenu("MAIN MENU", text);
    }

    public static void printExportMenu() {
        String[] text = {
                "1. Export to console.",
                "2. Export to .txt file."
        };
        printChoiceMenu("EXPORT COURSE OUTLINE", text);
    }

    public static void printEditOutlineMenu(CourseOutline outline) {
        String[] menu = {
                "1. Course.",
                "2. Course description.",
                "3. Knowledge block.",
                "4. Compiler.",
                "5. Course Objectives.",
                "6. Learning outcomes.",
                "7. Course content.",
                "8. Requirements.",
                "9. Learning outcomes.",
                "10. Grades."
        };
        printChoiceMenu(
                "EDIT " + outline.getCourse().getCourseName() + " (" + outline.getCourse().getCourseCode()
                        + ")'s outline",
                menu);
    }

    public static void printChoiceMenu(String label, String[] text) {
        System.out.println(printLabel(label, "=", WIDTH));
        for (int i = 0; i < text.length; i++) {
            System.out.println(printLine(text[i], 0, WIDTH, INDENT_SPACE));
        }
        System.out.println(printLine("0. Exit.", 0, WIDTH, INDENT_SPACE));
        System.out.println(printLabel("", "=", WIDTH));
    }

    public static void printChoiceMenu(String label, ArrayList<String> texts) {
        System.out.println(printLabel(label, "=", WIDTH));
        int i = 1;
        for (String text : texts) {
            System.out.println(printLine(i + ". " + text, 0, WIDTH, INDENT_SPACE));
        }
        System.out.println(printLine("0. Exit.", 0, WIDTH, INDENT_SPACE));
        System.out.println(printLabel("", "=", WIDTH));
    }

    public static String takeUserInput(String message) {
        String res;
        System.out.print("Enter " + message + ": ");
        res = ConsoleInput.sc.nextLine();
        System.out.println();
        return res;
    }

    public static int takeUserInput(String message, int minVal, int maxVal) {
        int res;
        while (true) {
            try {
                System.out.print("Enter " + message + ": ");
                res = Integer.parseInt(ConsoleInput.sc.nextLine());
                if (res < minVal || res > maxVal)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please re-enter.\n");
            }
        }
        System.out.println();
        return res;
    }

    public static void main(String[] args) {

    }
}
