/**
 * 
 */
package com.flipkart.client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.exception.GradeNotAllotedException;
import com.flipkart.business.ProfessorTasks;
//import com.flipkart.validator.ProfessorValidator;


/**
 *
 */

public class CRSProfessorMenu {
	
	ProfessorInterface professorInterface = ProfessorTasks.getInstance();

	/**
	 * @param profID
	 */
	public void createMenu(String profID) {
		Scanner in = new Scanner(System.in);
		
		int input;
		while (CRSMainApplicationClient.loggedin) {
		    System.out.println("+---------------------------+");
		    System.out.println("|     Professor Menu        |");
		    System.out.println("+---------------------------+");
		    System.out.println("|                           |");
		    System.out.println("| 1. View Courses           |");
		    System.out.println("| 2. View Enrolled Students |");
		    System.out.println("| 3. Add Grades             |");
		    System.out.println("| 4. Logout                 |");
		    System.out.println("|                           |");
		    System.out.println("+---------------------------+");
		    System.out.print("Choose From Menu: ");

		    input = in.nextInt();
		    switch (input) {
		        case 1:
		            getCourses(profID);
		            break;
		        case 2:
		            viewEnrolledStudents(profID);
		            break;
		        case 3:
		            addGrade(profID);
		            break;
		        case 4:
		        	CRSMainApplicationClient.loggedin = false;
		            return;
		        default:
		            System.out.println("Please select an appropriate option...");
		    }
		}

		in.close();
	}
	
	public void viewEnrolledStudents(String profID) {
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|             View Enrolled Students - Professor             |");
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|    COURSE CODE    |    COURSE NAME    |      Student       |");
		try {
		    List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
		    enrolledStudents = professorInterface.viewEnrolledStudents(profID);
		    for (EnrolledStudent obj : enrolledStudents) {
		        System.out.println(String.format("| %17s | %17s | %17s |", obj.getCourseCode(), obj.getCourseName(), obj.getStudentId()));
		    }

		} catch (Exception ex) {
		    System.out.println("\u001B[31m" + ex.getMessage() + " Something went wrong, please try again later!\u001B[0m");
		}
		System.out.println("+------------------------------------------------------------+");

	}
	
	public void getCourses(String profId) {
		System.out.println("+-----------------------------------------------------------------------+");
		System.out.println("|                      View Enrolled Courses - Professor                 |");
		System.out.println("+-----------------------------------------------------------------------+");
		System.out.println("|     COURSE CODE     |     COURSE NAME     |     No. of Students Enrolled     |");
		try {
		    List<Course> coursesEnrolled = professorInterface.viewCourses(profId);
		    for (Course obj : coursesEnrolled) {
		        System.out.println(String.format("| %18s | %18s | %30s |", obj.getCourseCode(), obj.getCourseName(), 10 - obj.getSeats()));
		    }
		} catch (Exception ex) {
		    System.out.println("\u001B[31mSomething went wrong! " + ex.getMessage() + "\u001B[0m");
		}
		System.out.println("+-----------------------------------------------------------------------+");

	}
	
	public void addGrade(String profId) {
	    Scanner in = new Scanner(System.in);

	    String courseCode, grade, studentId;
	    try {
	        List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
	        enrolledStudents = professorInterface.viewEnrolledStudents(profId);
	        System.out.println("+-----------------------------------------------------------------------+");
	        System.out.println("|                        View Enrolled Students                          |");
	        System.out.println("+-----------------------------------------------------------------------+");
	        System.out.println("|     COURSE CODE     |     COURSE NAME     |        Student ID           |");
	        for (EnrolledStudent obj : enrolledStudents) {
	            System.out.println(String.format("| %18s | %18s | %25s |", obj.getCourseCode(), obj.getCourseName(), obj.getStudentId()));
	        }
	        System.out.println("+-----------------------------------------------------------------------+");

	        List<Course> coursesEnrolled = new ArrayList<Course>();
	        coursesEnrolled = professorInterface.viewCourses(profId);

	        System.out.println("---- Add Grade ----");
	        System.out.print("Enter student id: ");
	        studentId = in.nextLine();
	        System.out.print("Enter course code: ");
	        courseCode = in.nextLine();
	        System.out.print("Enter grade: ");
	        grade = in.nextLine();

	        
	        professorInterface.addGrade(studentId, courseCode, grade);
	        System.out.println("Grade added successfully for " + studentId);
	        
	    } catch (GradeNotAllotedException ex) {
	        System.out.println("\u001B[31mGrade cannot be added for " + ex.getStudentId() + "\u001B[0m");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}