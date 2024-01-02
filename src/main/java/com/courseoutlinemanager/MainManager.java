package com.courseoutlinemanager;

import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.course.CourseManager;
import com.courseoutlinemanager.course.coursecondition.CourseCondition;
import com.courseoutlinemanager.course.knowledgeblock.KnowledgeBlock;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.courseoutline.CourseOutlineManager;
import com.courseoutlinemanager.educationalsystem.EducationalSystem;
import com.courseoutlinemanager.educationalsystem.EducationalSystemManager;
import com.courseoutlinemanager.lecturer.Lecturer;
import com.courseoutlinemanager.lecturer.LecturerManager;
import static com.courseoutlinemanager.common.output.ConsoleOutput.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.courseoutlinemanager.assessment.Assessment;
import com.courseoutlinemanager.assessment.AssessmentMethods;
import com.courseoutlinemanager.assessment.AssessmentTypes;
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
            System.out.println();

            printMainMenu();
            int choice = takeUserInput("your choice", 0, 9);
            switch (choice) {
                case 1 -> {
                    Lecturer newLecturer;
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
                    try {
                        Course course = inputCourse(cM);
                        CourseOutline newOutLine = new CourseOutline(course, newLecturer, this.inputEducationalSystem(course, eSM));
                        this.cOM.addCourseOutline(newOutLine);
                        editCourseOutline(newOutLine, cM);
                    } catch (CancelInputException e) { 
                        break;
                    }
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
                    String codeRequirement = takeUserInput("Input the code of requirement course: ");
                    System.out.println("Course List: ");
                    for (Course c : cM.findCourseByCodeRequirement(codeRequirement)) {
                        System.out.println(c);
                    }

                }
                case 6 -> {
                    // String[] sortingOptions = {
                    // "Arrange the list of lecturers' outlines",
                    // "Sort the outline list by outline list"
                    // };

                }
                case 7 -> {
                    String codeLecturer = takeUserInput("Input the code of requirement course: ");
                    System.out.println("Outline List: ");
                    for (CourseOutline c : cOM.findCourseOutlineListByIdLecturer(codeLecturer)) {
                        System.out
                                .println(c.getCourse().getCourseName() + "-" + c.getEducationalSystem().getTypeName());
                    }

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
                            for (CourseOutline cO : cOM.getCourseOutlineList()) {
                                printCourseOutLine(cO);
                            }
                        }
                        // export to txt
                        else if (choice == 2) {

                        } else {
                            break;
                        }
                    }
                }
                case 9 -> {
                    try {
                        statisticalCourseOutline(lM, cOM, cM);
                    } catch (CancelInputException e) {
                        break;
                    } catch (NotFoundException e) {
                        System.out.println(printLabel(e.getMessage(), "*"));
                    }

                }
                case 0 -> {
                    printLabel(" THANKS FOR USING OUR APPLICATION", "=", getWidth());
                    return;
                }
            }
            System.out.println();
        }
    }

    public Lecturer inputLecturer(LecturerManager lM) throws CancelInputException {
        Lecturer res = new Lecturer();
        String lecturerId;
        int choice;
        while (true) {
            lecturerId = takeUserInput("instructor's id");

            // if the lecturer with the input id is found -> return them
            try {
                // this function will throw NotFoundException() if it couldn't find by the input
                // id
                res = lM.getLecturerById(lecturerId);
                System.out.println(printLabel("WELCOME " + res, "~", getWidth()));
                System.out.println();
                break;

                // if not found it will throw NotFoundException() and force user to Re-enter /
                // Exit to main-menu
            } catch (NotFoundException e) {
                printLabel(e.getMessage(), "*", getWidth());
                String[] texts = { "Re-enter id" };
                printChoiceMenu("Couldn't find " + lecturerId, texts);
                choice = takeUserInput("your choice", 0, 1);

                // Exit to main-menu
                if (choice == 0)
                    throw new CancelInputException();
            }
        }
        return res;
    }

    public Course inputCourse(CourseManager cM) throws CancelInputException {
        String nameOrCode;
        int choice;
        ArrayList<Course> courseList; // courseList is the result after searching by nameOrCode
        Course newCourse = new Course();
        String[] subMenu = { "Create a new course.", "Re-enter." }; // the menu in case not found
        while (true) {
            nameOrCode = takeUserInput("course name or course code");
            courseList = cM.findCourse(nameOrCode);

            // if the result is empty -> print the subMenu
            if (courseList.isEmpty()) {
                printChoiceMenu("Couldn't find " + nameOrCode, subMenu);
                choice = takeUserInput("your choice", 0, 2);
                switch (choice) {
                    case 1 -> {
                        newCourse = cM.createCourse(takeUserInput("the name of the new course"));
                        System.out.println(printLabel("Your new course is: " + newCourse.toString(), "~"));
                        this.editCourseDescription(newCourse);
                        this.editKnowledgeBlock(newCourse);
                        this.editNumberOfCredits(newCourse);
                        try {
                            this.editCourseRequirements(newCourse, cM);
                        } catch (CancelInputException ignored) {
                        }

                        cM.addCourse(newCourse);
                        return newCourse;
                    }

                    // Exit to main-menu
                    case 0 -> throw new CancelInputException();
                }

                // if there is only 1 result -> return the found course
            } else if (courseList.size() == 1) {
                System.out.println(printLabel(courseList.get(0).toString(), "~"));
                return courseList.get(0);

                // if there are many results -> print the relevant courses and Re-enter the
                // nameOrCode
            } else {
                System.out.println("Relevant courses:");
                for (Course c : courseList) {
                    System.out.println(c);
                }
                System.out.println();
            }
        }
    }

    /**
     * This function returns the object of EducationalSystem which is an attribute
     * of the outline
     * if the outline's course allows adding outline
     */
    public EducationalSystem inputEducationalSystem(Course course, EducationalSystemManager eSM)
            throws CancelInputException {
        int choice;
        ArrayList<EducationalSystem> eList; // eList is all educationSystems available
        while (true) {
            ArrayList<String> subMenu = new ArrayList<>(); // subMenu that show choices of all available
                                                           // educationSystems
            eList = eSM.getEducationalSystemList();

            // add all type of educational system to subMenu
            eList.forEach(e -> subMenu.add(e.getTypeName()));
            printChoiceMenu("Choose educational system for " + course.toString() + "'s outline", subMenu);

            choice = takeUserInput("your choice", 0, eList.size());

            // Exit to main-menu
            if (choice == 0)
                throw new CancelInputException();

            // get the type of educational system to check if the course is available to add
            // courseoutline
            EducationalSystem typeOfSystem = eList.get(choice - 1);

            if (!course.isAvailForOutline(typeOfSystem.getTypeName())) {
                System.out.println(printLabel(course.toString() + " has enough outline in " + typeOfSystem, "*"));
            } else {
                return typeOfSystem;
            }
        }
    }

    public void editCourseOutline(CourseOutline outline, CourseManager cM) throws CancelInputException {
        int choice;
        while (true) {
            printEditOutlineMenu(outline);
            choice = takeUserInput("your choice", 0, 5);
            switch (choice) {
                // exit to main-menu
                case 0 -> throw new CancelInputException();

                // course objectives
                case 1 -> {
                    System.out.println(printLabel("Edit " + outline.toString() + "'s outline course objectives", "="));
                    int courseObjectivesNum = takeUserInput("the objectives number", 0, 10);
                    ArrayList<String> courseObjectiveList = new ArrayList<>();
                    for (int i = 1; i <= courseObjectivesNum; i++) {
                        courseObjectiveList.add(takeUserInput("objectives " + i));
                    }
                    outline.setCourseObjectiveList(courseObjectiveList);
                    System.out.println(printLabel("Edit successfully!", "~"));
                }

                // learning outcomes
                case 2 -> {
                    System.out.println(printLabel("Edit " + outline.toString() + "'s outline learning outcomes", "="));
                    int learningOutcomesNum = takeUserInput("the learning outcomes number", 0, 10);
                    ArrayList<String> learningOutcomeList = new ArrayList<>();
                    for (int i = 1; i <= learningOutcomesNum; i++) {
                        learningOutcomeList.add(takeUserInput("learning outcome " + i));
                    }
                    outline.setLearningOutcomeList(learningOutcomeList);
                    System.out.println(printLabel("Edit successfully!", "~"));
                }

                // course content
                case 3 -> {
                    System.out.println(printLabel("Edit " + outline.toString() + "'s outline content", "="));
                    int contentNum = takeUserInput("the content number", 0, 10);
                    ArrayList<String> courseContent = new ArrayList<>();
                    for (int i = 1; i <= contentNum; i++) {
                        courseContent.add(takeUserInput("content " + i));
                    }
                    outline.setCourseContent(courseContent);
                    System.out.println(printLabel("Edit successfully!", "~"));
                }

                // requirements
                case 4 -> {
                    this.editCourseRequirements(outline.getCourse(), cM);
                }

                // grades
                case 5 -> {
                    this.editCourseOutlineGrades(outline);
                }
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
        String[] addOrDeleteMenu = { "Add course.", "Delete course." };
        ArrayList<String> requirementsMenu = new ArrayList<>();
        // get all type of requirements to print menu
        for (CourseCondition req : course.getRequirementList()) {
            requirementsMenu.add(req.getTypeName());
        }
        while (true) {
            printChoiceMenu("Edit requirements of " + course.toString(), requirementsMenu);

            choice = takeUserInput("your choice", 0, requirementsMenu.size());
            if (choice == 0)
                throw new CancelInputException();
            try {
                String typeOfRequirement = requirementsMenu.get(choice - 1);

                // print the menu and the requirement's courses
                System.out.println(printLabel("Edit " + typeOfRequirement + " of " + course.toString(), "="));
                System.out.print(orderedList(addOrDeleteMenu, 0));
                System.out.println(printLine("0. Exit.", 0));
                System.out.println(printLine(typeOfRequirement, 4));
                if (course.getRequirement(typeOfRequirement).isEmpty())
                    System.out.println(printLine("NONE", 4));
                else {
                    // print the courseList of course's requirement
                    System.out.print(unorderedList(course.getRequirement(typeOfRequirement).getCourseList().stream()
                            .map(object -> object.toString()).collect(Collectors.toCollection(ArrayList::new)), 4));
                }

                System.out.println(printLabel("", "="));

                choice = takeUserInput("your choice", 0, addOrDeleteMenu.length);
                // xem
                // Exit to main-menu
                if (choice == 0)
                    throw new CancelInputException();

                // add course to requirement
                else if (choice == 1) {
                    course.addCourseToRequirementList(typeOfRequirement, this.inputCourse(cM));
                    System.out.println("Added successfully!");
                }

                // remove course of requirement
                else if (choice == 2) {
                    course.removeCourseOfRequirementList(typeOfRequirement, this.inputCourse(cM));
                    System.out.println("Removed successfully!");
                }

            } catch (OutOfCapacityException | AlreadyExistException | NotFoundException e) {
                System.out.println(printLabel(e.getMessage(), "*"));
            }
        }
    }

    public void editKnowledgeBlock(Course course) throws CancelInputException {
        int choice;
        KnowledgeBlock[] allBlocks = KnowledgeBlock.values();
        ArrayList<String> knowledgeBlockMenu = new ArrayList<>();
        for (KnowledgeBlock block : allBlocks) {
            knowledgeBlockMenu.add(block.toString());
        }
        knowledgeBlockMenu.remove(0);

        printChoiceMenu("Choose type of KnowledgeBlock of " + course.toString(), knowledgeBlockMenu);
        choice = takeUserInput("your choice", 0, knowledgeBlockMenu.size());
        if (choice == 0)
            throw new CancelInputException();
        course.setKnowledgeBlock(KnowledgeBlock.valueOf(knowledgeBlockMenu.get(choice - 1)));
    }

    public void editCourseOutlineGrades(CourseOutline outline) throws CancelInputException {
        int choice;
        String[] addOrDeleteMenu = { "Add grade.", "Edit grade.", "Delete grade." };

        while (true) {
            System.out.println(printLabel("Edit grades of " + outline.toString() + "'s outline", "="));
            System.out.print(orderedList(addOrDeleteMenu, 0));
            System.out.println(printLine("0. Exit.", 0));
            System.out.println(printLine("Grades: ", 1));
            if (outline.getGradeList().isEmpty())
                System.out.println(printLine("NONE", 4));
            else {
                // print the grades
                System.out.print(unorderedList(outline.getGradeList().stream()
                        .map(grade -> grade.toString()).collect(Collectors.toCollection(ArrayList::new)), 4));
            }
            System.out.println(printLabel("", "="));

            choice = takeUserInput("your choice", 0, addOrDeleteMenu.length);

            // exit to main-menu
            if (choice == 0)
                throw new CancelInputException();

            // add grade
            else if (choice == 1) {
                System.out.println(printLabel("Add grade to " + outline.toString() + "'s outline", "="));
                try {
                    outline.addGrade(
                            takeUserInput("the type of this grade"),
                            takeUserInput("the method of this grade"),
                            takeUserInput("the weight of this grade", "0.05", "1.0"));
                } catch (OutOfCapacityException e) {
                    System.out.println(printLabel(e.getMessage(), "*"));
                }
            }

            // edit grade
            else if (choice == 2) {
                while (true) {
                    System.out.println(printLabel("Edit grade of " + outline.toString() + "'s outline", "="));
                    printChoiceMenu("Edit grade of " + outline.toString() + "'s outline", outline.getGradeList()
                            .stream().map(grade -> grade.toString()).collect(Collectors.toCollection(ArrayList::new)));

                    choice = takeUserInput("the position of grade to edit", 0, outline.getGradeList().size());

                    // exit to add-edit-exit grade
                    if (choice == 0) {
                        break;
                    }

                    editGrade(outline, outline.getGrade(choice - 1));
                }
            }

            // remove grade
            else if (choice == 3) {
                removeGrade(outline);
            }

        }
    }

    private void editGrade(CourseOutline outline, Assessment assessment) {
        String[] gradeProperties = {
                "Type [" + assessment.getAssessingType() + "].",
                "Method [" + assessment.getAssessingMethod() + "].",
                "Weight [" + assessment.getWeight() + "].",
                "Content."
        };
        ArrayList<String> typeMenu = new ArrayList<>();

        for (AssessmentTypes type : AssessmentTypes.values())
            typeMenu.add(type.toString());
        typeMenu.remove(0);

        ArrayList<String> methodMenu = new ArrayList<>();
        for (AssessmentMethods method : AssessmentMethods.values())
            methodMenu.add(method.toString());
        methodMenu.remove(0);

        int choice;
        System.out.println(printLabel("Edit grade of " + outline.toString() + "'s outline", "="));
        System.out.print(orderedList(gradeProperties, 0));
        System.out.println(printLine("0. Exit.", 0));
        System.out.println(printLabel("", "="));
        choice = takeUserInput("your choice", 0, gradeProperties.length);

        // EXIT to choose-grade menu
        if (choice == 0)
            return;

        // ASSESSING TYPE
        else if (choice == 1) {
            printChoiceMenu("Choose type of assessments for " + outline.toString() + "'s outline's grade",
                    typeMenu);
            choice = takeUserInput("your choice", 0, typeMenu.size());

            // exit to edit-grades menu
            if (choice == 0)
                return;

            assessment.setAssessingType(typeMenu.get(choice - 1));
        }

        // ASSESSING METHOD
        else if (choice == 2) {
            printChoiceMenu("Choose method of assessments for " + outline.toString() + "'s outline's grade",
                    methodMenu);
            choice = takeUserInput("your choice", 0, methodMenu.size());

            // exit to edit-grades menu
            if (choice == 0)
                return;

            assessment.setAssessingType(methodMenu.get(choice - 1));
        }

        // WEIGHT
        else if (choice == 3) {
            System.out.println(printLabel("Edit weight of " + outline.toString() + "'s outline's grade", "="));
            try {
                outline.setGradeWeight(assessment, takeUserInput("grade's weight", "0.0", "1.0"));
            } catch (NumberFormatException | OutOfCapacityException e) {
                System.out.println(printLabel(e.getMessage(), "*"));
            }
        }

        // CONTENT
        else if (choice == 4) {
            System.out.println(printLabel("Edit content of " + outline.toString() + "'s outline's grade", "="));
            assessment.setContent(takeUserInput("grade's content"));
        }
    }

    public void removeGrade(CourseOutline outline) {
        int choice;
        while (true) {
            choice = takeUserInput("Enter the location to delete", 0, outline.getGradeList().size());
            // exit to edit-grades menu
            if (choice == 0)
                continue;
            outline.removeGrade(getIndentSpace());

        }

    }

    public void statisticalCourseOutline(LecturerManager lMm, CourseOutlineManager cOM, CourseManager cM)
            throws NotFoundException, CancelInputException {
        int choice;
        Map<Double, Integer> statistics = new HashMap<>();
        String[] selectionList = {
                "Statistics on the number of outlines according to the number of credits of the lecturer.",
                "Statistics on the number of outlines according to the number of credits in the outline list."
        };
        printChoiceMenu("Choose a statistical option", selectionList);

        choice = takeUserInput("your choose", 0, selectionList.length);

        // Exit to main-menu
        if (choice == 0)
            throw new CancelInputException();

        if (choice == 1) {
            statistics = new HashMap<>();
            Lecturer lecturer = this.inputLecturer(lM);
            for (CourseOutline cO : lecturer.getCourseOutlineList()) {
                double credits = cO.getCourse().getCourseCredits();
                // get the value(outlines num) of the course according to credits num(key)
                // if not found put it in the map with with the new key(credits) - value
                // (outlinesNum of the course)
                if (statistics.containsKey(credits))
                    statistics.put(credits, statistics.get(credits) + cO.getCourse().getCourseOutlineList().size());
                else
                    statistics.put(credits, 1);
            }
        }
        if (choice == 2) {
            statistics = new HashMap<>();
            for (Course c : cM.getCourseList()) {
                double credits = c.getCourseCredits();
                // get the value(outlines num) of the course according to credits num(key)
                // if not found put it in the map with with the new key(credits) - value
                // (outlinesNum of the course)
                if (statistics.containsKey(credits))
                    statistics.put(credits, statistics.get(credits) + c.getCourseOutlineList().size());
                else
                    statistics.put(credits, 1);
            }
        }
        System.out.println(printLabel("STATISTICS", "="));
        for (Map.Entry<Double, Integer> mapElement : statistics.entrySet()) {
            System.out.println(printLine(mapElement.getKey() + " credits: " + mapElement.getValue(), 1));
        }
        System.out.println(printLabel("", "="));
    }

    public void sortingCourseOutLine(LecturerManager lM) throws NotFoundException, CancelInputException {
        String[] sortingOptions = {
                "Sort the outline list of the lecturer.",
                "Sort all outlines."
        };
        printChoiceMenu("Choose a statistical option", sortingOptions);
        int choice = takeUserInput("your choose", 0, 2);
        // Exit to main-menu
        if (choice == 0)
            throw new CancelInputException();

        else if (choice == 1) {
            this.inputLecturer(lM).sortCourseOutline();
        } else if (choice == 2) {
            cOM.sortCourseOutline();
        }
    }

}
