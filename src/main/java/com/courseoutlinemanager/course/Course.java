package com.courseoutlinemanager.course;

import com.courseoutlinemanager.course.coursecondition.CourseCondition;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.course.knowledgeblock.*;
import com.courseoutlinemanager.educationalsystem.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Course {

	private static final int MAX_REQUIRED_COURSES = 3;

	private String courseCode;

	private String courseName;

	private String courseDescription;

	private int courseCredits;

	private KnowledgeBlock knowledgeBlock;

	private ArrayList<EducationalSystem> educationalSystem;

	private ArrayList<CourseOutline> courseOutlines;

	private ArrayList<CourseCondition> requirements;
}
