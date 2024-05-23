package com.flipkart.dao;

import com.flipkart.bean.Student;

public interface StudentDaoInterface {
	public static  String addStudent(Student student) {
		return null;
	}
	
	public String getStudentId(String userId);
	
	public boolean isApproved(String studentId);
	
}
