package com.flipkart.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoImpl;
import com.flipkart.exception.GradeNotAllotedException;

/**
 *
 */

public class ProfessorTasks implements ProfessorInterface {
	
	private static volatile ProfessorTasks instance=null;
	ProfessorDaoInterface professorDAOInterface=ProfessorDaoImpl.getInstance();
	private ProfessorTasks()
	{

	}
	
	/**
	 * Method to make ProfessorOperation Singleton
	 * @return
	 */
	public static ProfessorTasks getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(ProfessorTasks.class){
				instance=new ProfessorTasks();
			}
		}
		return instance;
	}
	

	@Override
	
	public boolean addGrade(String studentId,String courseCode,String grade) throws GradeNotAllotedException {
		try
		{
			professorDAOInterface.addGrade(studentId, courseCode, grade);
		}
		catch(Exception ex)
		{
			throw new GradeNotAllotedException(studentId);
		}
		return true;
	}
	
	
	/**
	 * Method to view all the enrolled students
	 * @param courseId: Course id 
	 * @return List of enrolled students
	 */
	
	@Override
	public List<EnrolledStudent> viewEnrolledStudents(String profId) throws SQLException{
		List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
		try
		{
			enrolledStudents=professorDAOInterface.getEnrolledStudents(profId);
		}
		catch(Exception ex)
		{
			throw ex;
		}
		return enrolledStudents;
	}

	
	
	@Override
	public List<Course> viewCourses(String profId) {
		//call the DAO class
		//get the courses for the professor
		List<Course> coursesOffered=new ArrayList<Course>();
		try
		{
			coursesOffered=professorDAOInterface.getCoursesByProfessor(profId);
		}
		catch(Exception ex)
		{
			throw ex;
		}
		return coursesOffered;
	}
	
	
	@Override
	public String getProfessorById(String profId)
	{
		return professorDAOInterface.getProfessorById(profId);
	}

	
}