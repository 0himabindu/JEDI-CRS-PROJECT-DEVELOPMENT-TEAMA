package com.flipkart.dao;

import com.flipkart.bean.Student;

public interface StudentDaoInterface {
	public String addStudent(Student student);
	
	public String getStudentId(String userId);
	
	public boolean isApproved(String studentId);
	
}
