package com.flipkart.business;
import java.util.HashMap;
import java.util.Map;

import com.flipkart.dao.UserDaoImpl;
import com.flipkart.dao.UserDaoInterface;

public class UserTasks implements UserInterface {

    private static volatile UserTasks instance = null;
    
	UserDaoInterface userDaoInterface= UserDaoImpl.getInstance();

    @Override
    public boolean updatePassword(String userID, String newPassword) {
    	return userDaoInterface.updatePassword(userID, newPassword);
    }

    @Override
    public boolean verifyCredentials(String userID, String password) {
    	return userDaoInterface.verifyCredentials(userID, password);
    }

 
    public void addUserCredentials(String userID, String password) {
    	return; 
    }

    @Override
    public String getRole(String userId) {
        return userDaoInterface.getRole(userId);
    }

    @Override
    public String getName(String userId) {
        return null;
    }
}
