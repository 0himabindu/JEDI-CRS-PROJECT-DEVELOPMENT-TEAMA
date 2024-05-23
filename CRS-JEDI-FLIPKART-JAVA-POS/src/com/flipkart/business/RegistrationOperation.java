package com.flipkart.business;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.dao.RegistrationDAOInterface;
import com.flipkart.dao.RegistrationDaoImpl;



public class RegistrationOperation implements RegistrationInterface {

	private static volatile RegistrationOperation instance = null;

	private RegistrationOperation() {
	}

	/**
	 * Method to make Registration Operation Singleton
	 * 
	 * @return
	 */
	public static RegistrationOperation getInstance() {
		if (instance == null) {
			synchronized (RegistrationOperation.class) {
				instance = new RegistrationOperation();
			}
		}
		return instance;
	}

	RegistrationDAOInterface registrationDaoInterface = RegistrationDaoImpl.getInstance();

	
	@Override
	
	public boolean addCourse(String courseCode, String studentId,List<Course> availableCourseList)
	{
       
		

		if (registrationDaoInterface.numOfRegisteredCourses(studentId) >= 6)
		{	
//			throw new CourseLimitExceededException(6);
		}
		else if (registrationDaoInterface.isRegistered(courseCode, studentId)) 
		{
			return false;
		} 
		else if (!registrationDaoInterface.seatAvailable(courseCode)) 
		{
//			throw new SeatNotAvailableException(courseCode);
		} 
		
		  

		return registrationDaoInterface.addCourse(courseCode, studentId);

	}

	
	@Override
	
	public boolean dropCourse(String courseCode, String studentId,List<Course> registeredCourseList) {
		 
		
		return registrationDaoInterface.dropCourse(courseCode, studentId);

	}

	
	@Override
	
	public double calculateFee(String studentId) {
		return registrationDaoInterface.calculateFee(studentId);
	}


	/**
	 * Method to view grade card for students
	 * @param studentId
	 * @return List of Student's Grades
	 * @throws SQLException 
	 */
	@Override
	
	public List<Grade> viewGradeCard(String studentId)  {
		return registrationDaoInterface.viewGradeCard(studentId);
	}

	/**
	 *  Method to view the list of available courses
	 * @param studentId
	 * @return List of courses
	 * @throws SQLException 
	 */
	@Override
	
	public List<Course> viewCourses(String studentId)  {
		return registrationDaoInterface.viewCourses(studentId);
	}

	/**
	 * Method to view the list of courses registered by the student
	 * @param studentId
	 * @return List of courses
	 * @throws SQLException 
	 */
	@Override
	
	public List<Course> viewRegisteredCourses(String studentId)  {
		return registrationDaoInterface.viewRegisteredCourses(studentId);
	}
    
	/**
	 *  Method to check student registration status
	 * @param studentId
	 * @return boolean indicating if the student's registration status
	 * @throws SQLException
	 */
	@Override

	public boolean getRegistrationStatus(String studentId)  {
		return registrationDaoInterface.getRegistrationStatus(studentId);
	}
	
	/**
	 * Method to set student registration status
	 * @param studentId
	 * @throws SQLException
	 */
	@Override
	
	public void setRegistrationStatus(String studentId)  {
		registrationDaoInterface.setRegistrationStatus(studentId);

	}

	@Override
	public boolean isReportGenerated(String studentId)  {
		
		return registrationDaoInterface.isReportGenerated(studentId);
	}

	@Override
	public boolean getPaymentStatus(String studentId)
	{
		return registrationDaoInterface.getPaymentStatus(studentId);
		
	}

	@Override
	public void setPaymentStatus(String studentId) {
		registrationDaoInterface.setPaymentStatus(studentId);
		
	}

	

}