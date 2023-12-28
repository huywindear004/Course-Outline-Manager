package com.courseoutlinemanager.course.knowledgeblock;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.common.customexception.NotFoundException;

import java.util.ArrayList;

public class KnowledgeBlockManager {

	private ArrayList<KnowledgeBlock> knowledgeBlockList;

	public KnowledgeBlockManager() {
		this.knowledgeBlockList = new ArrayList<>();
		//add discipline, foundation, major initially
		this.knowledgeBlockList.add(new Discipline());
		this.knowledgeBlockList.add(new Foundation());
		this.knowledgeBlockList.add(new Major());
	}

	public KnowledgeBlock getKnowledgeBlock(String blockName) throws NotFoundException{
		for(KnowledgeBlock i : this.knowledgeBlockList)
			if(ProcessString.equalsByAlphabet(i.getTypeName(),blockName))
				return i;
		throw new NotFoundException();
	}
}
