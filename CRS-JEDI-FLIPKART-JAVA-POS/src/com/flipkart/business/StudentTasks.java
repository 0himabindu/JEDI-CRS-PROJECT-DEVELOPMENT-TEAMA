package com.flipkart.business;

import com.flipkart.bean.Student;
import java.util.*;

public class StudentTasks implements StudentInterface{
	public Vector <Student> studentslist = new Vector <Student>();


	public String register(String name,String userId,String password,String gender,int batch,String branch,String address) 
	{
		String studentId = userId;
			//call the DAO class, and add the student record to the DB
		System.out.println("1 reg");
		Student newStudent=new Student(userId,name,"STUDENT",password,gender,address,branch,userId,batch,false);
		System.out.println("\nYour account has been created and pending for Approval by Admin.\n");
		studentslist.add(newStudent);
			
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

}
