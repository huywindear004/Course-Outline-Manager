package com.courseoutlinemanager.course.knowledgeblock;

import com.courseoutlinemanager.common.ProcessString;
import com.courseoutlinemanager.common.customexception.NotFoundException;

import java.util.ArrayList;

public class KnowledgeBlockManager {

	private static ArrayList<KnowledgeBlock> knowledgeBlockList;

	static{
		knowledgeBlockList = new ArrayList<>();
		//add discipline, foundation, major initially
		knowledgeBlockList.add(new Discipline());
		knowledgeBlockList.add(new Foundation());
		knowledgeBlockList.add(new Major());
	}

	public static KnowledgeBlock getKnowledgeBlock(String blockName) throws NotFoundException{
		for(KnowledgeBlock i : knowledgeBlockList)
			if(ProcessString.equalsByAlphabet(i.getTypeName(),blockName))
				return i;
		throw new NotFoundException();
	}
}
