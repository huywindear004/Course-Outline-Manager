package com.courseoutlinemanager.common;

import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.coursecondition.CourseCondition;
import com.courseoutlinemanager.courseoutline.CourseOutline;

public class Output {
    private static final int WIDTH = 90;
    private static final int INDENT_SPACE = 4;

    // public static void generalInformation() {
    // System.out.println("I. Thông tin tổng quát");
    // System.out.println("\t1. Tên môn học tiếng Việt: + Course.name");
    // System.out.println("\t2. Thuộc khối kiến thức/kỹ năng : + Course.block");
    // System.out.println("\t3. Số tín chỉ");
    // System.out.println("\t4. Phụ trách môn học");
    // }

    // public static void courseInformation() {
    // System.out.println("I. Thông tin về môn học");
    // System.out.println("\t1. Mô tả môn học ");
    // System.out.println("\t2. Môn học điều kiện");
    // System.out.println("\t\t1. Môn tiên quyết");
    // System.out.println("\t\t2. Môn học trước");
    // System.out.println("\t3. Mục tiêu môn học");
    // System.out.println("\t4. Chuẩn đầu ra (CĐR) môn học");
    // System.out.println("\t5. Đánh giá môn học");
    // }

    public static void printLabel(String label, String character) {
        int halfLineLength = (WIDTH - label.length()) / 2;
        String horizontialLine = character.repeat(halfLineLength);
        System.out.println(horizontialLine + label + horizontialLine);
    }

    public static void printLine(String text, int indent) {
        String indentString = " ".repeat(INDENT_SPACE * indent);
        int remainLength = WIDTH - 2 - text.length() - indentString.length();
        System.out.println("|" + indentString + text + " ".repeat(remainLength) + "|");
    }

    public static void printTable(String startText, String endText, int indent) {
        String indentString = " ".repeat(INDENT_SPACE * indent);
        int remainLength = WIDTH - 2 - startText.length() - indentString.length() - endText.length();
        System.out.println("|" + indentString + startText + " ".repeat(remainLength) + endText + "|");
    }

    // public static void printcourse(CourseOutline courseOutline) {
    // printLabel("Open University", " ");
    // printLabel("---------------", " ");
    // printLabel("Course Outline", "=");
    // printLine("I.General information", 0);
    // printLine("1.Course Name: " + courseOutline.getCourse().getCourseName(), 1);
    // printLine("2.Course Code: " + courseOutline.getCourse().getCourseCode(), 1);
    // printLine("3.Belongs to the knowledge/skills block: " +
    // courseOutline.getCourse().getKnowledgeBlock(), 1);
    // printLine("4.Number of credits: " +
    // courseOutline.getCourse().getCourseCredits(), 1);
    // printLine("5.In charge of the topic: " +
    // courseOutline.getCourse().getCourseCredits(), 1);
    // printLine("a.Lecture " + courseOutline.getCompiler().getName(), 2);
    // printLine("a.Id " + courseOutline.getCompiler().getId(), 2);
    // printLine("II.Information about the subject", 0);
    // printLine("1.Subject description: " +
    // courseOutline.getCourse().getCourseDescription(), 1);
    // printLine("2.Subject conditions: " +
    // courseOutline.getCourse().getCourseDescription(), 1);

    // for (CourseCondition requirements :
    // courseOutline.getCourse().getRequirements()) {
    // printLine("-" + requirements.getTypeName() + ":", 2);
    // for (Course i : requirements.getCourses()) {
    // printTable(i.getCourseName(), i.getCourseCode(), 3);
    // }
    // }
    // printLine("3.Subject objectives: ", 1);
    // for (String i : courseOutline.getCourseObjectives()) {
    // printLine("-" + i, 2);
    // }
    // printLine("4.Subject outcome standards: ", 1);
    // for (String i : courseOutline.getLearningOutcomes()) {
    // printLine("-" + i, 2);
    // }

    // printLine("5.Subject content: ", 1);
    // for (String i : courseOutline.getCourseContent()) {
    // printLine("-" + i, 2);
    // }
    // printLine("5.Subject evaluation: ", 1);

    // printLabel("", "=");
    // }
    public static void printcourse() {
        printLabel("Open University", " ");
        printLabel("---------------", " ");
        printLabel("Course Outline", "=");
        printLine("I.General information", 0);
        printLine("1.Course Name: " + "OOP", 1);
        printLine("2.Course Code: " + "ITEC7392", 1);
        printLine("3.Belongs to the knowledge/skills block: " + "BASIC", 1);
        printLine("4.Number of credits: " + 3, 1);
        printLine("5.In charge of the topic: ", 1);
        printLine("a.Lecture " + "Pham Dinh Duong", 2);
        printLine("a.Id " + "749494", 2);
        printLine("II.Information about the subject", 0);
        printLine("1.Subject description: ", 1);
        printLine("2.Subject conditions: ", 1);

        // for (CourseCondition requirements :
        // courseOutline.getCourse().getRequirements()) {
        // printLine("-" + requirements.getTypeName() + ":", 2);
        // for (Course i : requirements.getCourses()) {
        // printTable(i.getCourseName(), i.getCourseCode(), 3);
        // }
        // }
        printLine("3.Subject objectives: ", 1);
        // for (String i : courseOutline.getCourseObjectives()) {
        printLine("qua mon", 2);
        // }
        printLine("4.Subject outcome standards: ", 1);
        // for (String i : courseOutline.getLearningOutcomes()) {
        printLine("qua mon", 2);
        // }

        printLine("5.Subject content: ", 1);
        // for (String i : courseOutline.getCourseContent()) {
        printLine("qua mon", 2);
        // }
        printLine("5.Subject evaluation: ", 1);

        printLabel("", "=");
    }

    public static void printMainMenu() {
        printLabel("MAIN MENU", "=");
        printLine("1. Create a course outline.", 0);
        printLine("2. Change information of a course outline.", 0);
        printLine("3. Find course.", 0);
        printLine("4. Find all courses which this course is part of it's requirements.", 0);
        printLine("5. Sort course outline list in descending order by number of credits,", 0);
        printLine("if ", 0);
        printLine("", 0);
        printLine("", 0);
        printLine("", 0);
        printLine("", 0);

    }

    public static void main(String[] args) {
        // printLabel("De cuong mon hoc", "=");
        // printLine("Course name: OOP", 0);
        // printLine("Course code: ITEC2504", 0);
        // printLine("Mon hoc truoc: ", 0);
        // printLine("Ky thuat lap trinh", 1);
        // printLine("Ky thuat lap trinh", 1);
        // printLine("Ky thuat lap trinh", 1);
        // printLine("Ky thuat lap trinh", 1);
        // printLabel("", "=");
        System.out.println();
        System.out.println();
        System.out.println();
        printcourse();
    }

}
