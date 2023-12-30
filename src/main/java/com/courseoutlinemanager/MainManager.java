package com.courseoutlinemanager;

import com.courseoutlinemanager.course.CourseManager;
import com.courseoutlinemanager.course.knowledgeblock.KnowledgeBlockManager;
import com.courseoutlinemanager.courseoutline.CourseOutlineManager;
import com.courseoutlinemanager.educationalsystem.EducationalSystemManager;
import com.courseoutlinemanager.lecturer.LecturerManager;
import static com.courseoutlinemanager.common.output.ConsoleOutput.*;
import static com.courseoutlinemanager.common.ProcessString.*;
import static com.courseoutlinemanager.common.input.ConsoleInput.*;

public class MainManager {
    private LecturerManager lM;
    private CourseOutlineManager cOM;
    private CourseManager cM;
    private EducationalSystemManager eSM;
    private KnowledgeBlockManager kBM;

    public MainManager() {
        lM = new LecturerManager();
        cOM = new CourseOutlineManager();
        cM = new CourseManager();
        eSM = new EducationalSystemManager();
        kBM = new KnowledgeBlockManager();
    }

    public void addCourseOutline() {

    }

    public void run() {
        while (true) {
            printMainMenu();
            int choice = takeUserInput("your choice", 0, 9);
            switch (choice) {
                case 1 -> {
                    
                }
                case 2 -> {

                }
                case 3 -> {

                }
                case 4 -> {

                }
                case 5 -> {

                }
                case 6 -> {

                }
                case 7 -> {
                }
                // Export outline
                case 8 -> {
                    while (true) {
                        printExportMenu();
                        choice = takeUserInput("your choice", 0, 2);
                        // input outline (courseName and courseCode)
                        System.out.println(printLabel("INPUT COURSE OUTLINE", "=", getWidth()));

                        // export to console
                        if (choice == 1) {

                        }
                        // export to txt
                        else if (choice == 2) {

                        } else {
                            break;
                        }
                    }
                }
                case 9 -> {

                }
                case 0 -> {
                    printLabel(" THANKS FOR USING OUR APPLICATION", "=", getWidth());
                    return;
                }
            }
        }
    }
}
