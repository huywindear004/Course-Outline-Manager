package com.courseoutlinemanager.assessment;

import com.courseoutlinemanager.common.ProcessString;

public class Assessment {
	private double weight;
	private String content;
	private AssessmentTypes assessingType = AssessmentTypes.NULL;
	private AssessmentMethods assessingMethod = AssessmentMethods.NULL;

	public Assessment(String type, String method, double weight) {
		this.setAssessingType(type);
		this.setAssessingMethod(method);
		this.weight = weight;
	}

	public Assessment(String type, String method, double weight, String content) {
		this.setAssessingType(type);
		this.setAssessingMethod(method);
		this.weight = weight;
		this.content = content;
	}
	

	@Override
	public String toString() {
		return String.format("[Type: %s] [Method: %s] [Weight: %.1f%%] [Content: %s]", assessingType, assessingMethod,
				weight * 100.0, content);  
	}

	public double getWeight() {
		return this.weight;
	}

	public String getContent() {
		return this.content;
	}

	public AssessmentTypes getAssessingType() {
		return this.assessingType;
	}

	public AssessmentMethods getAssessingMethod() {
		return this.assessingMethod;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}



	// Check type when setting
	public void setAssessingType(String type) {
		for (AssessmentTypes i : AssessmentTypes.values()) {
			if (ProcessString.equalsByAlphabet(type, i.toString()))
				this.assessingType = i;
		}
	}
	//Check method when setting
	public void setAssessingMethod(String method) {
		for (AssessmentMethods i : AssessmentMethods.values()) {
			if (ProcessString.equalsByAlphabet(method, i.toString()))
				this.assessingMethod = i;
		}
	}
}