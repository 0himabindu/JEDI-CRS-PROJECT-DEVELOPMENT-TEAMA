
package com.flipkart.business;

import java.util.List;


import com.flipkart.bean.*;


public interface AdminInterface 
{
	
	public List<Course> viewCourses();

	public List<Professor> viewProfessors();
	
	public List<Student> viewPendingAdmissions();

	public List<RegisteredCourse> generateGradeCard(String Studentid);
	
	public void approveStudent(String studentid, List<Student> studentlist);
	
	public void addProfessor(Professor professor);
	
	public void removeCourse(String coursecode);
	
	public void addCourse(Course course, List<Course> courselist);
	
	public void assignCourse(String courseCode, String professorId);
	
}