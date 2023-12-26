package com.courseoutlinemanager.course.knowledgeblock;

public class Discipline extends KnowledgeBlock {

    public Discipline(String nameKnowledge) {
        super(nameKnowledge);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
