/**
 * 
 */
package com.flipkart.business;
import java.util.*;


import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;

/**
 * 
 */
public interface RegistrationInterface {
	
	public boolean addCourse(String courseCode, String studentId, List<Course> availableCourseList);
	/**
	 * Method to set student registration status
	 * @param studentId
	 * @throws SQLException
	 */
	void setRegistrationStatus(String studentId);

	/**
	 *  Method to check student registration status
	 * @param studentId
	 * @return boolean indicating if the student's registration status
	 * @throws SQLException
	 */
	boolean getRegistrationStatus(String studentId);
	
	boolean dropCourse(String courseCode, String studentId, List<Course> registeredCourseList);
	
	
	
	boolean getPaymentStatus(String studentId);

	/**
	 * Method to view the list of courses registered by the student
	 * @param studentId
	 * @return List of courses
	 * @throws SQLException 
	 */
	List<Course> viewRegisteredCourses(String studentId);

	/**
	 *  Method to view the list of available courses
	 * @param studentId
	 * @return List of courses
	 * @throws SQLException 
	 */
	List<Course> viewCourses(String studentId);
	/**
	 * Method to view grade card for students
	 * @param studentId
	 * @return List of Student's Grades
	 * @throws SQLException 
	 */
	List<Grade> viewGradeCard(String studentId);

	/** Method for Fee Calculation for selected courses
	 * Fee calculation for selected courses
	 * @param studentId
	 * @return Fee Student has to pay
	 * @throws SQLException 
	 */
	double calculateFee(String studentId);

	/**
	 *  Method to drop Course selected by student
	 * @param courseCode
	 * @param studentId
	 * @param registeredCourseList 
	 * @return boolean indicating if the course is dropped successfully
	 * @throws CourseNotFoundException
	 * @throws SQLException 
	 */
	

	public boolean isReportGenerated(String studentId);

	public void setPaymentStatus(String studentId);

}