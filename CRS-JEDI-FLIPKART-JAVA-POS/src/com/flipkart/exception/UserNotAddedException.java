package com.flipkart.exception;



public class UserNotAddedException extends Exception{
	private String userId;
	
	public UserNotAddedException(String id) {
		userId = id;
	}
	
	/**
	 * Getter function for UserId
	 * @return
	 */
	public String getUserId() {
		return userId;
	}
	

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "UserId: " + userId + " not added!";
	}
}