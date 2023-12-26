package com.courseoutlinemanager.course;

import com.courseoutlinemanager.course.coursecondition.CourseCondition;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.course.knowledgeblock.*;
import com.courseoutlinemanager.educationalsystem.*;

import java.util.ArrayList;

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

	public Course() {
	}

	public Course(String courseCode, String courseName, String courseDescription, int courseCredits) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.courseCredits = courseCredits;
	}

	public Course(String courseCode, String courseName, String courseDescription, int courseCredits,
			KnowledgeBlock knowledgeBlock, ArrayList<EducationalSystem> educationalSystem,
			ArrayList<CourseOutline> courseOutlines, ArrayList<CourseCondition> requirements) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.courseCredits = courseCredits;
		this.knowledgeBlock = knowledgeBlock;
		this.educationalSystem = educationalSystem;
		this.courseOutlines = courseOutlines;
		this.requirements = requirements;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public int getCourseCredits() {
		return courseCredits;
	}

	public void setCourseCredits(int courseCredits) {
		this.courseCredits = courseCredits;
	}

	public KnowledgeBlock getKnowledgeBlock() {
		return knowledgeBlock;
	}

	public void setKnowledgeBlock(KnowledgeBlock knowledgeBlock) {
		this.knowledgeBlock = knowledgeBlock;
	}

	public ArrayList<EducationalSystem> getEducationalSystem() {
		return educationalSystem;
	}

	public void setEducationalSystem(ArrayList<EducationalSystem> educationalSystem) {
		this.educationalSystem = educationalSystem;
	}

	public ArrayList<CourseOutline> getCourseOutlines() {
		return courseOutlines;
	}

	public void setCourseOutlines(ArrayList<CourseOutline> courseOutlines) {
		this.courseOutlines = courseOutlines;
	}

	public ArrayList<CourseCondition> getRequirements() {
		return requirements;
	}

	public void setRequirements(ArrayList<CourseCondition> requirements) {
		this.requirements = requirements;
	}

	public void addCourseCondition(CourseCondition other) {
		this.requirements.add(other);
	}

}
