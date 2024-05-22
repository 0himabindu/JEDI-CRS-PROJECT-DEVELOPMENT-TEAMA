
package com.flipkart.bean;


public abstract class User {
	private String userId;
	private String name;
	private String password;
	private String address;
	public String role;
	
	public User(String userId, String name, String password, String address, String role) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.address = address;
		this.role = role;
	}
	
	
	public String getAddress() {
		return address;
	}
	
	
	public void setAddress(String address) {
		this.address = address;
	}
	

	public User(){
		
	}
	

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getPassword() {
		return password;
	}
	

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	

	public void setRole(String role) {
		this.role = role;
	}
	
}