package com.courseoutlinemanager.common.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.courseoutlinemanager.assessment.Assessment;
import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.coursecondition.CourseCondition;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import static com.courseoutlinemanager.common.ProcessString.*;

public class FileOutput {

        public static void writeOutLine(CourseOutline courseOutline, String filePath) throws IOException {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                        writer.write(printLabel("Open University", " "));
                        writer.write("\n");
                        writer.write(printLabel("---------------", " "));
                        writer.write("\n");
                        writer.write(printLabel("Course Outline", "="));
                        writer.write("\n");
                        writer.write(printLine("I. General information", 0));
                        writer.write("\n");
                        writer.write(printLine("1. Course Name: " + courseOutline.getCourse().getCourseName(), 1));
                        writer.write("\n");
                        writer.write(printLine("2. Course Code: " + courseOutline.getCourse().getCourseCode(), 1));
                        writer.write("\n");
                        writer.write(printLine("3. Belongs to the knowledge/skills block: "
                                        + courseOutline.getCourse().getKnowledgeBlock().toString(), 1));
                        writer.write("\n");

                        writer.write(printLine("4. Number of credits: " + courseOutline.getCourse().getCourseCredits(),
                                        1));
                        writer.write("\n");
                        writer.write(printLine("5. In charge of the topic: ", 1));
                        writer.write("\n");
                        writer.write(printLine("a. Lecturer: " + courseOutline.getCompiler().getName(), 2));
                        writer.write("\n");
                        writer.write(printLine("b. Lecturer Id: " + courseOutline.getCompiler().getId(), 2));
                        writer.write("\n");
                        writer.write(printLine("II. Information about the subject", 0));
                        writer.write("\n");
                        writer.write(printLine(
                                        "1. Education system: " + courseOutline.getEducationalSystem().getTypeName(),
                                        1));
                        writer.write("\n");

                        writer.write(printLine(
                                        "2. Subject description: " + courseOutline.getCourse().getCourseDescription(),
                                        1));
                        writer.write("\n");

                        writer.write(printLine("3. Requirements:", 1));
                        writer.write("\n");

                        for (CourseCondition requirements : courseOutline.getCourse().getRequirementList()) {
                                writer.write(printLine("- " + requirements.getTypeName() + ":", 2));
                                writer.write("\n");

                                for (Course i : requirements.getCourseList()) {
                                        writer.write(printLine("" + i.toString(), 4));
                                        writer.write("\n");
                                }
                        }
                        writer.write(printLine("4. Subject objectives: ", 1));
                        writer.write("\n");
                        for (String i : courseOutline.getCourseObjectiveList()) {
                                writer.write(printLine("- " + i, 2));
                                writer.write("\n");

                        }
                        writer.write(printLine("5. Subject outcome standards: ", 1));
                        writer.write("\n");
                        for (String i : courseOutline.getLearningOutcomeList()) {
                                writer.write(printLine("- " + i, 2));
                                writer.write("\n");
                        }
                        writer.write(printLine("6. Subject content: ", 1));
                        writer.write("\n");
                        for (String i : courseOutline.getCourseContent()) {
                                writer.write(printLine("- " + i, 2));
                                writer.write("\n");
                        }
                        writer.write(printLine("7. Subject evaluation: ", 1));
                        writer.write("\n");
                        for (Assessment i : courseOutline.getGradeList()) {
                                writer.write(printLine("- " + i.toString(), 2));
                                writer.write("\n");
                        }
                        writer.write(printLabel("", "="));
                }
        }

        public static void writeOutlineList(ArrayList<CourseOutline> outlineList, String outputPath) {
                for (CourseOutline i : outlineList) {
                        try {
                                writeOutLine(i, outputPath + "/" + i.getCourse() + "_"
                                                + i.getEducationalSystem().getTypeName() + ".txt");
                        } catch (IOException e) {
                                System.out.println("Error: " + e.getMessage());
                        }
                }
        }

}