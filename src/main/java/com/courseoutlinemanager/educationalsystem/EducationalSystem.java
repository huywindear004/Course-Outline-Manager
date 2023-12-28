package com.courseoutlinemanager.educationalsystem;

import com.courseoutlinemanager.course.Course;
import com.courseoutlinemanager.common.customexception.AlreadyExistException;
import com.courseoutlinemanager.common.customexception.NotFoundException;

import java.util.ArrayList;

public abstract class EducationalSystem {

	private ArrayList<Course> courseList;

	public abstract String getTypeName();   
	
	public boolean containsCourse(Course course) {
		return courseList.contains(course);
	}

	public void addCourse(Course course) throws AlreadyExistException {
		if (this.containsCourse(course))
			throw new AlreadyExistException();
		this.courseList.add(course);
	}

	public void removeCourse(Course course) throws NotFoundException {
		if(!this.containsCourse(course))
			throw new NotFoundException();
		this.courseList.remove(course);
	}

}
