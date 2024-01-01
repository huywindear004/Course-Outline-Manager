package com.courseoutlinemanager;

import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.CourseManager;
import com.courseoutlinemanager.course.coursecondition.CourseCondition;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.courseoutline.CourseOutlineManager;
import com.courseoutlinemanager.educationalsystem.EducationalSystem;
import com.courseoutlinemanager.educationalsystem.EducationalSystemManager;
import com.courseoutlinemanager.lecturer.Lecturer;
import com.courseoutlinemanager.lecturer.LecturerManager;
import static com.courseoutlinemanager.common.output.ConsoleOutput.*;

import java.util.ArrayList;

import com.courseoutlinemanager.common.customexception.AlreadyExistException;
import com.courseoutlinemanager.common.customexception.CancelInputException;
import com.courseoutlinemanager.common.customexception.NotFoundException;
import com.courseoutlinemanager.common.customexception.OutOfCapacityException;

import static com.courseoutlinemanager.common.ProcessString.*;

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

    public Lecturer inputLecturer(LecturerManager lM) throws CancelInputException {
        Lecturer res = new Lecturer();
        int choice;
        String lecturerId;
        while (true) {
            lecturerId = takeUserInput("instructor's id");
            try {
                res = lM.getLecturerById(lecturerId);
                System.out.println(printLabel("WELCOME " + res, "~", getWidth()));
                break;

                // the lecturer doesn't exist
            } catch (NotFoundException e) {
                printLabel(e.getMessage(), "*", getWidth());
                String[] texts = { "Re-enter id" };
                printChoiceMenu("Couldn't find " + lecturerId, texts);
                choice = takeUserInput("your choice", 0, 1);
                if (choice == 0)
                    throw new CancelInputException();
            }
        }
        return res;
    }

    public Course inputCourse(CourseManager cM) throws CancelInputException {
        String nameOrCode;
        int choice;
        ArrayList<Course> courseList;
        Course newCourse = new Course();
        String[] subMenu = { "Create a new course.", "Re-enter." };
        while (true) {
            nameOrCode = takeUserInput("course name or course code");
            courseList = cM.findCourse(nameOrCode);

            if (courseList.isEmpty()) {
                printChoiceMenu("Couldn't find " + nameOrCode, subMenu);
                choice = takeUserInput("your choice", 0, 2);
                switch (choice) {
                    case 1 -> {
                        newCourse = cM.createCourse(takeUserInput("Enter the name of the course: "));
                        System.out.println(printLabel("Your new course is: " + newCourse.toString(), "~"));
                        cM.addCourse(newCourse);
                        return newCourse;
                    }
                    case 0 -> throw new CancelInputException();
                }
            } else if (courseList.size() == 1) {
                System.out.println(printLabel(courseList.get(0).toString(), "~"));
                return courseList.get(0);
            } else {
                System.out.println("Relevant courses:");
                for (Course c : courseList) {
                    System.out.println(c);
                }
                System.out.println();
            }
        }
    }

    public EducationalSystem inputEducationalSystem(Course course, EducationalSystemManager eSM)
            throws CancelInputException {
        int choice;
        ArrayList<EducationalSystem> eList;
        while (true) {
            ArrayList<String> subMenu = new ArrayList<>();
            eList = eSM.getEducationalSystemList();
            
            //get all type of educational system to print menu
            eList.forEach(e -> subMenu.add(e.getTypeName()));
            printChoiceMenu("Choose educational system", subMenu);

            choice = takeUserInput("your choice", 0, eList.size());
            if (choice == 0)
                throw new CancelInputException();
            // get the type of educational system to check if the course is available to add
            // courseoutline
            EducationalSystem typeOfSystem = eList.get(choice - 1);

            if (!course.isAvailForOutline(typeOfSystem.getTypeName())) {
                System.out.println(course.toString() + " has enough outline in " + typeOfSystem);
            } else {
                return typeOfSystem;
            }
        }
    }

    public void editCourseDescription(Course course) {
        course.setCourseDescription(takeUserInput("course description"));
    }

    public void editNumberOfCredits(Course course) {
        try {
            course.setCourseCredits(takeUserInput("the number of credits", "2.0", "4.0"));
        } catch (NumberFormatException e) {
            printLabel(e.getMessage(), "!", getWidth());
        }
    }

    public void editCourseRequirements(Course course, CourseManager cM) throws CancelInputException {
        int choice;
        ArrayList<String> requirementsMenu = new ArrayList<>();
        //get all type of requirements to print menu
        for (CourseCondition req : course.getRequirementList()) {
            requirementsMenu.add(req.getTypeName());
        }
        while (true) {
            printChoiceMenu("Choose type of requirement", requirementsMenu);
            choice = takeUserInput("your choice", 0, requirementsMenu.size()); 
            if(choice == 0)
                throw new CancelInputException();
            try {
                course.addCourseToRequirementList(requirementsMenu.get(choice - 1), this.inputCourse(this.cM));
            } 
            catch (OutOfCapacityException | AlreadyExistException e) {
                System.out.println(printLabel(e.getMessage(), "*"));
            }
        }
    }

    public void run() {

        cM.addCourse(new Course("OOP"));
        cM.addCourse(new Course("S&P"));
        cM.addCourse(new Course("PT"));
        cM.addCourse(new Course("LAW"));
        cM.addCourse(new Course("ITP"));

        lM.addLecturer(new Lecturer("Dương đẹp trai"));
        lM.addLecturer(new Lecturer("Dương "));
        lM.addLecturer(new Lecturer("Huy xấu trai"));
        while (true) {
            System.out.println("\nCourses: ");
            for (Course c : cM.getCourseList())
                System.out.print(c.toString() + " || ");
            System.out.println("\nLecturers: ");
            for (Lecturer l : lM.getLecturerList())
                System.out.print(l.toString() + " || ");
            System.out.println("\nOutlines: ");
            for (CourseOutline cl : cOM.getCourseOutlineList()) {
                System.out.print(cl.toString() + " || ");
            }

            printMainMenu();
            int choice = takeUserInput("your choice", 0, 9);
            switch (choice) {
                case 1 -> {
                    Course newCourse;
                    Lecturer newLecturer;
                    EducationalSystem educationalSystem;
                    // get lecturer and check if they have enough outline or not
                    try {
                        newLecturer = inputLecturer(this.lM);
                    } catch (CancelInputException e) {
                        break;
                    }
                    if (newLecturer.hasEnoughCourseOutlines()) {
                        System.out.println(printLabel("This lecturer has had enough outlines.", "*"));
                        break;
                    }

                    // get course and check if it has enough outline in specified educational system
                    // or not
                    try {
                        newCourse = inputCourse(cM);
                    } catch (CancelInputException e) {
                        break;
                    }

                    // get educationalSystem and check if the course has enough outlines in
                    // that educationalSystem or not
                    try {
                        educationalSystem = inputEducationalSystem(newCourse, eSM);
                    } catch (CancelInputException e) {
                        break;
                    }
                    editCourseDescription(newCourse);
                    editNumberOfCredits(newCourse);
                    try {
                        editCourseRequirements(newCourse, cM);
                    } catch (CancelInputException e) {
                        break;
                    }

                    // ArrayList<String> textCondition = new ArrayList<>();
                    // ArrayList<CourseCondition> conditionList = newCourse.getRequirementList();
                    // conditionList.forEach(c -> textCondition.add(c.getTypeName()));
                    // printChoiceMenu("Select subject requirements", textCondition);
                    // choice = takeUserInput("your choice", 0, conditionList.size());

                    // String typeOfRequirements = conditionList.get(choice - 1).getTypeName();

                    // Course tmpCourse = new Course();
                    // try {
                    // tmpCourse = inputCourse(cM);
                    // } catch (CancelInputException e) {
                    // break;
                    // }

                    // String nameCourse = takeUserInput("the name of the course");

                    // String codeCourse = takeUserInput("the code of the course");

                    // Course tempCourse = new Course(nameCourse, codeCourse);

                    // boolean isAddableCourse = false;
                    // // Check if the tempCourse already existed in cM.courseList
                    // try {
                    // // if the tempCourse exist then change the reference of tempCourse to the
                    // // existed course
                    // tempCourse = cM.getCourse(tempCourse);
                    // isAddableCourse = false;
                    // } catch (NotFoundException e) {
                    // isAddableCourse = true;
                    // }

                    // // add it to requirements
                    // try {
                    // newCourse.addCourseToRequirementList(typeOfRequirements, tempCourse);
                    // } catch (OutOfCapacityException | AlreadyExistException e) {
                    // System.out.println(
                    // e.getMessage() + " in " + newCourse);
                    // isAddableCourse = false;
                    // }

                    // if (isAddableCourse)
                    // cM.addCourse(tempCourse);

                    // }
                    // System.out.println(newCourse);
                    printCourse(newCourse);
                }
                case 2 -> {

                }
                case 3 -> {
                    // printEditOutlineMenu();

                }
                case 4 -> {
                    try {
                        printCourse(inputCourse(cM));
                    } catch (CancelInputException e) {
                        break;
                    }
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
            System.out.println();
        }
    }
}
