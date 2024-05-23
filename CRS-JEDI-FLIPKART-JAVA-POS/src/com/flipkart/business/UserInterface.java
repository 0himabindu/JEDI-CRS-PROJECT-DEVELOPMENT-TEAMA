package com.flipkart.business;


public interface UserInterface {

	String getRole(String userID);
	
	String getName(String userID);

	boolean verifyCredentials(String userID, String password);

	
	boolean updatePassword(String userID, String newPassword);
	
}