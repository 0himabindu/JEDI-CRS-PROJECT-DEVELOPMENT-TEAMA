/**
 * 
 */
package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.business.*;
import com.flipkart.constant.NotificationTypeConstant;
import com.flipkart.constant.PaymentModeConstant;
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;



public class CRSStudentMenu {

	Scanner sc = new Scanner(System.in);
	RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
	AdminInterface adminInterface = AdminOperation.getInstance();
	ProfessorInterface professorInterface = ProfessorOperation.getInstance();
	NotificationInterface notificationInterface = NotificationOperation.getInstance();
	private boolean is_registered;

	public void create_menu(String studentId) {

		is_registered = getRegistrationStatus(studentId);

		while (CRSMainApplicationClient.loggedin) {

			System.out.println("+------------------------+");
			System.out.println("|     Student Menu       |");
			System.out.println("+------------------------+");

			System.out.println("╔═══════════════════════════════╗");
	        System.out.println("║                               ║");
	        System.out.println("║ 1. Semester Registration      ║");
	        System.out.println("║ 2. Add Course                 ║");
	        System.out.println("║ 3. Drop Course                ║");
	        System.out.println("║ 4. View Course                ║");
	        System.out.println("║ 5. View Registered Courses    ║");
	        System.out.println("║ 6. View Grade Card            ║");
	        System.out.println("║ 7. Make Payment               ║");
	        System.out.println("║ 8. Logout                     ║");
	        System.out.println("║                               ║");
	        System.out.println("╚═══════════════════════════════╝");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				registerCourses(studentId);
				break;

			case 2:
				addCourse(studentId);
				break;

			case 3:
				dropCourse(studentId);
				break;

			case 4:
				viewCourse(studentId);
				break;

			case 5:
				viewRegisteredCourse(studentId);
				break;

			case 6:
				viewGradeCard(studentId);
				break;

			case 7:
				make_payment(studentId);
				break;

			case 8:
				CRSMainApplicationClient.loggedin = false;
				break;

			default:
				System.out.println("\u001B[31mIncorrect Choice!\u001B[0m");
			}

		}

	}

	private void registerCourses(String studentId) {
	    if (is_registered) {
	        System.out.println("+----------------------------------------+");
	        System.out.println("|     \u001B[31mRegistration is already completed\u001B[0m     |");
	        System.out.println("+----------------------------------------+");
	        return;
	    }

	    int count = 0;
	    while (count < 6) {
	        try {
	            List<Course> courseList = viewCourse(studentId);

	            if (courseList == null)
	                return;

	            System.out.print("Enter Course Code: " + (count + 1) + " ");
	            String courseCode = sc.next();

	            if (registrationInterface.addCourse(courseCode, studentId, courseList)) {
	                System.out.println("+----------------------------------------+");
	                System.out.println("|   Course " + courseCode + " registered successfully.  |");
	                System.out.println("+----------------------------------------+");
	                count++;
	            } else {
	                System.out.println("+----------------------------------------+");
	                System.out.println("|  You have already registered for Course: " + courseCode + "  |");
	                System.out.println("+----------------------------------------+");
	            }
	        } catch (CourseNotFoundException | CourseLimitExceededException | SQLException e) {
	            System.out.println("+----------------------------------------+");
	            System.out.println("|       \u001B[31m" + e.getMessage() + "\u001B[0m       |");
	            System.out.println("+----------------------------------------+");
	        } catch (SeatNotAvailableException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	    }

	    System.out.println();
	    System.out.println("-------------------------");
	    System.out.println("|  Registration Successful  |");
	    System.out.println("-------------------------|");

	    try {
	        registrationInterface.setRegistrationStatus(studentId);
	    } catch (SQLException e) {
	        System.out.println("+----------------------------------------+");
	        System.out.println("|        \u001B[31m" + e.getMessage() + "\u001B[0m       |");
	        System.out.println("+----------------------------------------+");
	    }
	    is_registered = true;
	}


	private void addCourse(String studentId) {
	    if (is_registered) {
	        List<Course> availableCourseList = viewCourse(studentId);

	        if (availableCourseList == null)
	            return;

	        try {
	            System.out.println("+-------------------------------+");
	            System.out.print("|  Enter Course Code: ");
	            String courseCode = sc.next();
	            if (registrationInterface.addCourse(courseCode, studentId, availableCourseList)) {
	                System.out.println("|  You have successfully registered for Course: " + courseCode + "  |");
	            } else {
	                System.out.println("|  You have already registered for Course: " + courseCode + "  |");
	            }
	        } catch (SQLException e) {
	            System.out.println("+-------------------------------+");
	            System.out.println("|       \u001B[31m" + e.getMessage() + "\u001B[0m       |");
	        } catch (CourseNotFoundException e) {
	            System.out.println("+-------------------------------+");
	            System.out.println("|       \u001B[31m" + e.getMessage() + "\u001B[0m       |");

	        } catch (CourseLimitExceededException e) {
	            System.out.println("+-------------------------------+");
	            System.out.println("|       \u001B[31m" + e.getMessage() + "\u001B[0m       |");

	        } catch (SeatNotAvailableException e) {
	            System.out.println("+-------------------------------+");
	            System.out.println("|       \u001B[31m" + e.getMessage() + "\u001B[0m       |");

	        }
	    } else {
	        System.out.println("+-------------------------------+");
	        System.out.println("|  \u001B[31mPlease complete registration\u001B[0m  |");
	    }
	    System.out.println("+-------------------------------+");
	}


	/**
	 * Method to check if student is already registered
	 * 
	 * @param studentId
	 * @return Registration Status
	 */
	private boolean getRegistrationStatus(String studentId) {
	    try {
	        boolean registrationStatus = registrationInterface.getRegistrationStatus(studentId);
	        System.out.println("+-------------------------------+");
	        System.out.println("|  Registration Status: " + registrationStatus + "  |");
	        System.out.println("+-------------------------------+");
	        return registrationStatus;
	    } catch (SQLException e) {
	        System.out.println("+-------------------------------+");
	        System.out.println("|       \u001B[31m" + e.getMessage() + "\u001B[0m       |");
	        System.out.println("+-------------------------------+");
	    }
	    return false;
	}


	/**
	 * Drop Course
	 * 
	 * @param studentId
	 */
	private void dropCourse(String studentId) {
	    if (is_registered) {
	        List<Course> registeredCourseList = viewRegisteredCourse(studentId);

	        if (registeredCourseList == null)
	            return;

	        System.out.println("Enter the Course Code: ");
	        String courseCode = sc.next();

	        try {
	            registrationInterface.dropCourse(courseCode, studentId, registeredCourseList);
	            System.out.println("+------------------------------------+");
	            System.out.println("|  You have successfully dropped Course: " + courseCode + "  |");
	            System.out.println("+------------------------------------+");
	        } catch (CourseNotFoundException e) {
	            System.out.println("+-------------------------------------------------------+");
	            System.out.println("|  You have not registered for course: " + e.getCourseCode() + "  |");
	            System.out.println("+-------------------------------------------------------+");
	        } catch (SQLException e) {
	            System.out.println("+-------------------------------+");
	            System.out.println("|       \u001B[31m" + e.getMessage() + "\u001B[0m       |");
	            System.out.println("+-------------------------------+");
	        }
	    } else {
	        System.out.println("+-------------------------------+");
	        System.out.println("|  \u001B[31mPlease complete registration\u001B[0m  |");
	        System.out.println("+-------------------------------+");
	    }
	}


	/**
	 * View all available Courses
	 * 
	 * @param studentId
	 * @return List of available Courses
	 */
	private List<Course> viewCourse(String studentId) {
	    List<Course> course_available = null;

	    try {
	        course_available = registrationInterface.viewCourses(studentId);
	    } catch (SQLException e) {
	        System.out.println("+-------------------------------+");
	        System.out.println("|      \u001B[31m" + e.getMessage() + "\u001B[0m      |");
	        System.out.println("+-------------------------------+");
	    }

	    if (course_available.isEmpty()) {
	        System.out.println("+-----------------+");
	        System.out.println("| NO COURSE AVAILABLE |");
	        System.out.println("+-----------------+");
	        return null;
	    }

	    System.out.println("+------------------+------------------+------------------+------------------+");
	    System.out.println("|    COURSE CODE   |    COURSE NAME   |    INSTRUCTOR    |       SEATS      |");
	    System.out.println("+------------------+------------------+------------------+------------------+");
	    for (Course obj : course_available) {
	        System.out.printf("| %-16s | %-16s | %-16s | %-16s |\n",
	                obj.getCourseCode(), obj.getCourseName(), obj.getInstructorId(), obj.getSeats());
	    }
	    System.out.println("+------------------+------------------+------------------+------------------+");

	    return course_available;
	}


	/**
	 * View Registered Courses
	 * 
	 * @param studentId
	 * @return List of Registered Courses
	 */
	
	private List<Course> viewRegisteredCourse(String studentId) {
	    List<Course> course_registered = null;
	    try {
	        course_registered = registrationInterface.viewRegisteredCourses(studentId);
	    } catch (SQLException e) {
	        System.out.println("+-------------------------------+");
	        System.out.println("|      \u001B[31m" + e.getMessage() + "\u001B[0m      |");
	        System.out.println("+-------------------------------+");
	    }

	    if (course_registered.isEmpty()) {
	        System.out.println("+-------------------------+");
	        System.out.println("| You haven't registered for any course |");
	        System.out.println("+-------------------------+");
	        return null;
	    }

	    System.out.println("+------------------+------------------+------------------+");
	    System.out.println("|    COURSE CODE   |    COURSE NAME   |    INSTRUCTOR    |");
	    System.out.println("+------------------+------------------+------------------+");
	    for (Course obj : course_registered) {
	        System.out.printf("| %-16s | %-16s | %-16s |\n",
	                obj.getCourseCode(), obj.getCourseName(), obj.getInstructorId());
	    }
	    System.out.println("+------------------+------------------+------------------+");

	    return course_registered;
	}


	/**
	 * View grade card for particular student
	 * 
	 * @param studentId
	 */
	private void viewGradeCard(String studentId) {
	    List<Grade> grade_card = null;
	    boolean isReportGenerated = false;

	    try {
	        isReportGenerated = registrationInterface.isReportGenerated(studentId);
	        if (isReportGenerated) {
	            grade_card = registrationInterface.viewGradeCard(studentId);
	            System.out.println("+------------------+------------------+------------------+");
	            System.out.println("|   COURSE CODE    |   COURSE NAME    |      GRADE       |");
	            System.out.println("+------------------+------------------+------------------+");

	            if (grade_card.isEmpty()) {
	                System.out.println("| You haven't registered for any course |");
	            } else {
	                for (Grade obj : grade_card) {
	                    System.out.printf("| %-16s | %-16s | %-16s |\n",
	                            obj.getCrsCode(), obj.getCrsName(), obj.getGrade());
	                }
	            }
	            System.out.println("+------------------+------------------+------------------+");
	        } else {
	            System.out.println("+-------------------------+");
	            System.out.println("| Report card not yet generated |");
	            System.out.println("+-------------------------+");
	        }
	    } catch (SQLException e) {
	        System.out.println("+-------------------------------+");
	        System.out.println("|      \u001B[31m" + e.getMessage() + "\u001B[0m      |");
	        System.out.println("+-------------------------------+");
	    }
	}


	private void make_payment(String studentId) {
	    double fee = 1000.0;
	    boolean isReg = false;
	    boolean isPaid = false;

	    try {
	        isReg = registrationInterface.getRegistrationStatus(studentId);
	        isPaid = registrationInterface.getPaymentStatus(studentId);
	        fee = registrationInterface.calculateFee(studentId);
	    } catch (SQLException e) {
	        System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
	        return;
	    }

	    if (!isReg) {
	        System.out.println("+---------------------------+");
	        System.out.println("| You have not registered yet |");
	        System.out.println("+---------------------------+");
	    } else if (isReg && !isPaid) {
	        System.out.println("+------------------------------------+");
	        System.out.printf("| Your total fee  = %.2f |\n", fee);
	        System.out.println("| Want to continue Fee Payment (y/n) |");
	        System.out.println("+------------------------------------+");
	        String ch = sc.next();
	        if (ch.equals("y")) {
	            System.out.println("+------------------------+");
	            System.out.println("| Select Mode of Payment |");
	            System.out.println("+------------------------+");

	            int index = 1;
	            for (PaymentModeConstant mode : PaymentModeConstant.values()) {
	                System.out.println(index + " " + mode);
	                index++;
	            }

	            int selectedModeIndex = sc.nextInt();
	            PaymentModeConstant selectedMode = PaymentModeConstant.getPaymentMode(selectedModeIndex);

	            if (selectedMode == null) {
	                System.out.println("+--------------+");
	                System.out.println("| Invalid Input |");
	                System.out.println("+--------------+");
	            } else {
	                try {
	                	if(selectedMode == PaymentModeConstant.CARD_PAYMENT) {
	                		System.out.println("+---------------------------+");
	            			System.out.println("|       CARD PAYMENT        |");
	            			System.out.println("+---------------------------+");
	            			System.out.println("| Card Number:              |");
	            			System.out.println("+---------------------------+");
	            			System.out.print("| ");
	            			String num = sc.nextLine();
	            			System.out.println("+---------------------------+");
	            			System.out.println("| Expiry Date:              |");
	            			System.out.println("+---------------------------+");
	            			System.out.print("| ");
	            			num = sc.next();
	            			System.out.println("+---------------------------+");
	            			System.out.println("| CVV:                      |");
	            			System.out.println("+---------------------------+");
	            			System.out.print("| ");
	            			num = sc.next();	            	
	                	}
	                	else if(selectedMode == PaymentModeConstant.NET_BANKING) {
	                		System.out.println("+---------------------------+");
	            			System.out.println("|        NET BANKING        |");
	            			System.out.println("+---------------------------+");
	            			System.out.println("| Bank Name:                |");
	            			System.out.println("+---------------------------+");
	            			System.out.print("| ");
	            			String num = sc.nextLine();
	            			System.out.println("+---------------------------+");
	            			System.out.println("| Account Number:           |");
	            			System.out.println("+---------------------------+");
	            			System.out.print("| ");
	            			num = sc.next();
	            			System.out.println("+---------------------------+");
	            			System.out.println("| IFSC CODE:                |");
	            			System.out.println("+---------------------------+");
	            			System.out.print("| ");
	            			num = sc.next();
	                	}
	                	else if (selectedMode == PaymentModeConstant.OFFLINE) {
	                		System.out.println("+---------------------------+");
	            			System.out.println("|      OFFLINE PAYMENT      |");
	            			System.out.println("+---------------------------+");
	            			System.out.println("| Bank Name:                |");
	            			System.out.println("+---------------------------+");
	            			System.out.print("| ");
	            			String num = sc.nextLine();
	            			System.out.println("+---------------------------+");
	            			System.out.println("| Challan/Cheque Number:    |");
	            			System.out.println("+---------------------------+");
	            			System.out.print("| ");
	            			num = sc.next();	            			
	                	}
	                	
	                    notificationInterface.sendNotification(NotificationTypeConstant.PAYED, studentId, selectedMode, fee);
	                    System.out.println("+-------------------------------------------+");
	                    System.out.println("| Payment Successful by StudentId: " + studentId + " |");
	                    System.out.println("+-------------------------------------------+");
	                    registrationInterface.setPaymentStatus(studentId);
	                } catch (Exception e) {
	                    System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
	                }
	            }
	        }
	    } else {
	        System.out.println("+------------------------------------+");
	        System.out.println("| You have already paid the fees |");
	        System.out.println("+------------------------------------+");
	    }
	}

}