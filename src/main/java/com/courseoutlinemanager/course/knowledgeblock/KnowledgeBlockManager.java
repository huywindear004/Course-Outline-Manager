package com.courseoutlinemanager.course.knowledgeblock;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.common.customexception.NotFoundException;

import java.util.ArrayList;

public class KnowledgeBlockManager {

	private ArrayList<KnowledgeBlock> knowledgeBlockList;

	public KnowledgeBlockManager(){
		knowledgeBlockList = new ArrayList<>();
		//add discipline, foundation, major initially
		knowledgeBlockList.add(new Discipline());
		knowledgeBlockList.add(new Foundation());
		knowledgeBlockList.add(new Major());
	}

	public KnowledgeBlock getKnowledgeBlock(String blockName) throws NotFoundException{
		for(KnowledgeBlock i : knowledgeBlockList)
			if(ProcessString.equalsByAlphabet(i.getTypeName(),blockName))
				return i;
		throw new NotFoundException("Couldn't find "+blockName);
	}
}
