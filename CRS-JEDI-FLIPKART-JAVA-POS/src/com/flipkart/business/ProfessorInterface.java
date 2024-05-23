package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.List;

public interface ProfessorInterface {
	

	public List<Course> getCourses(String profId);
	
	
	public String getProfessorById(String profId);
}