package com.flipkart.business;

public interface StudentInterface {
	
	public int register(String name, String User_Id, String password, String address, String role, int student_Id, int batch, boolean isApproved, String branchName);
	
	public int getStudentlist();
		
}
