package com.flipkart.bean;

import java.util.List;



public class GradeCard 
{
	Student stud;
	int sem;
	float cpi;
	List<String> reg_list ;

	public Student getStud() {
		return stud;
	}

	public void setStud(Student stud) {
		this.stud = stud;
	}


	public int getSem() {
		return sem;
	}

	void setSem(int sem) {
		this.sem = sem;
	}

	
	public float cpi() {
		return cpi;
	}

	
	public void setCgpa(float cpi) {
		this.cpi = cpi;
	}

	
	public List<String> getReg_list() {
		return reg_list;
	}

	
	public void setReg_list(List<String> reg_list) {
		this.reg_list = reg_list;
	}
	
	
}