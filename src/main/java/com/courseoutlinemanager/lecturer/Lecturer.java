package com.courseoutlinemanager.lecturer;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.common.customexception.*;

import java.util.ArrayList;

public class Lecturer {
	private static int idCount = 1;

	private static final int MAX_COURSEOUTLINES = 5;

	private String lecturerName;

	private String lecturerId;

	private ArrayList<CourseOutline> courseOutlines;

	public Lecturer() {
		courseOutlines = new ArrayList<>();
	}

	public Lecturer(String name) {
		this(name, String.format("LEC%05d", idCount++));
	}

	//Prevent assign id manually
	private Lecturer(String name, String id) {
		this.lecturerName = name;
		this.lecturerId = id;
	}

	public String getName() {
		return this.lecturerName;
	}

	public String getId() {
		return this.lecturerId;
	}

	public ArrayList<CourseOutline> getCourseOutlines() {
		return this.courseOutlines;
	}

	public void addCourseOutline(CourseOutline outline) throws OutOfCapacityException, AlreadyExistException {
		if (this.hasEnoughCourseOutlines())
			throw new OutOfCapacityException("This lecturer has had enough courseoutlines.");
		if (this.courseOutlines.contains(outline))
			throw new AlreadyExistException("This outline is already possessed by "+lecturerName+".");
		this.courseOutlines.add(outline);
	}

	public boolean hasEnoughCourseOutlines() {
		return this.getCourseOutlinesNum() >= MAX_COURSEOUTLINES;
	}

	public void removeCourseOutline(CourseOutline outline) {
		this.courseOutlines.remove(outline);
	}

	public void removeCourseOutline(String outlineName) {
		this.courseOutlines
				.removeIf(outline -> ProcessString.equalsByAlphabet(outlineName, outline.getCourse().getCourseName()));
	}

	public boolean contains(CourseOutline outline) {
		if (this.courseOutlines.contains(outline))
			return true;
		return false;
	}

	public int getCourseOutlinesNum() {
		return this.courseOutlines.size();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) 
			return true;
		if (o == null || this.getClass() != o.getClass())
			return false;
		Lecturer lecturer = (Lecturer) o;
		return ProcessString.equalsByAlphabet(this.lecturerId, lecturer.lecturerId)
		|| ProcessString.equalsByAlphabet(this.lecturerName, lecturer.lecturerName);
	}
}
 
