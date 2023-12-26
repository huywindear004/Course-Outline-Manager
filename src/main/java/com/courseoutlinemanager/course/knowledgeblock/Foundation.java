package com.courseoutlinemanager.course.knowledgeblock;

public class Foundation extends KnowledgeBlock {
    public Foundation(String nameKnowledge) {
        super(nameKnowledge);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
