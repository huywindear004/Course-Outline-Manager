package com.courseoutlinemanager;

import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.CourseManager;
import com.courseoutlinemanager.course.knowledgeblock.KnowledgeBlock;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.courseoutline.CourseOutlineManager;
import com.courseoutlinemanager.educationalsystem.EducationalSystem;
import com.courseoutlinemanager.educationalsystem.EducationalSystemManager;
import com.courseoutlinemanager.lecturer.Lecturer;
import com.courseoutlinemanager.lecturer.LecturerManager;
import static com.courseoutlinemanager.common.output.ConsoleOutput.*;

import java.util.ArrayList;

import com.courseoutlinemanager.common.customexception.AlreadyExistException;
import com.courseoutlinemanager.common.customexception.NotFoundException;
import com.courseoutlinemanager.common.customexception.OutOfCapacityException;

import static com.courseoutlinemanager.common.ProcessString.*;
import static com.courseoutlinemanager.common.input.ConsoleInput.*;

public class MainManager {
    private LecturerManager lM;
    private CourseOutlineManager cOM;
    private CourseManager cM;
    private EducationalSystemManager eSM;

    public MainManager() {
        lM = new LecturerManager();
        cOM = new CourseOutlineManager();
        cM = new CourseManager();
        eSM = new EducationalSystemManager();
    }

    public void addCourseOutline() {

    }

    public void run() throws NotFoundException {
        while (true) {
            printMainMenu();
            int choice = takeUserInput("your choice", 0, 9);
            switch (choice) {
                case 1 -> {
                    Lecturer newLecturer = new Lecturer();
                    boolean check = true;

                    while (check) {
                        System.out.println("Enter instructor id: ");
                        String lecturerId = sc.nextLine();
                        try {
                            newLecturer = lM.findLecturerById(lecturerId);
                        } catch (NotFoundException e) {
                            System.out.println("Instructor not found");
                            System.out.println("Do you want to create a lecturer?");
                            printYesOrNo();
                            choice = takeUserInput("your choice", 0, 2);
                            switch (choice) {
                                case 1 -> {
                                    System.out.println("Enter instructor name: ");
                                    String nameLecturer = sc.nextLine();
                                    newLecturer = new Lecturer(nameLecturer, lecturerId);
                                    lM.addLecturer(newLecturer);
                                    System.out.println("Instructor information: " + newLecturer);
                                    check = false;
                                }
                                case 2 -> {
                                    break;
                                }
                                case 0 -> {
                                    break;
                                }
                            }

                        }
                        if (newLecturer.getCourseOutlineList() != null && newLecturer.hasEnoughCourseOutlines()) {
                            System.out.println("This lecturer has created the syllabus maximum times");
                        }
                    }
                    Course newCourse = new Course();
                    check = true;

                    while (check) {
                        System.out.println("Enter the subject name or subject code: ");
                        String nameOrCode = sc.nextLine();
                        ArrayList<Course> listCoure = cM.findCourse(nameOrCode);
                        if (listCoure.isEmpty()) {
                            System.out.println("Would you like to add it to the subject list?");
                            printYesOrNo();
                            choice = takeUserInput("your choice", 0, 2);
                            switch (choice) {
                                case 1 -> {
                                    cM.addCourse(newCourse);
                                    check = false;
                                }
                                case 2 -> {
                                    break;
                                }

                            }
                        } else if (listCoure.size() == 1) {
                            try {
                                newCourse = cM.getCourse(newCourse);
                            } catch (Exception ignored) {
                                break;
                            }
                        } else {
                            System.out.println("List:");
                            for (Course c : listCoure) {
                                System.out.println(c);
                            }
                        }
                    }

                    check = true;
                    while (check) {
                        try {
                            printEducation();
                            choice = takeUserInput("your choice", 0, 2);
                            String tmp = "";
                            switch (choice) {
                                case 1 -> {
                                    EducationalSystem edu = eSM.getEducationalSystem("FormalEducation");
                                    tmp = edu.getTypeName();
                                }
                                case 2 -> {
                                    EducationalSystem edu = eSM.getEducationalSystem("TransferEducation");
                                    tmp = edu.getTypeName();
                                }
                            }
                            if (!newCourse.isAvailForOutline(tmp)) {
                                System.out.println("The course has an outline of the training system");

                            } else {
                                System.out.println("Enter course description");
                                String desc = sc.nextLine();
                                newCourse.setCourseDescription(desc);
                                System.out.println("Enter the number of credits");
                                int credits = sc.nextInt();
                                newCourse.setCourseCredits(credits);
                                printRequirementList();
                                choice = takeUserInput("your choice", 0, 2);
                                switch (choice) {
                                    case 1 -> {
                                        String typeOfRequirements = "PrerequisiteCourses";
                                        System.out.println("Enter the name of course: ");
                                        String nameCourse = sc.nextLine();
                                        System.out.println("Enter the code of course: ");
                                        String codeCourse = sc.nextLine();
                                        Course tempCourse = new Course(nameCourse, codeCourse);
                                        boolean isAddableCourse = false;
                                        // Check if the tempCourse already existed in cM.courseList
                                        try {
                                            // if the tempCourse exist then change the reference of tempCourse to the
                                            // existed course
                                            tempCourse = cM.getCourse(tempCourse);
                                            isAddableCourse = false;
                                        } catch (NotFoundException e) {
                                            isAddableCourse = true;
                                        }

                                        // add it to requirements
                                        try {
                                            newCourse.addCourseToRequirementList(typeOfRequirements, tempCourse);
                                        } catch (OutOfCapacityException | AlreadyExistException e) {
                                            System.out.println(
                                                    e.getMessage() + " in " + newCourse);
                                            isAddableCourse = false;
                                        }

                                        if (isAddableCourse)
                                            cM.addCourse(tempCourse);

                                    }
                                    case 2 -> {

                                    }
                                    case 0 -> {

                                    }

                                }

                            }

                        } catch (NotFoundException e) {
                            // TODO: handle exception
                        }
                    }
                    System.out.println(newCourse);
                }
                case 2 -> {

                }
                case 3 -> {
                    // printEditOutlineMenu();

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
