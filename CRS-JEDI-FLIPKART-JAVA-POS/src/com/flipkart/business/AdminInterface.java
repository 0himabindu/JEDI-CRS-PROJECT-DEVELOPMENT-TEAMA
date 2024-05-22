/**
 * 
 */
package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

/**
 * 
 */
public interface AdminInterface {
	public boolean addProfessor(String userID, String name, String gender, String role, String password, String address);

    public boolean assignCourse(int professorId, String courseCode);

    public boolean approveStudent(int studentId);

    public boolean updateDetails();

    public boolean addCourse(Course newCourse);

    public boolean removeCourse(String courseCode);

    public boolean generateReportCard();
}
