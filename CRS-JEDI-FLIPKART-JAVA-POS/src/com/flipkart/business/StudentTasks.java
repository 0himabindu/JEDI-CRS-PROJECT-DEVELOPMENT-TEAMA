package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.exception.StudentNotRegisteredException;

import java.util.*;

public class StudentTasks implements StudentInterface{
	private static volatile StudentTasks instance=null;
	public Vector <Student> studentslist = new Vector <Student>();
	

	@Override
	public String register(String name,String userId,String password,String gender,int batch,String branch,String address){
		String studentId;
		Student newStudent=new Student(userId,name,"STUDENT",password,gender,address,branch,userId,batch,false);
		System.out.println("\nYour account has been created and pending for Approval by Admin.\n");
		studentId=StudentDaoInterface.addStudent(newStudent);
		return studentId;
	}

	@Override
	public int getStudentlist() {
		// TODO Auto-generated method stub
		for (Student student : studentslist) {
			System.out.println("Details of the customer are: " + student.getName() + " "
					+ student.getStudentId() + " " + student.getUserId());
		}
		return 0;
	}
	
	@Override
	public boolean isApproved(String studentId) {
		return true;
	}

}
