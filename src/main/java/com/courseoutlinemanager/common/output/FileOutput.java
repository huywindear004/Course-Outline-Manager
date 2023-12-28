import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.coursecondition.CourseCondition;
import com.courseoutlinemanager.courseoutline.CourseOutline;

public class FileOutput {
    public static void writeOutLine(CourseOutline courseOutline, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // writer.write("\nI. General information");

            // writer.write("\n\t1. Course Code: " +
            // courseOutline.getCourse().getCourseCode());

            // writer.write("\n\t2. Course Name: " +
            // courseOutline.getCourse().getCourseName());

            // writer.write("\n\t3. Belongs to the knowledge/skills block: " +
            // courseOutline.getCourse().getKnowledgeBlock());

            // writer.write("\n\t4. Number of credits: " +
            // courseOutline.getCourse().getCourseCredits());

            // writer.write("\n\t5. In charge of the topic: ");

            // writer.write("\n\t\ta.Lecture: " + courseOutline.getCompiler().getName());

            // writer.write("\n\t\tb.Id: " + courseOutline.getCompiler().getId());

            // writer.write("\n\tII.Information about the subject");
            // writer.write("\n\t\t"1.Subject description: "");

            writer.write(ConsoleOutput.printLabel("Open University", " "));
            writer.write(printLabel("---------------", " "));
            writer.write(printLabel("Course Outline", "="));
            writer.write(printLine("I.General information", 0));
            writer.write(printLine("1.Course Name: " + courseOutline.getCourse().getCourseName(), 1));
            writer.write(printLine("2.Course Code: " + courseOutline.getCourse().getCourseCode(), 1));
            writer.write(printLine("3.Belongs to the knowledge/skills block: "
                    + courseOutline.getCourse().getKnowledgeBlock().getTypeName(), 1));
            writer.write(printLine("4.Number of credits: " + courseOutline.getCourse().getCourseCredits(), 1));
            writer.write(printLine("5.In charge of the topic: ", 1));
            writer.write(printLine("a.Lecturer " + courseOutline.getCompiler().getName(), 2));
            writer.write(printLine("b.Id " + courseOutline.getCompiler().getId(), 2));
            writer.write();
            printLine("II.Information about the subject", 0);
            writer.write(printLine("1.Subject description: " + courseOutline.getCourse().getCourseDescription(), 1));
            writer.write(printLine("2. Requirements:", 1));

            for (CourseCondition requirements : courseOutline.getCourse().getRequirements()) {
                printLine("- " + requirements.getTypeName() + ":", 2);
                for (Course i : requirements.getCourses()) {
                    writer.write(printTable(i.getCourseName(), i.getCourseCode(), 3));
                }
            }
            printLine("3.Subject objectives: ", 1);
            for (String i : courseOutline.getCourseObjectives()) {
                writer.write(printLine("-" + i, 2));
            }
            printLine("4.Subject outcome standards: ", 1);
            for (String i : courseOutline.getLearningOutcomes()) {
                writer.write(printLine("-" + i, 2));
            }

            printLine("5.Subject content: ", 1);
            for (String i : courseOutline.getCourseContent()) {
                writer.write(printLine("-" + i, 2));
            }
            writer.write(printLine("5.Subject evaluation: ", 1));

            writer.write(printLabel("", "="));
        }
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
        // printLabel("", "="); rồi đó. giờ ghi file thì append cái hàm đó vào, in
        // console thì SOUT ra
        System.out.println();
        System.out.println();
        System.out.println();
        printcourse();
    }
}