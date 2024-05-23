package com.flipkart.business;

import java.util.List;
import java.util.Vector;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoImpl;
import com.flipkart.dao.AdminDaoInterface;

public class AdminOperation implements AdminInterface {
	
	private static volatile AdminOperation instance = null;
	
	public AdminOperation()
	{
		
	}
	
	
	public static AdminOperation getInstance()
	{
		if(instance == null)
		{
			synchronized(AdminOperation.class){
				instance = new AdminOperation();
			}
		}
		return instance;
	}
	
	AdminDaoInterface adminDaoOperation =AdminDaoImpl.getInstance();

	
	@Override
	public List<Course> viewCourses()
	{
		return adminDaoOperation.viewCourses();
	}
	@Override
	public List<Professor> viewProfessors()
	{
		return adminDaoOperation.viewProfessors();
	}
	

	@Override
	public List<Student> viewPendingAdmissions() {
		return adminDaoOperation.viewPendingAdmissions();
	}
	
	@Override
	public List<RegisteredCourse> generateGradeCard(String Studentid)
	{
		return adminDaoOperation.generateGradeCard(Studentid);
	}
	
	@Override
	public void removeCourse(String dropCourseCode) 
	{	
		adminDaoOperation.removeCourse(dropCourseCode);
	}

	@Override
	public void addCourse(Course newCourse, List<Course> courseList)
	{
		adminDaoOperation.addCourse(newCourse);
	}
	
	@Override
	public void approveStudent(String studentId, List<Student> studentList) 
	{
		adminDaoOperation.approveStudent(studentId);
	}

	@Override
	public void addProfessor(Professor professor) {
			adminDaoOperation.addProfessor(professor);
	}
	
	@Override
	public void assignCourse(String courseCode, String professorId) 
	{
		adminDaoOperation.assignCourse(courseCode, professorId);
	}




	

}
