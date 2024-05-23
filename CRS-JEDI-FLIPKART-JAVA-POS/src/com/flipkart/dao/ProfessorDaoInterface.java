package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;

public interface ProfessorDaoInterface {
	
	public List<Course> getCoursesByProfessor(String userId);
	
	
	
	public List<EnrolledStudent> getEnrolledStudents(String courseId);
	
	
	public Boolean addGrade(String studentId,String courseCode,String grade);


	
	public String getProfessorById(String profId);

}