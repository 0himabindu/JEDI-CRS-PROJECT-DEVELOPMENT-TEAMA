/**
 * 
 */
package com.flipkart.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentOperation;
import com.flipkart.business.UserInterface;
import com.flipkart.business.UserOperation;
import com.flipkart.constant.GenderConstant;
import com.flipkart.constant.NotificationTypeConstant;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.exception.UserNotFoundException;



public class CRSMainApplicationClient {


	static boolean loggedin = false;
	StudentInterface studentInterface=StudentOperation.getInstance();
	UserInterface userInterface =UserOperation.getInstance();
	NotificationInterface notificationInterface=NotificationOperation.getInstance();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CRSMainApplicationClient crsApplication=new CRSMainApplicationClient();
		int userInput;	
		//create the main menu
		createMainMenu();
		userInput=sc.nextInt();
		try
		{
			
		//until user do not exit the application
		while(userInput!=4)
		{
			switch(userInput)
			{	
				case 1:
					//login
					crsApplication.loginUser();
					break;
				case 2:
					//student registration
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
		catch(Exception ex)
		{
			System.out.println("Error occured "+ex);
		}
		finally
		{
			sc.close();
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
		//multiple exceptions are possible
		//invalid credential exception
		//user not found exception
		//user not approved exception
		Scanner in = new Scanner(System.in);

		String userId,password;
		try
		{System.out.println("+-----------------+");
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


		//2 cases1
			
			
		if (loggedin) {
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		    LocalDateTime myDateObj = LocalDateTime.now();
		    String formattedDate = myDateObj.format(myFormatObj);
		    String role = userInterface.getRole(userId);

		    switch (role) {
		        case "ADMIN":
		            System.out.println("+----------------------------+");
		            System.out.println("|   " + formattedDate + "      |");
		            System.out.println("|   Admin Login Successful   |");
		            System.out.println("+----------------------------+");
		            System.out.println("       Welcome " + userInterface.getName(userId));
		            CRSAdminMenu adminMenu = new CRSAdminMenu();
		            adminMenu.createMenu();
		            break;
		        case "PROFESSOR":
		            System.out.println("+-----------------------------+");
		            System.out.println("|     " + formattedDate + "     |");
		            System.out.println("| Professor Login Successful  |");
		            System.out.println("+-----------------------------+");
		            System.out.println("        Welcome " + userInterface.getName(userId));

		            CRSProfessorMenu professorMenu = new CRSProfessorMenu();
		            professorMenu.createMenu(userId);
		            break;
		        case "STUDENT":
		            String studentId = userId;
		            boolean isApproved = studentInterface.isApproved(studentId);
		            if (isApproved) {
		                System.out.println("+-----------------------------+");
		                System.out.println("|     " + formattedDate + "     |");
		                System.out.println("|  Student Login Successful   |");
		                System.out.println("+----------------------------+");
		                System.out.println("     Welcome " + userInterface.getName(userId));

		                CRSStudentMenu studentMenu = new CRSStudentMenu();
		                studentMenu.create_menu(studentId);
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
		catch (UserNotFoundException ex) {
		    System.out.println("\u001B[31m" + "+------------------------------------+" + "\u001B[0m");
		    System.out.println("\u001B[31m" + "|                                    |" + "\u001B[0m");
		    System.out.println("\u001B[31m" + "|     " + ex.getMessage() + "" + "\u001B[0m");
		    System.out.println("\u001B[31m" + "|                                    |" + "\u001B[0m");
		    System.out.println("\u001B[31m" + "+------------------------------------+" + "\u001B[0m");
		}

		
	}
	
	/**
	 * Method to help Student register themselves, pending admin approval
	 */
	public void registerStudent()
	{
		Scanner sc=new Scanner(System.in);

		String userId,name,password,address,branchName;
		GenderConstant gender;
		int genderV, batch;
		try {
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
			        gender = GenderConstant.MALE;
			        break;
			    case 2:
			        gender = GenderConstant.FEMALE;
			        break;
			    case 3:
			        gender = GenderConstant.OTHER;
			        break;
			    default:
			        gender = GenderConstant.OTHER;
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

		    // notificationInterface.sendNotification(NotificationTypeConstant.REGISTRATION, newStudentId, null,0);

		} catch (StudentNotRegisteredException ex) {
		    System.out.println("\u001B[31m+---------------------------------------------------------------------------------------------------+\u001B[0m");
		    System.out.println("\u001B[31m|  Something went wrong! " + ex.getStudentName() + " not registered. Please try again  |\u001B[0m");
		    System.out.println("\u001B[31m+--------------------------------------------------+------------------------------------------------+\u001B[0m");
		}

		//sc.close();
	}
	
	/**
	 * Method to update password of User
	 */
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
