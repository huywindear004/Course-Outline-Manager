package com.courseoutlinemanager.course;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.common.customexception.AlreadyExistException;
import com.courseoutlinemanager.common.customexception.OutOfCapacityException;
import com.courseoutlinemanager.course.coursecondition.CourseCondition;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.course.knowledgeblock.*;
import com.courseoutlinemanager.educationalsystem.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Course {

	private String courseCode;

	private String courseName;

	private String courseDescription;

	private int courseCredits;

	private KnowledgeBlock knowledgeBlock;

	private ArrayList<EducationalSystem> educationalSystem;

	private ArrayList<CourseOutline> courseOutlines;

	private ArrayList<CourseCondition> requirements;

	public Course() {
		this.educationalSystem = new ArrayList<>();
		this.courseOutlines = new ArrayList<>();
		this.requirements = new ArrayList<>();
	}

	public Course(String courseCode, String courseName) {
		this.courseCode = courseCode;
		this.courseName = courseName;
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

	// =============================================================REQUIREMENTS=============================================================
	public ArrayList<CourseCondition> getRequirements() {
		return requirements;
	}

	/**
	 * Return the required course list
	 */
	public ArrayList<CourseCondition> getRequirements(String typeOfRequiredCourse) {
		return new ArrayList<CourseCondition>(this.requirements.stream()
				.filter(course -> ProcessString.compare(course.getTypeName(), typeOfRequiredCourse))
				.collect(Collectors.toList()));
	}

	/**
	 * Return the size of the requirement types
	 */
	public int sizeOfRequiredTypeOfCourse(String type) {
		int count = 0;
		for (CourseCondition course : this.requirements)
			if (ProcessString.compare(course.getTypeName(), type))
				count++;
		return count;
	}

	public void setRequirements(ArrayList<CourseCondition> requirements) {
		this.requirements = requirements;
	}

	/**
	 * Return whether it already has the requirement or not
	 */
	public boolean containsRequirement(CourseCondition requirementCheck) {
		for (CourseCondition requirement : this.requirements)
			if (requirement.equals(requirementCheck)) 
				return true;
		return false;  
	}

	public void addCourseCondition(CourseCondition other) throws OutOfCapacityException, AlreadyExistException {
		if (this.sizeOfRequiredTypeOfCourse(other.getTypeName()) >= other.getMAX_COURSES())
			throw new OutOfCapacityException();
		if (this.containsRequirement(other))
			throw new AlreadyExistException();
		this.requirements.add(other);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || this.getClass() != object.getClass())
			return false;
		Course course = (Course) object;
		return ProcessString.compare(course.courseName, this.courseName)
				&& ProcessString.compare(course.courseCode, this.courseCode);
	}
}
