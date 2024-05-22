
package com.flipkart.bean;


public class Professor extends User{
	private String professorID;
	private String department;
	private String designation;
	
	
	
	
	public Professor(String userID, String name, String gender, String role, String password, String address) {
		super(userID, name, role, password, gender, address);
	}




	public String getProfessorID() {
		return professorID;
	}




	public void setProfessorID(String professorID) {
		this.professorID = professorID;
	}




	public String getDepartment() {
		return department;
	}




	public void setDepartment(String department) {
		this.department = department;
	}




	public String getDesignation() {
		return designation;
	}




	public void setDesignation(String designation) {
		this.designation = designation;
	}

	
}