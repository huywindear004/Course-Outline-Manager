package com.courseoutlinemanager.course.knowledgeblock;

import java.util.ArrayList;

import com.courseoutlinemanager.common.ProcessString;

public class KnowledgeBlockManager {

	private ArrayList<KnowledgeBlock> knowledgeBlockList;

	public KnowledgeBlockManager() {
		this.knowledgeBlockList = new ArrayList<>();
	}

	public KnowledgeBlockManager(ArrayList<KnowledgeBlock> knowledgeBlockList) {
		this.knowledgeBlockList = knowledgeBlockList;
	}

	public void addKnowledgeBlock(KnowledgeBlock block) {
		this.knowledgeBlockList.add(block);
	}

	public void removeKnowledgeBlock(KnowledgeBlock block) {
		this.knowledgeBlockList.remove(block);
	}

	public void removeKnowledgeBlock(String name) {
		this.knowledgeBlockList.removeIf(knowledgeBlock -> ProcessString.compare(name, knowledgeBlock.toString()));
	}
	
}
