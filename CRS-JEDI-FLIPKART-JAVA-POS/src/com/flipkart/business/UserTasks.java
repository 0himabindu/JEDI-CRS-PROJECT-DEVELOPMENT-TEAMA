package com.flipkart.business;
import java.util.HashMap;
import java.util.Map;

public class UserTasks implements UserInterface {

    private static volatile UserTasks instance = null;
    private final Map<String, String> credentialsMap = new HashMap<>();

    public static UserTasks getInstance() {
        if (instance == null) {
            synchronized (UserTasks.class) {
                instance = new UserTasks();
            }
        }
        return instance;
    }

    @Override
    public boolean updatePassword(String userID, String newPassword) {
        if (credentialsMap.containsKey(userID)) {
            credentialsMap.put(userID, newPassword);
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyCredentials(String userID, String password) {
        String storedPassword = credentialsMap.get(userID);
        return storedPassword != null && storedPassword.equals(password);
    }

 
    public void addUserCredentials(String userID, String password) {
        credentialsMap.put(userID, password);
    }

    @Override
    public String getRole(String userId) {
        return null;
    }

    @Override
    public String getName(String userId) {
        return null;
    }
}
