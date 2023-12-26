package com.courseoutlinemanager.course.knowledgeblock;

public class Major extends KnowledgeBlock{
    public Major(String nameKnowledge) {
        super(nameKnowledge);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
