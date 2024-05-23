package com.flipkart.dao;



public interface UserDaoInterface {

	public boolean verifyCredentials(String userId,String password);
	
	public boolean updatePassword(String userID);

	public String getRole(String userId);
	
	public String getName(String userId);
	
	public boolean updatePassword(String userID,String newPassword);
}