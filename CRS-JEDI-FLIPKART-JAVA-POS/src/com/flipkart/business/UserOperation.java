package com.flipkart.business;

import com.flipkart.dao.UserDAOInterface;
import com.flipkart.dao.UserDAOImple;
import com.flipkart.exception.UserNotFoundException;



public class UserOperation implements UserInterface {
	
	private static volatile UserOperation instance=null;
	UserDAOInterface userDaoInterface= UserDAOImple.getInstance();
	private UserOperation()
	{
		
	}
	
	/**
	 * Method to make UserOperation Singleton
	 * @return
	 */
	public static UserOperation getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(UserOperation.class){
				instance=new UserOperation();
			}
		}
		return instance;
	}

	/**
	 * Method to update password of a user
	 * @param userID
	 * @param newPassword
	 * @return boolean indicating if the password is updated successfully
	 */
	
	@Override
	public boolean updatePassword(String userID,String newPassword) {
		return userDaoInterface.updatePassword(userID, newPassword);
	}

	
	/**
	 * Method to verify User credentials
	 * @param userID
	 * @param password
	 * @return boolean indicating if user exists in the database
	 */

	@Override
	public boolean verifyCredentials(String userID, String password) throws UserNotFoundException {
		//DAO class
		try
		{
			return userDaoInterface.verifyCredentials(userID, password);		
		}
		finally
		{
			
		}
	}
	
	/**
	 * Method to get role of a specific User
	 * @param userId
	 * @return RoleConstant of the User
	 */
	@Override
	public String getRole(String userId) {
		return userDaoInterface.getRole(userId);
	}

	/**
	 * Method to get name of a specific User
	 * @param userId
	 * @return name of the User
	 */
	@Override
	public String getName(String userId) {
		return userDaoInterface.getName(userId);
	}


	

}