package com.courseoutlinemanager.common.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.courseoutlinemanager.assessment.Assessment;
import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.coursecondition.CourseCondition;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import static com.courseoutlinemanager.common.ProcessString.*;

public class FileOutput {
        private static final int WIDTH = 100;
        private static final int INDENT_SPACE = 4;

        public static void writeOutLine(CourseOutline courseOutline, String filePath) throws IOException {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                        writer.write(printLabel("Open University", " "));
                        writer.write(printLabel("---------------", " "));
                        writer.write(printLabel("Course Outline", "="));
                        writer.write(printLine("\nI.General information", 0));
                        writer.write(printLine("\n1.Course Name: " + courseOutline.getCourse().getCourseName(),1));
                        writer.write(printLine("\n2.Course Code: " + courseOutline.getCourse().getCourseCode(),1));
                        writer.write(printLine("\n3.Belongs to the knowledge/skills block: "+ courseOutline.getCourse().getKnowledgeBlock().toString(),1));
                        writer.write(printLine("\n4.Number of credits: " + courseOutline.getCourse().getCourseCredits(), 1));
                        writer.write(printLine("\n5.In charge of the topic: ", 1));
                        writer.write(printLine("\na.Lecturer " + courseOutline.getCompiler().getName(), 2));
                        writer.write(printLine("\nb.Id " + courseOutline.getCompiler().getId(), 2));
                        writer.write(printLine("\n\nII.Information about the subject", 0));
                        writer.write(printLine("\n1.Subject description: "+ courseOutline.getCourse().getCourseDescription(), 1));
                        writer.write(printLine("\n2. Requirements:", 1));
                        
                        for (CourseCondition requirements : courseOutline.getCourse().getRequirementList()) {
                                writer.write(printLine("\n- " + requirements.getTypeName() + ":", 2));
                                for (Course i : requirements.getCourseList()) {
                                        writer.write(printLine("\n"+i.toString(), 4));
                                } 
                        }
                        writer.write(printLine("\n3.Subject objectives: ", 1));

                        for (String i : courseOutline.getCourseObjectiveList()) {
                                writer.write(printLine("-" + i, 2));

                        }
                        writer.write(printLine("\n4.Subject outcome standards: ", 1));

                        for (String i : courseOutline.getLearningOutcomeList()) {
                                writer.write(printLine("-" + i, 2));
                        }
                        writer.write(printLine("\n5.Subject content: ", 1));
                        for (String i : courseOutline.getCourseContent()) {
                                writer.write(printLine("-" + i, 2));
                        }
                        writer.write(printLine("\n6.Subject evaluation: ", 1));
                        for (Assessment i : courseOutline.getGradeList()) {
                                writer.write(printLine("- " + i.toString(),2));
                        }

                        writer.write(printLabel("", "="));
                }
        }

}