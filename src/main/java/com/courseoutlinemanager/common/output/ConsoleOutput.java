package com.courseoutlinemanager.common.output;

import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.coursecondition.CourseCondition;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.lecturer.Lecturer;
import com.courseoutlinemanager.assessment.Assessment;
import com.courseoutlinemanager.common.input.ConsoleInput;

import static com.courseoutlinemanager.common.ProcessString.*;

import java.math.BigDecimal;
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
        System.out.println(printLabel("COURSE INFORMATION", "="));

        System.out.println(printLine("1. Course Name: " + course.getCourseName(), 1));
        System.out.println(printLine("2. Course Code: " + course.getCourseCode(), 1));
        System.out.println(printLine("3. Knowledge block: " + course.getKnowledgeBlock(), 1));
        System.out.println(printLine("4. Credits: " + course.getCourseCredits(), 1));
        System.out.println(printLine("5. Description: " + course.getCourseDescription(), 1));
        System.out.println(printLine("6. Requirements: ", 1));
        for (CourseCondition requirement : course.getRequirementList()) {
            System.out.println(printLine("- " + requirement.getTypeName() + ":", 2));
            if (requirement.isEmpty()) {
                System.out.println(printLine("NONE", 5));
                continue;
            }
            for (Course i : requirement.getCourseList()) {
                System.out.println(printLine(i.toString(), 3));
            }
        }
        System.out.println(printLabel("", "="));
    }

    public static void printLecturer(Lecturer lecturer) {
        System.out.println(printLabel("LECTURER INFORMATION", "="));
        System.out.println(printLine("1. Lecturer Name: " + lecturer.getName(), 1));
        System.out.println(printLine("2. Lecturer Code: " + lecturer.getId(), 1));
        System.out.println(printLabel("", "="));
    }

    public static void printCourseOutLine(CourseOutline courseOutline) {
        System.out.println(printLabel("OPEN UNIVERSITY", " "));
        System.out.println(printLabel("---------------", " "));
        printLecturer(courseOutline.getCompiler());
        printCourse(courseOutline.getCourse());
        System.out.println(printLine("7. Education system: " + courseOutline.getEducationalSystem().getTypeName(), 1));

        System.out.println(printLine("8. Subject objectives:", 1));
        for (String i : courseOutline.getCourseObjectiveList()) {
            System.out.println(printLine("- " + i, 2));
        }
        System.out.println(printLine("9. Subject outcome standards: ", 1));
        for (String i : courseOutline.getLearningOutcomeList()) {
            System.out.println(printLine("- " + i, 2));
        }
        System.out.println(printLine("10. Subject content:", 1));
        for (String i : courseOutline.getCourseContent()) {
            System.out.println(printLine("- " + i, 2));
        }
        System.out.println(printLine("11. Student assessment: ", 1));
        System.out.println(printTable("Assessing type", "Assessing method", "Weight", 2));
        for (Assessment i : courseOutline.getGradeList()) {
            System.out.println(
                    printTable(i.getAssessingType().toString(), i.getAssessingMethod().toString(),
                            String.valueOf(i.getWeight()), 3));
            printLine(i.getContent(), 3);
        }
        System.out.println(printLabel("", "="));

    }

    public static void printMainMenu() {
        String[] text = {
                "Create a course outline.",
                "Read custom input data.",
                "Edit information of a course outline.",
                "Find course.",
                "Find all courses which this course is part of their requirements.",
                "Sort course outline list.",
                "Find the course outlines of a lecturer by Id.",
                "Export course outline.",
                "Show statistics on the number of outlines according to the number of credits.",
                "Create lecturer",
                "Create course",
                "Delete outline"
        };
        printChoiceMenu("MAIN MENU", text);
    }

    public static void optionToExportMenu() {
        String[] text = {
                "Lecturer",
                "Course OutLine Manager",
        };
        printChoiceMenu("EXPORT COURSE OUTLINE", text);
    }

    public static void printExportMenu() {
        String[] text = {
                "Export 1 outline to console.",
                "Export all outlines to console.",
                "Export all outlines to .txt file."
        };
        printChoiceMenu("EXPORT COURSE OUTLINE", text);
    }

    public static void printEditOutlineMenu(CourseOutline outline) {
        String[] menu = {
                "Course Objectives.",
                "Learning outcomes.",
                "Course content.",
                "Requirements.",
                "Grades."
        };
        printChoiceMenu("EDIT " + outline.toString() + "'s outline", menu);
    }

    public static void printChoiceMenu(String label, String[] text) {
        System.out.println(printLabel(label, "="));
        for (int i = 0; i < text.length; i++) {
            System.out.println(printLine((i + 1) + ". " + text[i], 0));
        }
        System.out.println(printLine("0. Exit.", 0));
        System.out.println(printLabel("", "="));
    }

    public static void printChoiceMenu(String label, ArrayList<String> texts) {
        System.out.println(printLabel(label, "="));
        int i = 1;
        for (String text : texts) {
            System.out.println(printLine(i++ + ". " + text, 0));
        }
        System.out.println(printLine("0. Exit.", 0));
        System.out.println(printLabel("", "="));
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
                // If the input value is not a number or not in valid range.
                res = Integer.parseInt(ConsoleInput.sc.nextLine().trim());
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

    public static double takeUserInput(String message, String minVal, String maxVal) throws NumberFormatException {
        BigDecimal min = new BigDecimal(minVal);
        BigDecimal max = new BigDecimal(maxVal);
        BigDecimal tempRes;
        while (true) {
            try {
                System.out.print("Enter " + message + ": ");
                // If the input value is not a number or not in valid range.
                tempRes = new BigDecimal(ConsoleInput.sc.nextLine().trim());
                if (tempRes.compareTo(min) < 0 || tempRes.compareTo(max) > 0)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Invalid input! Please re-enter.\n");
            }
        }
        System.out.println();
        return tempRes.doubleValue();
    }

    public static void printOutlinesOfLecturerMenu(Lecturer lect) {
        ArrayList<CourseOutline> outlineList = lect.getCourseOutlineList();
        System.out.println(printLabel(lect + "'s outline list", "="));
        for (int i = 0; i < outlineList.size(); i++) {
            System.out.println(printLine((i + 1) + ". " + outlineList.get(i), 0));
        }
        System.out.println(printLine("0. Exit.", 0));
        System.out.println(printLabel("", "="));
    }
}
