package com.courseoutlinemanager.courseoutline;

import com.courseoutlinemanager.lecturer.Lecturer;
import com.courseoutlinemanager.course.Course;

import com.courseoutlinemanager.assessment.*;
import com.courseoutlinemanager.common.customexception.*;

import java.util.ArrayList;

public class CourseOutline {

	private static final int MIN_GRADES = 2;

	private static final int MAX_GRADES = 4;

	private static final double TOTAL_WEIGHT = 1;

	private Course course;

	private Lecturer compiler;

	private ArrayList<String> courseObjectives;

	private ArrayList<String> learningOutcomes;

	private ArrayList<String> courseContent;

	private ArrayList<Assessment> grades;

	public CourseOutline() {
		this.courseObjectives = new ArrayList<>();
		this.learningOutcomes = new ArrayList<>();
		this.courseContent = new ArrayList<>();
		this.grades = new ArrayList<>();
	}

	public CourseOutline(Course course, Lecturer compiler) {
		this.course = course;
		this.compiler = compiler;
	}

	public CourseOutline(Course course, Lecturer compiler, ArrayList<Assessment> grades) {
		this.course = course;
		this.compiler = compiler;
		this.grades = new ArrayList<>();
	}

	public Course getCourse() {
		return this.course;
	}

	public ArrayList<String> getCourseObjectives() {
		return this.courseObjectives;
	}

	public ArrayList<String> getLearningOutcomes() {
		return this.learningOutcomes;
	}

	public ArrayList<String> getCourseContent() {
		return this.courseContent;
	}

	public Lecturer getCompiler() {
		return this.compiler;
	}

	public ArrayList<Assessment> getGrades() {
		return this.grades;
	}

	public boolean hasEnoughGrades() {
		return this.grades.size() >= MIN_GRADES && this.grades.size() <= MAX_GRADES;
	}

	public void setCourseObjectives(ArrayList<String> objectives) {
		this.courseObjectives = objectives;
	}

	public void setLearningOutcomes(ArrayList<String> outcomes) {
		this.learningOutcomes = outcomes;
	}

	public void setCourseContent(ArrayList<String> content) {
		this.courseContent = content;
	}

	/**
	 * Add new grade the the grade columns
	 * @param type
	 * Type of assessments
	 * @param method
	 * Method of assessments
	 * @param weight
	 * Weight (ratio) of this assessment
	 * @throws OutOfCapacityException
	 * If the number of grades is enough or the total weight would be greater than 100%
	 */
	public void addGrade(String type, String method, double weight) throws OutOfCapacityException {
		if (this.grades.size() >= MAX_GRADES)
			throw new OutOfCapacityException("The number of grades is enough.");
		double currTotalWeight = 0;
		for (Assessment i : this.grades)
			currTotalWeight += i.getWeight();
		if (Double.compare(currTotalWeight, TOTAL_WEIGHT) > 0
				|| Double.compare(currTotalWeight + weight, TOTAL_WEIGHT) > 0)
			throw new OutOfCapacityException("The total weight would be greater than 100%");
		this.grades.add(new Assessment(type, method, weight));
	}

	public void addGrade(String type, String method, double weight, String content) throws OutOfCapacityException {
		this.addGrade(type, method, weight);
	}

	public void removeGrade(int position) {
		if (position >= 0 && position < this.grades.size()) {
			this.grades.remove(position);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) 
			return true;
		if (o == null || this.getClass() != o.getClass())
			return false;
		CourseOutline outline = (CourseOutline) o;
		return this.course.equals(outline.course) && this.compiler.equals(outline.compiler);
	}
}
