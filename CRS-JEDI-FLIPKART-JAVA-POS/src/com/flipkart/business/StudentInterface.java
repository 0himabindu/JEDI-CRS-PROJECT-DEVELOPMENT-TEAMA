package com.flipkart.business;

public interface StudentInterface {
	
	public String register(String name,String userID,String password,String gender,int batch,String branch,String address); 	
	public int getStudentlist();
	
	public  boolean isApproved(String studentId);
		
}
