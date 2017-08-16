package com.shsxt.crm.constant;

public enum ModuleGrade {
	
	root(0, "根级"),
	
	first(1, "第一级"),
	
	second(2, "第二级");
	
	
	ModuleGrade(int grade, String gradeName) {
		this.grade = grade;
		this.gradeName = gradeName;
	}
	
	private int grade;
	private String gradeName;
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
}
