package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.*;

public interface AdminDaoInterface {
	
	public List<Course> viewCourses();
	
	public List<Professor> viewProfessors();
		
	public List<RegisteredCourse> generateGradeCard(String Studentid);
	
	public List<Student> viewPendingAdmissions();
	
	public void approveStudent(String studentid);
	
	public void addProfessor(Professor professor);
	
	public void removeCourse(String coursecode);
	
	public void addCourse(Course course);

	public void assignCourse(String courseCode, String professorId);
	
	public void addUser(User user);
}