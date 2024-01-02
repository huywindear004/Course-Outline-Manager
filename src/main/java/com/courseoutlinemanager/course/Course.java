package com.courseoutlinemanager.course;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.common.customexception.AlreadyExistException;
import com.courseoutlinemanager.common.customexception.NotFoundException;
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
	private static int courseCodeCount = 1;

	private String courseCode;

	private String courseName;

	private String courseDescription;

	private double courseCredits;

	private KnowledgeBlock knowledgeBlock = KnowledgeBlock.NULL;

	private ArrayList<CourseOutline> courseOutlineList;

	private ArrayList<CourseCondition> requirementList;

	public Course() {
		this.courseOutlineList = new ArrayList<>();
		this.requirementList = new ArrayList<>();
		// add previousCourses and PrerequisiteCourses initially
		this.requirementList.add(new PreviousCourses());
		this.requirementList.add(new PrerequisiteCourses());
	}

	/**
	 * This constructor is for user input operation
	 */
	public Course(String courseName) {
		this();
		this.courseName = courseName;
		this.courseCode = String.format("COUR%03d", courseCodeCount++);
	}

	/**
	 * This constructor just only for compare operation between courses
	 * (Hàm khởi tạo 2 tham số này chỉ dành cho việc tạo môn học mới để so sánh với
	 * các môn học khác)
	 */
	public Course(String courseCode, String courseName) {
		this.courseCode = courseCode;
		this.courseName = courseName;
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

	public double getCourseCredits() {
		return courseCredits;
	}

	public void setCourseCredits(double courseCredits) {
		this.courseCredits = courseCredits;
	}

	public KnowledgeBlock getKnowledgeBlock() {
		return this.knowledgeBlock;
	}

	public void setKnowledgeBlock(KnowledgeBlock knowledgeBlock) {
		this.knowledgeBlock = knowledgeBlock;
	}

	// =============================================================COURSE OUTLINE=============================================================
	public ArrayList<CourseOutline> getCourseOutlineList() {
		return this.courseOutlineList;
	}

	public void addCourseOutline(CourseOutline outline) throws AlreadyExistException {

	}


	public boolean isAvailForOutline(String typeOfSystem) {
		EducationalSystem eSys = null;
		// Get type of educational system to which outline in this list belongs
		for (CourseOutline outline : this.courseOutlineList) {
			if (ProcessString.equalsByAlphabet(typeOfSystem, outline.getEducationalSystem().getTypeName()))
				eSys = outline.getEducationalSystem();
		}
		if (eSys == null)
			return true;
		int count = 0;
		for (CourseOutline outline : this.courseOutlineList) {
			if (outline.getEducationalSystem().equals(eSys))
				count++;
		}
		return count < eSys.getMaxOutlinePerCourse(); // hàm này sẽ trả về true nếu kiểu hệ đào tạo có thể thêm đề cương
	}

	// =============================================================REQUIREMENTS=============================================================
	public ArrayList<CourseCondition> getRequirementList() {
		return requirementList;
	}

	/**
	 * Return the required course list
	 */
	public CourseCondition getRequirement(String typeOfRequirement) throws NotFoundException {
		for(CourseCondition i : this.requirementList)
			if(ProcessString.equalsByAlphabet(typeOfRequirement,i.getTypeName()))
				return i;
		throw new NotFoundException("Couldn't find " + typeOfRequirement);
	}

	// public void setRequirementList(ArrayList<CourseCondition> requirements) {
	// 	this.requirementList = requirements;
	// }

	/**
	 * Add course to list.
	 * 
	 * @param typeOfRequirement
	 *                          Type of the requirement that the course needs to be
	 *                          added to
	 * @param course
	 *                          Course that needs to be added
	 * @throws OutOfCapacityException
	 *                                If the requirement is enough of elements.
	 * @throws AlreadyExistException
	 *                                If there is the equivalent course in the
	 *                                requirement.
	 * @throws NotFoundException 
	 * If the type of requirement is not found
	 */
	public void addCourseToRequirementList(String typeOfRequirement, Course toBeAdded)
			throws OutOfCapacityException, AlreadyExistException, NotFoundException {
		if(toBeAdded.equals(this))
			throw new AlreadyExistException("Duplicate course code " + this + " " + toBeAdded);
		this.getRequirement(typeOfRequirement).addCourse(toBeAdded);
	}


	public boolean removeCourseOfRequirementList(String typeOfRequirement, Course toDelete) 
			throws AlreadyExistException, NotFoundException {
		if(toDelete.equals(this))
			throw new AlreadyExistException("Duplicate course code " + this + " " + toDelete);
		return this.getRequirement(typeOfRequirement).removeCourse(toDelete);
	}
	
	


	
	public Course findCourseHasRequirementCode(String codeCourse) {
		for (CourseCondition i : this.requirementList) {
			return i.findCourseById(codeCourse);
		}
		return null;
	}
	




	/**
	 * Return true if courseCode and courseName is the same. False if vice versa.
	 * 
	 * @param o
	 *          Given object
	 * @return
	 *         True if courseCode and courseName is the same
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || this.getClass() != o.getClass())
			return false;
		Course course = (Course) o;
		return ProcessString.equalsByAlphabet(course.courseCode, this.courseCode);
	}

	@Override
	public String toString() {
		return this.courseName + "(" + this.courseCode + ")";
	}
}
