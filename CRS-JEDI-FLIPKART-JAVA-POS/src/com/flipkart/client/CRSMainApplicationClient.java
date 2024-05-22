/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;


import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentTasks;
import com.flipkart.business.UserInterface;
import com.flipkart.business.UserTasks;



public class CRSMainApplicationClient {


	static boolean loggedin = false;
	StudentInterface studentInterface=StudentTasks.getInstance();
	UserInterface userInterface =UserTasks.getInstance();
	
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
	    System.out.println("------------------------------------------------------");
	    System.out.println("|        Welcome to Course Registration System        |");
	    System.out.println("|                                                     |");
	    System.out.println("------------------------------------------------------");
	    System.out.println("|   1. Login                                          |");
	    System.out.println("|   2. Student Registration                           |");
	    System.out.println("|   3. Update Password                                |");
	    System.out.println("|   4. Exit                                           |");
	    System.out.println("------------------------------------------------------");
	    System.out.println("Enter your choice: ");
	}

	
	/**
	 * Method for Login function2ality
	 */
	public void loginUser()
	{
		Scanner in = new Scanner(System.in);

		String userId,password;
		System.out.println("+-----------------+");
		System.out.println("|      Login      |");
		System.out.println("+-----------------+");
		System.out.println("| User ID:        |");
		System.out.print("| ");
		userId = in.next();
		System.out.println("+-----------------+");
		System.out.println("| Password:       |");
		System.out.print("| ");
		password = in.next();
		System.out.println("+-----------------+");

		loggedin = userInterface.verifyCredentials(userId, password);

			
		if (loggedin) {
			System.out.println("+--------Login Successful---------+");


		} 
		else {
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
			System.out.println("+---------------------------+");
			System.out.println("|  Student Registration     |");
			System.out.println("+---------------------------+");
			System.out.println("| Name:                     |");
			System.out.println("+---------------------------+");
			System.out.print("| ");
			name = sc.nextLine();
			System.out.println("+---------------------------+");
			System.out.println("| User ID:                    |");
			System.out.println("+---------------------------+");
			System.out.print("| ");
			userId = sc.next();
			System.out.println("+---------------------------+");
			System.out.println("| Password:                 |");
			System.out.println("+---------------------------+");
			System.out.print("| ");
			password = sc.next();

			System.out.println("+---------------------------+");
			System.out.println("| GenderConstant:           |");
			System.out.println("+---------------------------+");
			System.out.println("| 1: Male                   |");
			System.out.println("| 2. Female                 |");
			System.out.println("| 3. Other                  |");
			System.out.println("+---------------------------+");
			System.out.print("| ");
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
			System.out.println("+---------------------------+");
			System.out.println("| Branch:                   |");
			System.out.println("+---------------------------+");
			System.out.print("| ");
			branchName = sc.nextLine();
			System.out.println("+---------------------------+");
			System.out.println("| Batch:                    |");
			System.out.println("+---------------------------+");
			System.out.print("| ");
			batch = sc.nextInt();
			sc.nextLine();
			System.out.println("+---------------------------+");
			System.out.println("| Address:                  |");
			System.out.println("+---------------------------+");
			System.out.print("| ");
			address = sc.nextLine();
			System.out.println("+---------------------------+");

			String newStudentId = studentInterface.register(name, userId, password, gender, batch, branchName, address);
	}

	
	
	public void updatePassword() {
		Scanner in = new Scanner(System.in);
		String userId,newPassword;
		System.out.println("+--------------------------------+");
		System.out.println("|     Update Password            |");
		System.out.println("+--------------------------------+");

		try {
		    System.out.println("| User ID:                         |");
		    userId = in.next();
		    System.out.println("+--------------------------------+");
		    System.out.println("| New Password:                  |");
		    newPassword = in.next();
		    System.out.println("+--------------------------------+");

		    boolean isUpdated = userInterface.updatePassword(userId, newPassword);
		    if (isUpdated) {
		        System.out.println("| \u001B[32mPassword updated successfully!\u001B[0m |");
		    } else {
		        System.out.println("| \u001B[31mSomething went wrong, please try again!\u001B[0m |");
		    }
		} catch (Exception ex) {
		    System.out.println("| \u001B[31mError Occurred: " + ex.getMessage() + "\u001B[0m |");
		}

		System.out.println("+--------------------------------+");

		
	}
		
}