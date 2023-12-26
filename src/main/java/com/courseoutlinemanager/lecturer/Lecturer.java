package com.courseoutlinemanager.lecturer;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.common.customexception.*;

import java.util.ArrayList;

public class Lecturer {

	private static final int MAX_COURSEOUTLINES = 5;

	private String lecturerName;

	private String lecturerId;

	private ArrayList<CourseOutline> courseOutlines = new ArrayList<>();

	public Lecturer(String name, String id) {
		this.lecturerName = name;
		this.lecturerId = id;
	}

	public String getName() {
		return this.lecturerName;
	}

	public String getId() {
		return this.lecturerId;
	}

	public void addCourseOutline(CourseOutline outline) throws OutOfCapacityException, AlreadyExistException {
		if (!this.hasEnoughCourseOutlines())
			throw new OutOfCapacityException();
		if (this.contains(outline))
			throw new AlreadyExistException();
		this.courseOutlines.add(outline);
	}

	public void removeCourseOutline(CourseOutline outline) {
		this.courseOutlines.remove(outline);
	}

	public void removeCourseOutline(String outlineName) {
		this.courseOutlines.removeIf(outline -> ProcessString.compare(outlineName, outline.toString()));
	}

	public boolean contains(CourseOutline outline) {
		if (this.courseOutlines.contains(outline))
			return true;
		return false;
	}

	public boolean contains(String outlineName, String courseId) {
		for (CourseOutline outline : this.courseOutlines)
			if (ProcessString.compare(outline.getCourse().getCourseName(), outlineName)
					&& ProcessString.compare(outline.getCourse().getCourseCode(), courseId))
				return true;
		return false;
	}

	public int getCourseOutlinesNum() {
		return this.courseOutlines.size();
	}

	public boolean hasEnoughCourseOutlines() {
		if (this.getCourseOutlinesNum() <= MAX_COURSEOUTLINES)
			return false;
		return true;
	}

}
