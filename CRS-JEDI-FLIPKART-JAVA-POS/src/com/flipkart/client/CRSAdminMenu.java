/**
 * 
 */
package com.flipkart.client;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.business.RegistrationInterface;
import com.flipkart.business.RegistrationOperation;
import com.flipkart.constant.GenderConstant;
import com.flipkart.constant.NotificationTypeConstant;
import com.flipkart.constant.RoleConstant;
import com.flipkart.exception.CourseExistsAlreadyException;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;


public class CRSAdminMenu {

	AdminInterface adminOperation = AdminOperation.getInstance();
	Scanner in = new Scanner(System.in);
	NotificationInterface notificationInterface = NotificationOperation.getInstance();
	RegistrationInterface registrationInterface = RegistrationOperation.getInstance();

	/**
	 * Method to Create Admin Menu
	 */
	public void createMenu() {
	    while (CRSMainApplicationClient.loggedin) {
	        System.out.println("+-----------------+");
	        System.out.println("|   Admin Menu    |");
	        System.out.println("+-----------------+");
	        System.out.println("╔═══════════════════════════════╗");
	        System.out.println("║                               ║");
	        System.out.println("║ 1. View course in catalog     ║");
	        System.out.println("║ 2. Add Course to catalog      ║");
	        System.out.println("║ 3. Delete Course from catalog ║");
	        System.out.println("║ 4. Approve Students           ║");
	        System.out.println("║ 5. View Pending Approvals     ║");
	        System.out.println("║ 6. Add Professor              ║");
	        System.out.println("║ 7. Assign Professor To Courses║");
	        System.out.println("║ 8. Generate Report Card       ║");
	        System.out.println("║ 9. Logout                     ║");
	        System.out.println("║                               ║");
	        System.out.println("╚═══════════════════════════════╝");


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
	                System.out.println("+------------------+");
	                System.out.println("|  Wrong Choice!   |");
	                System.out.println("+------------------+");
	        }
	    }
	}


	private void generateReportCard() {
	    List<Grade> grade_card = null;
	    boolean isReportGenerated = true;

	    Scanner in = new Scanner(System.in);

	    System.out.println("+------------------------------------+");
	    System.out.println("|    Generate Report Card             |");
	    System.out.println("+------------------------------------+");
	    System.out.println("\n");
	    System.out.println("Enter the StudentId for report card generation: ");
	    String studentId = in.next();

	    try {
	        adminOperation.setGeneratedReportCardTrue(studentId);
	        if (isReportGenerated) {
	            grade_card = registrationInterface.viewGradeCard(studentId);
	            System.out.println(String.format("%-20s %-20s %-20s", "COURSE CODE", "COURSE NAME", "GRADE"));

	            if (grade_card.isEmpty()) {
	                System.out.println("You haven't registered for any course");
	                return;
	            }

	            for (Grade obj : grade_card) {
	                System.out.println(
	                        String.format("%-20s %-20s %-20s", obj.getCrsCode(), obj.getCrsName(), obj.getGrade()));
	            }
	        } else {
	            System.out.println("Report card not yet generated");
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}



	/**
	 * Method to assign Course to a Professor
	 */
	private void assignCourseToProfessor() {
	    List<Professor> professorList = adminOperation.viewProfessors();
	    System.out.println("+" + "-".repeat(67) + "+");
	    System.out.println("|" + " ".repeat(67) + "|");
	    System.out.println("|    Assign Course to Professor       ");
	    System.out.println("|" + " ".repeat(67) + "|");
	    System.out.println("+" + "-".repeat(67) + "+");
	    System.out.println("\n");

	    System.out.println("+" + "-".repeat(67) + "+");
	    System.out.println("|           ---- Professor ----       |");
	    System.out.println("|" + String.format("%20s | %20s | %20s ", "ProfessorId", "Name", "Designation") + "|");
	    for (Professor professor : professorList) {
	        System.out.println("|" + String.format("%20s | %20s | %20s ", professor.getUserId(), professor.getName(),
	                professor.getDesignation()) + "|");
	    }
	    System.out.println("+" + "-".repeat(67) + "+");
	    System.out.println("\n");

	    List<Course> courseList = adminOperation.viewCourses();
	    System.out.println("+" + "-".repeat(67) + "+");
	    System.out.println("|             ---- Course ----        |");
	    System.out.println("|" + String.format("%20s | %20s | %20s", "CourseCode", "CourseName", "ProfessorId") + "|");
	    for (Course course : courseList) {
	        System.out.println("|" + String.format("%20s | %20s | %20s", course.getCourseCode(), course.getCourseName(),
	                course.getInstructorId()) + "|");
	    }
	    System.out.println("+" + "-".repeat(67) + "+");
	    System.out.println("\n");

	    Scanner in = new Scanner(System.in);
	    System.out.println("Enter Course Code:");
	    String courseCode = in.next();

	    System.out.println("Enter Professor's User Id:");
	    String userId = in.next();

	    try {
	        adminOperation.assignCourse(courseCode, userId);
	    } catch (CourseNotFoundException | UserNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
	}



	/**
	 * Method to add Professor to DB
	 */
	private void addProfessor() {
	    System.out.println("+------------------------------------+");
	    System.out.println("|          Add Professor              |");
	    System.out.println("+------------------------------------+\n");

	    Scanner in = new Scanner(System.in);

	    System.out.print("| Enter User Id (integer): ");
	    String userId = in.next();
	    Professor professor = new Professor(userId);

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
	    int gender = in.nextInt();

	    if (gender == 1)
	        professor.setGender(GenderConstant.MALE);
	    else if (gender == 2)
	        professor.setGender(GenderConstant.FEMALE);
	    else if (gender == 3)
	        professor.setGender(GenderConstant.OTHER);

	    System.out.print("| Enter Address: ");
	    String address = in.next();
	    professor.setAddress(address);

	    professor.setRole(RoleConstant.PROFESSOR);

	    try {
	        adminOperation.addProfessor(professor);
	        System.out.println("+------------------------------------+");
	    } catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
	        System.out.println("| Error: " + e.getMessage());
	        System.out.println("+------------------------------------+");
	    }
	}


	/**
	 * Method to view Students who are yet to be approved
	 * 
	 * @return List of Students whose admissions are pending
	 */
	private List<Student> viewPendingAdmissions() {
	    List<Student> pendingStudentsList = adminOperation.viewPendingAdmissions();

	    System.out.println("+-------------------------------------------------------+");
	    System.out.println("|              View Pending Admissions                   |");
	    System.out.println("+-------------------------------------------------------+\n");

	    if (pendingStudentsList.size() == 0) {
	        System.out.println("No students pending approvals");
	        System.out.println("+-------------------------------------------------------+");
	        return pendingStudentsList;
	    }

	    System.out.println(String.format("| %16s | %16s | %16s |", "StudentId", "Name", "Gender"));
	    for (Student student : pendingStudentsList) {
	        System.out.println(String.format("| %16s | %16s | %16s |", student.getStudentId(), student.getName(),
	                student.getGender().toString()));
	    }

	    System.out.println("+-------------------------------------------------------+");
	    return pendingStudentsList;
	}


	/**
	 * Method to approve a Student using Student's ID
	 */
	private void approveStudent() {
	    List<Student> studentList = viewPendingAdmissions();

	    System.out.println("+-------------------------------------------------------+");
	    System.out.println("|                  Approve Students                      |");
	    System.out.println("+-------------------------------------------------------+\n");

	    if (studentList.size() == 0) {
	        System.out.println("No Pending Approval!");
	        System.out.println("+-------------------------------------------------------+");
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
	            try {
	                adminOperation.approveStudent(it.getUserId(), studentList);
	                System.out.println("\nStudent Id: " + it.getUserId() + " has been approved\n");
	                // send notification from system
	                notificationInterface.sendNotification(NotificationTypeConstant.REGISTRATION, it.getUserId(), null, 0);
	            } catch (StudentNotFoundForApprovalException e) {
	                System.out.println(e.getMessage());
	            }
	        });
	    } else if (choice == 2) {
	        System.out.println("Enter Student's ID:");
	        studentUserIdApproval = in.next();

	        try {
	            adminOperation.approveStudent(studentUserIdApproval, studentList);
	            System.out.println("\nStudent Id: " + studentUserIdApproval + " has been approved\n");
	            // send notification from system
	            notificationInterface.sendNotification(NotificationTypeConstant.REGISTRATION, studentUserIdApproval, null, 0);
	        } catch (StudentNotFoundForApprovalException e) {
	            System.out.println(e.getMessage());
	        }
	    } else {
	        System.out.println("\t Incorrect choice!");
	    }

	    System.out.println("+-------------------------------------------------------+");
	}


	/**
	 * Method to delete Course from catalogue
	 * 
	 * @throws CourseNotFoundException
	 */
	private void removeCourse() {
	    List<Course> courseList = viewCoursesInCatalogue();

	    System.out.println("+-------------------------------------------------------+");
	    System.out.println("|                  Remove Course                         |");
	    System.out.println("+-------------------------------------------------------+\n");

	    System.out.println("Enter Course Code:");
	    String courseCode = in.next();

	    try {
	        adminOperation.removeCourse(courseCode, courseList);
	        System.out.println("\nCourse Deleted: " + courseCode + "\n");
	    } catch (CourseNotFoundException e) {
	        System.out.println(e.getMessage());
	    } catch (CourseNotDeletedException e) {
	        System.out.println(e.getMessage());
	    }

	    System.out.println("+-------------------------------------------------------+");
	}


	/**
	 * Method to add Course to catalogue
	 * 
	 * @throws CourseExistsAlreadyException
	 */
	private void addCourseToCatalogue() {
	    List<Course> courseList = viewCoursesInCatalogue();

	    in.nextLine();

	    System.out.println("+-------------------------------------------------------+");
	    System.out.println("|               Add Course to Catalogue                  |");
	    System.out.println("+-------------------------------------------------------+\n");

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

	    try {
	        adminOperation.addCourse(course, courseList);
	        System.out.println("\nCourse Added to Catalogue: " + courseCode + "\n");
	    } catch (CourseExistsAlreadyException e) {
	        System.out.println("Course already exists: " + e.getMessage());
	    }

	    System.out.println("+-------------------------------------------------------+");
	}

	

	/**
	 * Method to display courses in catalogue
	 * 
	 * @return List of courses in catalogue
	 */
	private List<Course> viewCoursesInCatalogue() {
	    List<Course> courseList = adminOperation.viewCourses();
	    if (courseList.size() == 0) {
	        System.out.println("+-------------------------------------------------------+");
	        System.out.println("|               Courses in Catalogue                     |");
	        System.out.println("+-------------------------------------------------------+");
	        System.out.println("|        Nothing present in the catalogue!              |");
	        System.out.println("+-------------------------------------------------------+");
	        return courseList;
	    }

	    System.out.println("+-------------------------------------------------------+");
	    System.out.println("|               Courses in Catalogue                     |");
	    System.out.println("+-------------------------------------------------------+");
	    System.out.println(String.format("| %16s | %16s | %16s |", "COURSE CODE", "COURSE NAME", "INSTRUCTOR"));
	    for (Course course : courseList) {
	        System.out.println(String.format("| %16s | %16s | %16s |", course.getCourseCode(), course.getCourseName(), course.getInstructorId()));
	    }
	    System.out.println("+-------------------------------------------------------+");

	    return courseList;
	}

}
