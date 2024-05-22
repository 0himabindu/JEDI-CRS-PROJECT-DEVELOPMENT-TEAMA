package com.flipkart.business;


public interface UserInterface {

	String getRole(String userId);
	
	String getName(String userId);

	boolean verifyCredentials(String userID, String password);

	
	boolean updatePassword(String userID, String newPassword);
	
}