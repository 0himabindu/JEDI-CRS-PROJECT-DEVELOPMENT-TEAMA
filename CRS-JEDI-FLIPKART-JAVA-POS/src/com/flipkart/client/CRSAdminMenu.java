/**
 * 
 */
package com.flipkart.client;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.*;
import com.flipkart.business.*;


public class CRSAdminMenu {

	AdminInterface adminOperation = AdminOperation.getInstance();
	Scanner in = new Scanner(System.in);
	PaymentNotificationTasks notificationInterface = PaymentNotificationTasks.getInstance();
//	RegistrationInterface registrationInterface = RegistrationOperation.getInstance();


	public void createMenu() {
	    while (CRSMainApplicationClient.loggedin) {
	        System.out.println( "Admin Menu");
	        System.out.println("1. View course in catalog");
	        System.out.println("2. Add Course to catalog");
	        System.out.println("3. Delete Course from catalog");
	        System.out.println("4. Approve Students");
	        System.out.println("5. View Pending Approvals");
	        System.out.println("6. Add Professor");
	        System.out.println("7. Assign Professor To Courses");
	        System.out.println("8. Generate Report Card");
	        System.out.println("9. Logout");



	        int choice = in.nextInt();

	        switch (choice) {
	            case 1:
	                viewCoursesInCatalogue();
	                break;

	            case 2:
	                addCourseToCatalogue();
	                break;

	            case 3:
	                removeCourse();
	                break;

	            case 4:
	                approveStudent();
	                break;

	            case 5:
	                viewPendingAdmissions();
	                break;

	            case 6:
	                addProfessor();
	                break;

	            case 7:
	                assignCourseToProfessor();
	                break;

	            case 8:
	                generateReportCard();
	                break;

	            case 9:
	                CRSMainApplicationClient.loggedin = false;
	                return;

	            default:
	                System.out.println("Wrong Choice!");
	        }
	    }
	}


	private void generateReportCard() {
//	    List<Grade> grade_card = null;
//	    boolean isReportGenerated = true;
//
//	    Scanner in = new Scanner(System.in);
//
//	    System.out.println("Generate Report Card");
//	    System.out.println("\n");
//	    System.out.println("Enter the StudentId for report card generation: ");
//	    String studentId = in.next();
//
//	    try {
//	        adminOperation.setGeneratedReportCardTrue(studentId);
//	        if (isReportGenerated) {
//	            grade_card = registrationInterface.viewGradeCard(studentId);
//	            System.out.println(String.format("%-20s %-20s %-20s", "COURSE CODE", "COURSE NAME", "GRADE"));
//
//	            if (grade_card.isEmpty()) {
//	                System.out.println("You haven't registered for any course");
//	                return;
//	            }
//
//	            for (Grade obj : grade_card) {
//	                System.out.println(
//	                        String.format("%-20s %-20s %-20s", obj.getCrsCode(), obj.getCrsName(), obj.getGrade()));
//	            }
//	        } else {
//	            System.out.println("Report card not yet generated");
//	        }
//	    } catch (SQLException e) {
//	        System.out.println(e.getMessage());
//	    }
	}



	private void assignCourseToProfessor() {
		
	    List<Professor> professorList = adminOperation.viewProfessors();

	    System.out.println("Assign Course to Professor");

	    System.out.println("\n");

	    System.out.println("Professor");
	    
	    for (Professor professor : professorList) {
	        System.out.println(String.format("%20s | %20s | %20s ", professor.getUserId(), professor.getName(), professor.getDesignation()));
	    }
	    System.out.println("\n");

	    List<Course> courseList = adminOperation.viewCourses();
	    System.out.println("Course");
	    for (Course course : courseList) {
	        System.out.println(String.format("%20s | %20s | %20s", course.getCourseCode(), course.getCourseName(),
	                course.getInstructorId()));
	    }
	    System.out.println("\n");

	    Scanner in = new Scanner(System.in);
	    System.out.println("Enter Course Code:");
	    String courseCode = in.next();

	    System.out.println("Enter Professor's User Id:");
	    String userId = in.next();

	        adminOperation.assignCourse(courseCode, userId);
	}



	/**
	 * Method to add Professor to DB
	 */
	private void addProfessor() {
	    System.out.println("Add Professor");

	    Scanner in = new Scanner(System.in);

	    
	    Professor professor = new Professor();
	    System.out.print("| Enter User Id (integer): ");
	    String userId = in.next();
	    professor.setUserId(userId);

	    System.out.print("| Enter Professor Name: ");
	    String professorName = in.next();
	    professor.setName(professorName);

	    System.out.print("| Enter Department: ");
	    String department = in.next();
	    professor.setDepartment(department);

	    System.out.print("| Enter Designation: ");
	    String designation = in.next();
	    professor.setDesignation(designation);

	    System.out.print("| Enter Password: ");
	    String password = in.next();
	    professor.setPassword(password);

	    System.out.println("| Enter GenderConstant: 1: Male   2: Female   3: Other ");
	    System.out.print("| Select Gender: ");
	    String gender = in.next();
        professor.setGender(gender);
	   

	    System.out.print("| Enter Address: ");
	    String address = in.next();
	    professor.setAddress(address);

	    professor.setRole("PROFESSOR");

        adminOperation.addProfessor(professor);
	}



	private List<Student> viewPendingAdmissions() {
	    List<Student> pendingStudentsList = adminOperation.viewPendingAdmissions();

	    System.out.println("View Pending Admissions");

	    if (pendingStudentsList.size() == 0) {
	        System.out.println("No students pending approvals");
	        return pendingStudentsList;
	    }

	    for (Student student : pendingStudentsList) {
	        System.out.println(String.format("| %16s | %16s | %16s |", student.getStudentId(), student.getName(),
	                student.getGender().toString()));
	    }
	    return pendingStudentsList;
	}



	private void approveStudent() {
	    List<Student> studentList = viewPendingAdmissions();

	    System.out.println("Approve Students");

	    if (studentList.size() == 0) {
	        System.out.println("No Pending Approval!");
	        return;
	    }

	    int choice = 0;

	    System.out.println("Select Type of approval:");
	    System.out.println("1. Approve all students");
	    System.out.println("2. Approve by ID");
	    choice = in.nextInt();

	    String studentUserIdApproval;

	    if (choice == 1) {
	        studentList.forEach(it -> {
	           
	                adminOperation.approveStudent(it.getUserId(), studentList);
	                System.out.println("\nStudent Id: " + it.getUserId() + " has been approved\n");
	                notificationInterface.sendNotification("REGISTRATION", it.getUserId(), null, 0);	            
	        });
	    } else if (choice == 2) {
	        System.out.println("Enter Student's ID:");
	        studentUserIdApproval = in.next();


            adminOperation.approveStudent(studentUserIdApproval, studentList);
            System.out.println("\nStudent Id: " + studentUserIdApproval + " has been approved\n");
            // send notification from system
            notificationInterface.sendNotification("REGISTRATION", studentUserIdApproval, null, 0);
            
	    } else {
	        System.out.println("\t Incorrect choice!");
	    }

	}



	private void removeCourse() {
	    List<Course> courseList = viewCoursesInCatalogue();

	    System.out.println("Remove Course");

	    System.out.println("Enter Course Code:");
	    String courseCode = in.next();

	    adminOperation.removeCourse(courseCode);
	    System.out.println("\nCourse Deleted: " + courseCode + "\n");

	}


	private void addCourseToCatalogue() {
	    List<Course> courseList = viewCoursesInCatalogue();

	    in.nextLine();

	    System.out.println("Add Course to Catalogue");

	    System.out.println("Enter Course Code:");
	    String courseCode = in.nextLine();

	    System.out.println("Enter Course Name:");
	    String courseName = in.next();

	    System.out.println("Enter Course Fee:");
	    int courseFee = in.nextInt();

	    System.out.println("Enter Number of Seats:");
	    int seats = in.nextInt();

	    Course course = new Course();
	    course.setCourseCode(courseCode);
	    course.setCourseName(courseName);
	    course.setSeats(seats);
	    course.setCourseFee(courseFee);


        adminOperation.addCourse(course, courseList);
        System.out.println("\nCourse Added to Catalogue: " + courseCode + "\n");
	}

	private List<Course> viewCoursesInCatalogue() {
	    List<Course> courseList = adminOperation.viewCourses();
	    if (courseList.size() == 0) {
	        System.out.println("Courses in Catalogue");
	        System.out.println("Nothing present in the catalogue!");
	        return courseList;
	    }

	    System.out.println("Courses in Catalogue");
	    for (Course course : courseList) {
	        System.out.println(String.format("| %16s | %16s | %16s |", course.getCourseCode(), course.getCourseName(), course.getInstructorId()));
	    }

	    return courseList;
	}

}