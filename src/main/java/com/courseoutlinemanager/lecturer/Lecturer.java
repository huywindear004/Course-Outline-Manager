package com.courseoutlinemanager.lecturer;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.courseoutline.CourseOutline;
import com.courseoutlinemanager.common.customexception.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

	public ArrayList<CourseOutline> sortCourseOutline() {
		ArrayList<CourseOutline> sortedList = new ArrayList<>(courseOutlineList);
		Comparator<CourseOutline> comparator = new Comparator<CourseOutline>() {
			@Override
			public int compare(CourseOutline outline1, CourseOutline outline2) {

				double credit1 = outline1.getCourse().getCourseCredits();
				double credit2 = outline2.getCourse().getCourseCredits();

				int creditComparison = Double.compare(credit2, credit1);

				if (creditComparison == 0) {
					return outline1.getCourse().getCourseCode().compareTo(outline2.getCourse().getCourseCode());
				}
				return creditComparison;
			}
		};

		Collections.sort(this.courseOutlineList, comparator);

		return sortedList;
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
