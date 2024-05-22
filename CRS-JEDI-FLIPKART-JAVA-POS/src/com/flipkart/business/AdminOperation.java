/**
 * 
 */
package com.flipkart.business;

import java.util.Vector;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

/**
 * 
 */
public class AdminOperation implements AdminInterface {
	public Vector <Professor> Professorslist = new Vector <Professor>();

	public boolean addProfessor(String userID, String name, String gender, String role, String password, String address) {
		// TODO Auto-generated method stub
		Professor newpf = new Professor(userID, name, gender, role, password, address); 
		Professorslist.add(newpf);
		System.out.println("New Professor added");

		return false;
	}
	
	public int getStudentlist() {
		// TODO Auto-generated method stub
		for (Professor professor: Professorslist) {
			System.out.println("Details of the customer are: " + professor.getName() + " "
					+ professor.getDepartment() + " " + professor.getDepartment());
		}
		return 0;
	}

	@Override
	public boolean assignCourse(int professorId, String courseCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean approveStudent(int studentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateDetails() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCourse(Course newCourse) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCourse(String courseCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean generateReportCard() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
