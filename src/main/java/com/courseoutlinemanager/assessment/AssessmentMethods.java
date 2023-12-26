package com.courseoutlinemanager.assessment;

public enum AssessmentMethods {
	NULL,

	ATTENDANCE,

	OBJECTIVE_EXAM,

	SUBJECTIVE_EXAM,

	PRACTICAL_EXAM,

	MAJOR_ASSIGNMENT,

	PROJECT;

	public String toString() {
		return super.toString().replaceAll("_", " ");
	}

}
