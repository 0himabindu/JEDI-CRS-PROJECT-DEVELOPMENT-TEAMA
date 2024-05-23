/**
 * 
 */
package com.flipkart.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentTasks;
import com.flipkart.business.UserInterface;
import com.flipkart.business.UserTasks;



public class CRSMainApplicationClient {


	static boolean loggedin = false;
	StudentInterface studentInterface= new StudentTasks();
	UserInterface userInterface = new UserTasks();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CRSMainApplicationClient crsApplication=new CRSMainApplicationClient();
		int userInput;	
		createMainMenu();
		userInput=sc.nextInt();
		while(userInput!=4)
		{
			switch(userInput)
			{	
				case 1:
					crsApplication.loginUser();
					break;
				case 2:
					crsApplication.registerStudent();
					break;	
				case 3:
					crsApplication.updatePassword();
					break;
				default:
					System.out.println("Invalid Input");
			}
			createMainMenu();
			userInput=sc.nextInt();
		}
	}
	
	/**
	 * Method to Create Main Menu
	 */
	public static void createMainMenu() {
		System.out.println("----------Welcome to Course Management System---------");
        System.out.println("1. Login");
        System.out.println("2. Student Registration");
        System.out.println("3. Update password");
        System.out.println("4. Exit");
        System.out.println("Enter user input");
	}

	
	/**
	 * Method for Login function2ality
	 */
	public void loginUser()
	{
		Scanner in = new Scanner(System.in);

		String userId,password;
		System.out.println("Login");
		System.out.println("User ID:");
		System.out.print(" ");
		userId = in.next();
		System.out.println("Password:");
		System.out.print(" ");
		password = in.next();

		loggedin = userInterface.verifyCredentials(userId, password);

			
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    LocalDateTime myDateObj = LocalDateTime.now();
	    String formattedDate = myDateObj.format(myFormatObj);
	    String role = userInterface.getRole(userId);
	    System.out.println(role);
	    if (loggedin) {
	    switch (role) {
	        case "Admin":
	            System.out.println("+----------------------------+");
	            System.out.println("|   " + formattedDate + "      |");
	            System.out.println("|   Admin Login Successful   |");
	            System.out.println("+----------------------------+");
	            System.out.println("       Welcome " + userInterface.getName(userId));
	            CRSAdminMenu adminMenu = new CRSAdminMenu(userId);
//	            adminMenu.createMenu();
	            break;
	        case "Professor":
	            System.out.println("+-----------------------------+");
	            System.out.println("|     " + formattedDate + "     |");
	            System.out.println("| Professor Login Successful  |");
	            System.out.println("+-----------------------------+");
	            System.out.println("        Welcome " + userInterface.getName(userId));

	           CRSProfessorMenu professorMenu = new CRSProfessorMenu();
//	            professorMenu.createMenu(userId);
	            break;
	        case "Student":
	            String studentId = userId;
	            boolean isApproved = studentInterface.isApproved(studentId);
	            if (isApproved) {
	                System.out.println("+-----------------------------+");
	                System.out.println("|     " + formattedDate + "     |");
	                System.out.println("|  Student Login Successful   |");
	                System.out.println("+----------------------------+");
	                System.out.println("     Welcome " + userInterface.getName(userId));

	                CRSStudentMenu studentMenu = new CRSStudentMenu();
//	                studentMenu.create_menu(studentId);
	            } else {
	                System.out.println("\u001B[31m+---------------------------------------------------------------------+");
	                System.out.println("|   \u001B[31mFailed to login, you have not been approved by the administration!   \u001B[31m|");
	                System.out.println("+---------------------------------------------------------------------+\u001B[0m");
	                loggedin = false;
	            }
	            break;
	    }
	} else {
		System.out.println("\u001B[31m+------------------------+");
		System.out.println("|   Invalid Credentials! |");
		System.out.println("+------------------------+\u001B[0m");

	}
	}
	/**
	 * Method to help Student register themselves, pending admin approval
	 */
	public void registerStudent()
	{
		Scanner sc=new Scanner(System.in);

		String userId,name,password,address,branchName;
		String gender;
		int genderV, batch;
		    // Input all the student details
			System.out.println("Student Registration");
			System.out.println("Name:");
			System.out.print(" ");
			name = sc.nextLine();
			System.out.println("User ID:");
			System.out.print(" ");
			userId = sc.next();
			System.out.println("Password:");
			System.out.print(" ");
			password = sc.next();

			System.out.println("GenderConstant:");
			System.out.println("1: Male");
			System.out.println("2. Female");
			System.out.println("3. Other");
			System.out.print(" ");
			genderV = sc.nextInt();
			sc.nextLine();
			switch (genderV) {
			    case 1:
			        gender = "MALE";
			        break;
			    case 2:
			        gender = "FEMALE";
			        break;
			    case 3:
			        gender = "OTHER";
			        break;
			    default:
			        gender = "OTHER";
			}
			System.out.println("Branch:");
			System.out.print(" ");
			branchName = sc.nextLine();
			System.out.println("Batch:");
			System.out.print(" ");
			batch = sc.nextInt();
			sc.nextLine();
			System.out.println("Address:");
			System.out.print(" ");
			address = sc.nextLine();

			String newStudentId = studentInterface.register(name, userId, password, gender, batch, branchName, address);
	}

	
	
	public void updatePassword() {
		Scanner in = new Scanner(System.in);
		String userId,newPassword;
		System.out.println("Update Password");

		try {
		    System.out.println("User ID:");
		    userId = in.next();
		    System.out.println("New Password:");
		    newPassword = in.next();

		    boolean isUpdated = userInterface.updatePassword(userId, newPassword);
		    if (isUpdated) {
		        System.out.println("Password updated successfully!");
		    } else {
		        System.out.println("Something went wrong, please try again!");
		    }
		} catch (Exception ex) {
		    System.out.println("Error Occurred: " + ex.getMessage());
		}

		
	}
		
}