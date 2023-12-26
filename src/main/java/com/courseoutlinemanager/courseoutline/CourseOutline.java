package com.courseoutlinemanager.courseoutline;

import com.courseoutlinemanager.lecturer.Lecturer;
import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.assessment.*;

import java.util.ArrayList;

public class CourseOutline {

	private static final int MIN_GRADES = 2;

	private static final int MAX_GRADES = 4;

	private Course course;

	private Lecturer compiler;

	private ArrayList<String> courseObjectives;

	private ArrayList<String> learningOutcomes;

	private ArrayList<String> courseContent;

	private ArrayList<Assessment> grades;

	public CourseOutline(Course course, Lecturer compiler) {

	}

	public CourseOutline(Course course, Lecturer compiler, ArrayList<Assessment> grades) {

	}

	public Course getCourse() {
		return null;
	}

	public ArrayList<String> getCourseObjectives() {
		return null;
	}

	public ArrayList<String> getLearningOutcomes() {
		return null;
	}

	public ArrayList<String> getCourseContent() {
		return null;
	}

	public Lecturer getInstructor() {
		return null;
	}

	public ArrayList<Assessment> getGrades() {
		return null;
	}

	public int hasEnoughGrades() {
		return 0;
	}

	public void setCourseObjectives(ArrayList<String> objectives) {

	}

	public void setLearningOutcomes(ArrayList<String> outcomes) {

	}

	public void setCourseContent(ArrayList<String> content) {

	}

	public void addGrade(String type, String method, double weight) {

	}

	public void addGrade(String type, String method, double weight, String content) {

	}

	public void removeGrade(int position) {

	}

}
