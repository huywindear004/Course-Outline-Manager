package com.courseoutlinemanager.educationalsystem;

import java.util.ArrayList;
import com.courseoutlinemanager.course.Course;

public abstract class EducationalSystem {

	private ArrayList<Course> courseList;

	public boolean contains(Course course) {
		return courseList.contains(course);
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Courses in the system:\n");
		for (Course course : courseList) {
			stringBuilder.append(course.toString()).append("\n");
		}
		return stringBuilder.toString();
	}

	public void addCourse(Course course) {
		if (!courseList.contains(course)) {
			courseList.add(course);
			System.out.println("Course added: " + course.getCourseName());
		} else {
			System.out.println("Course already exists: " + course.getCourseName());
		}
	}

	public void removeCourse(Course course) {
		if (courseList.contains(course)) {
			courseList.remove(course);
			System.out.println("Course removed: " + course.getCourseName());
		} else {
			System.out.println("Course not found: " + course.getCourseName());
		}
	}

}
