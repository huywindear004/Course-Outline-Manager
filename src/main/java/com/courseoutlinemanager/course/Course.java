package com.courseoutlinemanager.course;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.common.customexception.AlreadyExistException;
import com.courseoutlinemanager.common.customexception.OutOfCapacityException;
import com.courseoutlinemanager.course.coursecondition.CourseCondition;
import com.courseoutlinemanager.course.coursecondition.PrerequisiteCourses;
import com.courseoutlinemanager.course.coursecondition.PreviousCourses;
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
		//add previousCourses and PrerequisiteCourses initially
		this.requirements.add(new PreviousCourses());
		this.requirements.add(new PrerequisiteCourses());
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

	public Course(String courseCode, String courseName, ArrayList<EducationalSystem> educationalSystems, KnowledgeBlock block, int courseCredits) {
		this(courseCode, courseName);
		this.educationalSystem = educationalSystems;
		this.knowledgeBlock = block;
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
		return this.knowledgeBlock;
	}

	public void setKnowledgeBlock(KnowledgeBlock knowledgeBlock) {
		this.knowledgeBlock = knowledgeBlock;
	}

	// =============================================================COURSE OUTLINE=============================================================
	public ArrayList<CourseOutline> getCourseOutlines() {
		return this.courseOutlines;
	}

	public void setCourseOutlines(ArrayList<CourseOutline> courseOutlines) {
		this.courseOutlines = courseOutlines;
	}

	public void addCourseOutline(CourseOutline outline) {
		
	}

	// =============================================================EDUCATIONAL SYSTEM=============================================================
	public ArrayList<EducationalSystem> getEducationalSystem() {
		return educationalSystem;
	}

	public void setEducationalSystem(ArrayList<EducationalSystem> educationalSystem) {
		this.educationalSystem = educationalSystem;
	}

	// =============================================================REQUIREMENTS=============================================================
	public ArrayList<CourseCondition> getRequirements() {
		return requirements;
	}

	/**
	 * Return the required course list
	 */
	public ArrayList<CourseCondition> getRequirements(String typeOfRequirement) {
		return this.requirements.stream()
                .filter(e -> ProcessString.equalsByAlphabet(e.getTypeName(), typeOfRequirement))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public void setRequirements(ArrayList<CourseCondition> requirements) {
		this.requirements = requirements;
	}

	/**
	 * Add course to list.
	 * @param typeOfRequirement
	 * Type of the requirement that the course needs to be added to
	 * @param course
	 * Course that needs to be added
	 * @throws OutOfCapacityException
	 * If the requirement is enough of elements.
	 * @throws AlreadyExistException
	 * If there is the equivalent course in the requirement.
	 */
	public void addCourseRequirements(String typeOfRequirement, Course course)
	throws OutOfCapacityException, AlreadyExistException {
		for(CourseCondition i : this.requirements)
			if(ProcessString.equalsByAlphabet(i.getTypeName(),typeOfRequirement))
				i.addCourse(course);
	}

	/**
	 * Return true if courseCode and courseName is the same. False if vice versa.
	 * @param o
	 * Given object
	 * @return
	 * True if courseCode and courseName is the same
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || this.getClass() != o.getClass()) return false;
		Course course = (Course) o;
		return ProcessString.equalsByAlphabet(course.courseName, this.courseName)
				&& ProcessString.equalsByAlphabet(course.courseCode, this.courseCode);
	}
}
