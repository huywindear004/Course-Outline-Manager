package com.courseoutlinemanager.course.knowledgeblock;

public enum KnowledgeBlock {
	NULL,

	FOUNDATION,

	DISCIPLINE,

	MAJOR;

	public String toString() {
		return super.toString().replaceAll("_", " ");
	}
}
