package com.courseoutlinemanager.assessment;

public enum AssessmentTypes {
	NULL,

	FORMATIVE,

	MID_TERM,

	END_OF_COURSE;

	public String toString() {
		return super.toString().replaceAll("_", " ");
	}

}
