package com.courseoutlinemanager.course.knowledgeblock;

public abstract class KnowledgeBlock {
	String nameKnowledge;
	@Override
	public abstract String toString();
	public KnowledgeBlock(String nameKnowledge) {
		this.nameKnowledge = nameKnowledge;
	}

}
