package com.courseoutlinemanager.common.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.coursecondition.CourseCondition;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import static com.courseoutlinemanager.common.ProcessString.*;

public class FileOutput {
    private static final int WIDTH = 100;
    private static final int INDENT_SPACE = 4;

    public static void writeOutLine(CourseOutline courseOutline, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(printLabel("Open University", " ", WIDTH));
            writer.write(printLabel("---------------", " ", WIDTH));
            writer.write(printLabel("Course Outline", "=", WIDTH));
            writer.write(printLine("\nI.General information", 0, WIDTH, INDENT_SPACE));
            writer.write(printLine("\n1.Course Name: " + courseOutline.getCourse().getCourseName(),
                    1, WIDTH, INDENT_SPACE));
            writer.write(printLine("\n2.Course Code: " + courseOutline.getCourse().getCourseCode(),
                    1, WIDTH, INDENT_SPACE));
            writer.write(
                    printLine(
                            "\n3.Belongs to the knowledge/skills block: "
                                    + courseOutline.getCourse().getKnowledgeBlock().toString(),
                            1, WIDTH, INDENT_SPACE));
            writer.write(printLine(
                    "\n4.Number of credits: " + courseOutline.getCourse().getCourseCredits(), 1, WIDTH, INDENT_SPACE));
            writer.write(printLine("\n5.In charge of the topic: ", 1, WIDTH, INDENT_SPACE));
            writer.write(
                    printLine("\na.Lecturer " + courseOutline.getCompiler().getName(), 2, WIDTH, INDENT_SPACE));
            writer.write(
                    printLine("\nb.Id " + courseOutline.getCompiler().getId(), 2, WIDTH, INDENT_SPACE));
            writer.write(printLine("\nII.Information about the subject", 0, WIDTH, INDENT_SPACE));
            writer.write(
                    printLine("\n1.Subject description: " + courseOutline.getCourse().getCourseDescription(), 1, WIDTH,
                            INDENT_SPACE));
            writer.write(printLine("\n2. Requirements:", 1, WIDTH, INDENT_SPACE));
            writer.write(
                    printLine("\n1.Subject description: " + courseOutline.getCourse().getCourseDescription(), 1, WIDTH,
                            INDENT_SPACE));

            for (CourseCondition requirements : courseOutline.getCourse().getRequirements()) {
                writer.write(
                        printLine("- " + requirements.getTypeName() + ":", 2, WIDTH, INDENT_SPACE));
                for (Course i : requirements.getCourses()) {
                    writer.write(printTable(i.getCourseName(), i.getCourseCode(), 3, WIDTH, INDENT_SPACE));
                }
            }
            writer.write(printLine("\n3.Subject objectives: ", 1, WIDTH, INDENT_SPACE));

            for (String i : courseOutline.getCourseObjectives()) {
                writer.write(printLine("-" + i, 2, WIDTH, INDENT_SPACE));

            }
            writer.write(printLine("4.Subject outcome standards: ", 1, WIDTH, INDENT_SPACE));

            for (String i : courseOutline.getLearningOutcomes()) {
                writer.write(printLine("-" + i, 2, WIDTH, INDENT_SPACE));
            }
            writer.write(printLine("5.Subject content: ", 1, WIDTH, INDENT_SPACE));
            for (String i : courseOutline.getCourseContent()) {
                writer.write(printLine("-" + i, 2, WIDTH, INDENT_SPACE));
            }
            writer.write(printLine("5.Subject evaluation: ", 1, WIDTH, INDENT_SPACE));

            writer.write(printLabel("", "=", WIDTH));
        }
    }

}