package com.flipkart.dao;


import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
//import com.flipkart.bean.Grade;
import com.flipkart.bean.Grade;

public interface RegistrationDAOInterface {
	
	
	
	public boolean addCourse(String courseCode, String studentId);

	
	
	public boolean dropCourse(String courseCode, String studentId);

	
	public List<Course> viewCourses(String studentId);

	
	
	public List<Course> viewRegisteredCourses(String studentId) ;

	
	public double calculateFee(String studentId) ;

	
	public boolean seatAvailable(String courseCode);

	
	public int numOfRegisteredCourses(String studentId) ;

	
	public boolean isRegistered(String courseCode, String studentId);

	
	
	public boolean getRegistrationStatus(String studentId) ;
	
	public boolean getPaymentStatus(String studentId);

	
	public void setRegistrationStatus(String studentId);


	public List<Grade> viewGradeCard(String studentId) ;


	public boolean isReportGenerated(String studentId) ;


	public void setPaymentStatus(String studentId) ;

	
}