package com.courseoutlinemanager.course.knowledgeblock;

import java.util.ArrayList;

import com.courseoutlinemanager.common.ProcessString;

public class KnowledgeBlockManager {

	private ArrayList<KnowledgeBlock> knowledgeBlockList;

	public KnowledgeBlockManager() {
		this.knowledgeBlockList = new ArrayList<>();
		this.knowledgeBlockList.add(new Discipline());
		this.knowledgeBlockList.add(new Foundation());
		this.knowledgeBlockList.add(new Major());
	}

	public void addKnowledgeBlock(KnowledgeBlock block) {
		this.knowledgeBlockList.add(block);
	}

	public void removeKnowledgeBlock(KnowledgeBlock block) {
		this.knowledgeBlockList.remove(block);
	}

	public void removeKnowledgeBlock(String name) {
		this.knowledgeBlockList.removeIf(knowledgeBlock -> ProcessString.equalsByAlphabet(name, knowledgeBlock.toString()));
	}

	public KnowledgeBlock getKnowledgeBlock(String name){
		return null;
	}
}
