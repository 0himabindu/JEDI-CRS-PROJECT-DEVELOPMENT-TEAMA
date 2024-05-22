
package com.flipkart.bean;

import java.util.Date;


public class Admin extends User{
	
	private String adminID;
	
	public Admin(String userID, String name, String gender, String role, String password, String address) 
	{
		super(userID, name, role, password, gender, address);
	}
}