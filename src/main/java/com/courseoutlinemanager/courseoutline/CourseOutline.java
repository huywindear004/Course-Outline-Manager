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

	public CourseOutline(Course course, Lecturer compiler) {
		this();
		this.course = course;
		this.compiler = compiler;
	}

	public CourseOutline(Course course, Lecturer compiler, EducationalSystem e) {
		this(course, compiler);
		this.educationalSystem = e;
	}

	public static int getMinGradesNum() {
		return MIN_GRADES;
	}

	public static int getMaxGradesNum() {
		return MAX_GRADES;
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

	public EducationalSystem getEducationalSystem() {
		return this.educationalSystem;
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

	public ArrayList<Assessment> getGradeList() {
		return this.gradeList;
	}

	/**
	 * @return {@code -1} if {@code gradeList.size()} {@code <} {@code MIN_GRADES}.
	 *         {@code 0} if {@code gradeList.size()} in range {@code MIN_GRADES} ->
	 *         {@code MAX_GRADES}.
	 *         {@code 1} if {@code gradeList.size()} {@code ==} {@code MIN_GRADES}.
	 */
	public int hasEnoughGrades() {
		int size = this.gradeList.size();  
		if (size < MIN_GRADES)
			return -1;
		if (size < MAX_GRADES)
			return 0;
		return 1;
	}

	public void addGrade(Assessment a) throws OutOfCapacityException {
		if (this.hasEnoughGrades() == 1)
			throw new OutOfCapacityException("The grades number of " + this.toString() + "'s outline is enough.");
		double currTotalWeight = 0;
		for (Assessment i : this.gradeList)
			currTotalWeight += i.getWeight();
		if (Double.compare(currTotalWeight + a.getWeight(), TOTAL_WEIGHT) > 0)
			throw new OutOfCapacityException("The total weight of all grades should be less than 100% "
					+ String.format("[%.2f%% / 100%%]", (currTotalWeight + a.getWeight()) * 100.0));
		this.gradeList.add(a);
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
		this.addGrade(new Assessment(type, method, weight));
	}

	public void addGrade(String type, String method, double weight, String content) throws OutOfCapacityException {
		this.addGrade(new Assessment(type, method, weight, content));
	}

	public void removeGrade(int position) {
		this.gradeList.remove(position);
	}

	public Assessment getGrade(int pos) {
		return gradeList.get(pos);
	}

	public void setGradeWeight(Assessment a, double val) throws OutOfCapacityException {
		double currTotalWeight = this.gradeList.stream()
				.mapToDouble(Assessment::getWeight)
				.sum();
		if (Double.compare(currTotalWeight - a.getWeight() + val, TOTAL_WEIGHT) > 0)
			throw new OutOfCapacityException("The total weight of all grades should be less than 100% "
					+ String.format("[%.2f%% / 100%%]", (currTotalWeight + a.getWeight()) * 100.0));
		a.setWeight(val);
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
