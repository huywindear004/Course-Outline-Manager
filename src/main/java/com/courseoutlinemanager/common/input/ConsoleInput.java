package com.courseoutlinemanager.common.input;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.courseoutlinemanager.common.customexception.NotFoundException;
import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.CourseManager;
import com.courseoutlinemanager.course.knowledgeblock.KnowledgeBlockManager;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.educationalsystem.EducationalSystem;
import com.courseoutlinemanager.educationalsystem.EducationalSystemManager;
import com.courseoutlinemanager.educationalsystem.FormalEducation;
import com.courseoutlinemanager.educationalsystem.TransferEducation;

import static com.courseoutlinemanager.common.ProcessString.*;
import static com.courseoutlinemanager.common.output.ConsoleOutput.*;

public class ConsoleInput {
    public static Scanner sc = new Scanner(System.in);

    private static String srcPath = "./src/main/java/com/courseoutlinemanager/";

    public static Course createCourse(EducationalSystemManager eSM, CourseManager cM, KnowledgeBlockManager kBM) {
        printLabel("CREATE COURSE", "=", getWidth());
        System.out.print("Enter course name: ");
        Course newCourse = new Course(sc.nextLine());



        return newCourse;
    }

    // public static CourseOutline createCourseOutline() {

    // }

    public static void main(String[] args) {

    }
}
