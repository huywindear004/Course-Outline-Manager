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

	private ArrayList<CourseOutline> courseOutlineList;

	public Lecturer() {
		courseOutlineList = new ArrayList<>();
	}

	public Lecturer(String name) {
		this();
		this.lecturerName = name;
		this.lecturerId = String.format("LECT%03d", idCount++);
	}

	public Lecturer(String name, String id) {
		this();
		this.lecturerName = name;
		this.lecturerId = id;
	}

	public String getName() {
		return this.lecturerName;
	}

	public String getId() {
		return this.lecturerId;
	}

	public ArrayList<CourseOutline> getCourseOutlineList() {
		return this.courseOutlineList;
	}

	public void addCourseOutline(CourseOutline outline) throws OutOfCapacityException, AlreadyExistException {
		if (this.hasEnoughCourseOutlines())
			throw new OutOfCapacityException("This lecturer has had enough courseoutlines.");
		if (this.courseOutlineList.contains(outline))
			throw new AlreadyExistException("This outline is already possessed by " + lecturerName + ".");
		this.courseOutlineList.add(outline);
	}

	public boolean hasEnoughCourseOutlines() {
		return this.getCourseOutlinesNum() >= MAX_COURSEOUTLINES;
	}

	public void removeCourseOutline(CourseOutline outline) {
		this.courseOutlineList.remove(outline);
	}

	public void removeCourseOutline(String outlineName) {
		this.courseOutlineList
				.removeIf(outline -> ProcessString.equalsByAlphabet(outlineName, outline.getCourse().getCourseName()));
	}

	public boolean contains(CourseOutline outline) {
		if (this.courseOutlineList.contains(outline))
			return true;
		return false;
	}

	public int getCourseOutlinesNum() {
		return this.courseOutlineList.size();
	}

	public void sortCourseOutline() {
		this.courseOutlineList.sort((x, y) -> {
			int comp = Double.compare(y.getCourse().getCourseCredits(), x.getCourse().getCourseCredits());
			if (comp != 0)
				return comp;
			return x.getCourse().getCourseCode().compareTo(y.getCourse().getCourseCode());
		});
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || this.getClass() != o.getClass())
			return false;
		Lecturer lecturer = (Lecturer) o;
		return ProcessString.equalsByAlphabet(this.lecturerId, lecturer.lecturerId);
	}

	@Override
	public String toString() {
		return this.lecturerName + " (" + this.lecturerId + ")";
	}
}
