
package com.flipkart.dao;

import java.util.ArrayList;  
import java.util.List;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.*;

import com.flipkart.utils.DBUtils;


public class AdminDaoImpl implements AdminDaoInterface{
	
	private static volatile AdminDaoImpl instance = null;
	private PreparedStatement statement = null;
	
	private AdminDaoImpl(){}
	

	public static AdminDaoImpl getInstance()
	{
		if(instance == null)
		{
			synchronized(AdminDaoImpl.class){
				instance = new AdminDaoImpl();
			}
		}
		return instance;
	}
	
	Connection connection = DBUtils.getConnection();
	
	@Override
	public void removeCourse(String courseCode) 
	{
		statement = null;
		try {
			String sql = "delete from Course where courseCode = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1,courseCode);
			int row = statement.executeUpdate();
			
			System.out.println(row + " entries deleted.");
			if(row == 0) {
				System.out.println(courseCode + " not in catalog!");
			}

			System.out.println("Course with courseCode: " + courseCode + " deleted.");
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
		}
		
	}

	@Override
	public void addCourse(Course course){
		
		statement = null;
		try {
			
			String sql = "insert into Course(courseCode, CourseName, InstructorId, seats, coursefee) values (?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, course.getCourseCode());
			statement.setString(2, course.getCourseName());
			statement.setString(3, course.getInstructorId());
			statement.setInt(4, course.getSeats());
			statement.setInt(5, course.getCourseFee());
			
			int row = statement.executeUpdate();
			
			System.out.println(row + " course added");
			if(row == 0) {
				System.out.println("Course with courseCode: " + course.getCourseCode() + "not added to catalog.");
			}
			
			System.out.println("Course with courseCode: " + course.getCourseCode() + " is added to catalog."); 
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			
		}
		
	}
	

	@Override
	public List<Student> viewPendingAdmissions() {
		
		statement = null;
		List<Student> userList = new ArrayList<Student>();
		try {
			
			String sql = "select userId,name, password, role, gender, address, studentID from student, user where isapproved = 0 and studentId = userId";
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				
				Student user = new Student();
				user.setUserId(resultSet.getString(1));
				user.setName(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRole(resultSet.getString(4));
				user.setGender(resultSet.getString(5));
				user.setAddress(resultSet.getString(6));
				user.setStudentId(resultSet.getString(7));
				userList.add(user);
				
			}
			
			System.out.println(userList.size() + " students have pending-approval.");
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			
		}
		
		return userList;
		
	}

	@Override
	public void approveStudent(String studentId){
		
		statement = null;
		try {
			String sql = "update Student set isApproved = 1 where studentID = ?";;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1,studentId);
			int row = statement.executeUpdate();
			
			System.out.println(row + " student approved.");
			if(row == 0) {
				//System.out.println("Student with studentId: " + studentId + " not found.");
			}
			
			System.out.println("Student with studentId: " + studentId + " approved by admin.");
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			
		}
		
	}


	@Override
	public void addUser(User user){
		
		statement = null;
		try {
			
			String sql = "insert into User(userID, name, password, role, gender, address) values (?, ?, ?, ?, ?, ?)";;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, user.getUserId());
			statement.setString(2, user.getName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getRole());
			statement.setString(5, user.getGender());
			statement.setString(6, user.getAddress());
			
			int row = statement.executeUpdate();
			
			System.out.println(row + " user added.");
			if(row == 0) {
				System.out.println("User with userId: " + user.getUserId() + " not added.");
			}

			System.out.println("User with userId: " + user.getUserId() + " added."); 
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			
		}
		
	}

	@Override
	public void addProfessor(Professor professor)
	{	
		
		statement = null;
		try {
			
			String sql = "insert into Professor(professorId, department, designation) values (?, ?, ?)";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, professor.getUserId());
			statement.setString(2, professor.getDepartment());
			statement.setString(3, professor.getDesignation());
			int row = statement.executeUpdate();

			System.out.println(row + " professor added.");
			if(row == 0) {
				System.out.println("Professor with professorId: " + professor.getUserId() + " not added.");
			}
			
			System.out.println("Professor with professorId: " + professor.getUserId() + " added."); 
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());			
		} 
		
	}

	@Override
	public void assignCourse(String courseCode, String professorId){
		
		statement = null;
		try {
			String sql = "update Course set InstructorId = ? where courseCode = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1,professorId);
			statement.setString(2,courseCode);
			int row = statement.executeUpdate();
			
			System.out.println(row + " course assigned.");
			if(row == 0) {
				System.out.println(courseCode + " not found");
			}
			
			System.out.println("Course with courseCode: " + courseCode + " is assigned to professor with professorId: " + professorId + ".");
		
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			
		}
		
	}
	@Override
	public List<Course> viewCourses() {
		
		statement = null;
		List<Course> courseList = new ArrayList<>();
		try {
			
			String sql = "select courseCode, courseName, InstructorId from Course";
			statement = connection.prepareStatement(sql);
			//statement.setInt(1, catalogId);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Course course = new Course();
				course.setCourseCode(resultSet.getString(1));
				course.setCourseName(resultSet.getString(2));
				course.setInstructorId(resultSet.getString(3));
				courseList.add(course);
				
			}
			
			System.out.println("Number of courses in the Catalog are : " + courseList.size());
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			
		}
		
		return courseList; 
		
	}
	

	@Override
	public List<Professor> viewProfessors() {
		
		statement = null;
		List<Professor> professorList = new ArrayList<Professor>();
		try {
			
			String sql = "select userID, name, gender, department, designation, address from Professor natural join User where userID = professorId";
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Professor professor = new Professor();
				professor.setUserId(resultSet.getString(1));
				professor.setName(resultSet.getString(2));
				professor.setGender(resultSet.getString(3));
				professor.setDepartment(resultSet.getString(4));
				professor.setDesignation(resultSet.getString(5));
				professor.setAddress(resultSet.getString(6));
				professor.setRole("PROFESSOR");
				professor.setPassword("*********");
				professorList.add(professor);
				
			}
			
			System.out.println(professorList.size() + " professors in the institute.");
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			
		}
		return professorList;
	}
	
	@Override
	public List<RegisteredCourse> generateGradeCard(String Studentid) 
	{
		List<RegisteredCourse> CoursesOfStudent = new ArrayList<RegisteredCourse>();
		
		try {
					String sql = "select * from Course inner join RegisteredCourses on Course.courseCode = RegisteredCourses.courseId where RegisteredCourses.student = ?";
					statement = connection.prepareStatement(sql);
					statement.setString(1, Studentid);
					ResultSet resultSet = statement.executeQuery();
					
					while(resultSet.next()) {
						
						Course course = new Course();
						RegisteredCourse temp = new RegisteredCourse() ;
						course.setCourseCode(resultSet.getString(1));
						course.setCourseName(resultSet.getString(2));
						course.setInstructorId(resultSet.getString(3));
						course.setSeats(resultSet.getInt(4));
						
						
						temp.setCourseId(course.getCourseCode());
						System.out.println("course object generated");
						temp.setStudentId(Studentid);
						
						
						temp.setGrade(resultSet.getString(8));
						
						System.out.println("graded");
						CoursesOfStudent.add(temp);
						
					}
						
					
				}catch(SQLException se) {
					
					System.out.println(se.getMessage());
					
				}
		
		return CoursesOfStudent;
		
		
	}



	
	
	

}