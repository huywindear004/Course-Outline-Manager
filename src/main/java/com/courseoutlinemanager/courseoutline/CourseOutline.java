package com.courseoutlinemanager.courseoutline;

import com.courseoutlinemanager.lecturer.Lecturer;
import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.educationalsystem.EducationalSystem;
import com.courseoutlinemanager.assessment.*;
import com.courseoutlinemanager.common.customexception.*;

import java.util.ArrayList;

public class CourseOutline {

	private static final int MIN_GRADES = 2;

	private static final int MAX_GRADES = 4;

	private static final double TOTAL_WEIGHT = 1;

	private Course course;

	private Lecturer compiler;

	private ArrayList<String> courseObjectiveList;

	private ArrayList<String> learningOutcomeList;

	private ArrayList<String> courseContent;

	private ArrayList<Assessment> gradeList;

	private EducationalSystem educationalSystem;

	public CourseOutline() {
		this.courseObjectiveList = new ArrayList<>();
		this.learningOutcomeList = new ArrayList<>();
		this.courseContent = new ArrayList<>();
		this.gradeList = new ArrayList<>();
	}

	public CourseOutline(Course course, EducationalSystem e) {
		this.course = course;
		this.educationalSystem = e;
	}

	public CourseOutline(Course course, Lecturer compiler) {
		this.course = course;
		this.compiler = compiler;
	}

	public CourseOutline(Course course, Lecturer compiler, EducationalSystem e) {
		this(course, compiler);
		this.educationalSystem = e;
	}

	public Course getCourse() {
		return this.course;
	}

	public ArrayList<String> getCourseObjectiveList() {
		return this.courseObjectiveList;
	}

	public ArrayList<String> getLearningOutcomeList() {
		return this.learningOutcomeList;
	}

	public ArrayList<String> getCourseContent() {
		return this.courseContent;
	}

	public Lecturer getCompiler() {
		return this.compiler;
	}

	public ArrayList<Assessment> getGradeList() {
		return this.gradeList;
	}

	public EducationalSystem getEducationalSystem() {
		return this.educationalSystem;
	}

	public void setEducationalSystem(EducationalSystem e) {
		this.educationalSystem = e;
	}

	public boolean hasEnoughGrades() {
		return this.gradeList.size() >= MIN_GRADES && this.gradeList.size() <= MAX_GRADES;
	}

	public void setCourseObjectiveList(ArrayList<String> objectives) {
		this.courseObjectiveList = objectives;
	}

	public void setLearningOutcomeList(ArrayList<String> outcomes) {
		this.learningOutcomeList = outcomes;
	}

	public void setCourseContent(ArrayList<String> content) {
		this.courseContent = content;
	}

	/**
	 * Add new grade the the grade columns
	 * 
	 * @param type
	 *               Type of assessments
	 * @param method
	 *               Method of assessments
	 * @param weight
	 *               Weight (ratio) of this assessment
	 * @throws OutOfCapacityException
	 *                                If the number of grades is enough or the total
	 *                                weight would be greater than 100%
	 */
	public void addGrade(String type, String method, double weight) throws OutOfCapacityException {
		if (this.gradeList.size() >= MAX_GRADES)
			throw new OutOfCapacityException("The number of grades is enough.");
		double currTotalWeight = 0;
		for (Assessment i : this.gradeList)
			currTotalWeight += i.getWeight();
		if (Double.compare(currTotalWeight, TOTAL_WEIGHT) > 0
				|| Double.compare(currTotalWeight + weight, TOTAL_WEIGHT) > 0)
			throw new OutOfCapacityException("The total weight should be less than 100%");
		this.gradeList.add(new Assessment(type, method, weight));
	}

	public void addGrade(String type, String method, double weight, String content) throws OutOfCapacityException {
		this.addGrade(type, method, weight);
	}

	public void removeGrade(int position) {
		if (position >= 0 && position < this.gradeList.size()) {
			this.gradeList.remove(position);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || this.getClass() != o.getClass())
			return false;
		CourseOutline outline = (CourseOutline) o;
		return this.course.equals(outline.course) && this.educationalSystem.equals(outline.educationalSystem);
	}

	@Override
	public String toString() {
		return this.course.toString() + "_" + this.educationalSystem.getTypeName();
	}
}
