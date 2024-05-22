package com.flipkart.business;

import com.flipkart.bean.Student;
import java.util.*;

public class StudentTasks implements StudentInterface{
	public Vector <Student> studentslist = new Vector <Student>();


	public int register(String name, String User_Id, String password, String address, String role, int student_Id, int batch, boolean isApproved, String branchName)
	{
		Student newstd = new Student(User_Id, name, password, address, branchName, student_Id, batch, isApproved, role); 
		studentslist.add(newstd);
		System.out.println("New user added");
		return 0;
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
