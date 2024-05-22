package com.flipkart.business;

public interface StudentInterface {
	
	public int register(String name, int User_Id, String password, String address,String branch);
	
	public int getStudentId(String userId);
		
}
