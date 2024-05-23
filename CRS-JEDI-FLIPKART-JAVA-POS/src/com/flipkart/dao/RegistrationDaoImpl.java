package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.utils.DBUtils;



public class RegistrationDaoImpl implements RegistrationDAOInterface{
    
	
	private static volatile RegistrationDaoImpl instance=null;
//	private static Logger logger = Logger.getLogger(RegistrationDaoOperation.class);
	private PreparedStatement stmt = null;
	
	/**
	 * Default Constructor
	 */
	private RegistrationDaoImpl() {}
	
	/**
	 * Method to make RegistrationDaoOperation Singleton
	 * @return
	 */
	public static RegistrationDaoImpl getInstance()
	{
		if(instance==null)
		{
			synchronized(RegistrationDaoImpl.class){
				instance=new RegistrationDaoImpl();
			}
		}
		return instance;
	}


	@Override
	public boolean addCourse(String courseCode, String studentId) throws SQLException{
		
		Connection conn = DBUtils.getConnection();
		
		try 
		{
			stmt = conn.prepareStatement("insert into registeredcourse (studentId,courseCode,grade) values ( ? , ?, ?)");
			stmt.setString(1, studentId);
			stmt.setString(2, courseCode);
			stmt.setString(3, "-");
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement("UPDATE Student SET isRegistered = '1' WHERE (studentId = ?);");
			stmt.setString(1, studentId);
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement("update course set seats = seats-1 where courseCode = ? ");
			stmt.setString(1, courseCode);
			stmt.executeUpdate();
			return true;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		return false;
		
	}
	
	
	/**
	 * Number of registered courses for a student
	 * @param studentId
	 * @return Number of registered courses for a student
	 * @throws SQLException 
	 */
	@Override
	public int numOfRegisteredCourses(String studentId) throws SQLException{
		
		Connection conn = DBUtils.getConnection();
		
		int count = 0;
		try {

			stmt = conn.prepareStatement(" select studentId from registeredcourse where studentId = ? ");
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count++;
			}
			return count;

		}
		catch (SQLException se) 
		{

			System.out.println(se.getMessage());

		} 
		catch (Exception e)
		{

			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return count;
	}


	@Override
	public boolean seatAvailable(String courseCode) throws SQLException {

		Connection conn = DBUtils.getConnection();
		try 
		{
			stmt = conn.prepareStatement("select seats from course where courseCode = ?;");
			stmt.setString(1, courseCode);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return (rs.getInt("seats") > 0);
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return true;
		

	}
	


	/**
	 * Method checks if the student is registered for that course
	 * @param courseCode
	 * @param studentId
	 * @return Students registration status
	 * @throws SQLException 
	 */
	@Override
	public boolean isRegistered(String courseCode, String studentId) throws SQLException{
		
		Connection conn = DBUtils.getConnection();
		
		boolean check = false;
		try
		{
			stmt = conn.prepareStatement(" select courseCode from registeredcourse where courseCode=? and studentId=? ");
			stmt.setString(1, courseCode);
			stmt.setString(2, studentId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				check = true;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return check;
		
	}


	/**
	 * Drop Course selected by student
	 * @param courseCode : code for selected course
	 * @param studentId
	 * @return status of drop course operation
	 * @throws CourseNotFoundException 
	 */
	@Override
	public boolean dropCourse(String courseCode, String studentId) throws SQLException {
	
		Connection conn = DBUtils.getConnection();
		
		
			try
			{
				stmt = conn.prepareStatement("delete from registeredcourse where courseCode = ? AND studentId = ?;");
				stmt.setString(1, courseCode);
				stmt.setString(2, studentId);
				stmt.execute();
				
				stmt = conn.prepareStatement("update course set seats = seats + 1 where  courseCode = ?;");
				stmt.setString(1, courseCode);
				stmt.execute();
				
				stmt.close();
				
				return true;
			}
			catch(Exception e)
			{
				System.out.println("Exception found" + e.getMessage());
			}
			finally
			{
	
				stmt.close();
				conn.close();
			}
			
		
		return false;
		
	}
	
	/**
	 * Method to retrieve fee for the selected courses from the database and calculate total fee
	 * @param studentId
	 * @return Fee Student has to pay
	 * @throws SQLException 
	 */
	
	@Override
	public double calculateFee(String studentId) throws SQLException
	{
		Connection conn = DBUtils.getConnection();
		double fee = 0;
		try
		{
			stmt = conn.prepareStatement("select sum(courseFee) from course where courseCode in (select courseCode from registeredcourse where studentId = ?);");
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			fee = rs.getDouble(1);
		}
		catch(SQLException e)
		{
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return fee;
	}

	@Override
	public List<Course> viewCourses(String studentId) throws SQLException {
		
		List<Course> availableCourseList = new ArrayList<>();
		Connection conn = DBUtils.getConnection();
		
		try 
		{
			stmt = conn.prepareStatement(" select * from course where courseCode not in  (select courseCode  from registeredcourse where studentId = ?) and seats > 0");
			stmt.setString(1, studentId);
			//stmt.setBoolean(2, true);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				availableCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
						rs.getString("professorId"), rs.getInt("seats"), rs.getInt("courseFee")));

			}
			

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return availableCourseList;
		
	}

	/**
	 * Method to get the list of courses registered by the student
	 * @param studentId
	 * @return list of courses registered by student
	 * @throws SQLException 
	 */
	@Override
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException {

		Connection conn = DBUtils.getConnection();
		List<Course> registeredCourseList = new ArrayList<>();
		try 
		{
			stmt = conn.prepareStatement(" select * from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where registeredcourse.studentId = ?");
			stmt.setString(1, studentId);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				registeredCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
						rs.getString("professorId"), rs.getInt("seats"), rs.getInt("courseFee")));

			}
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());

		} 
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return registeredCourseList;
	}

	/**
	 * Method to retrieve Student's registration status
	 * @param studentId
	 * @return Student's registration status
	 * @throws SQLException
	 */
	@Override
	public boolean getRegistrationStatus(String studentId) throws SQLException
	{
		Connection conn = DBUtils.getConnection();
		boolean status = false;
		try 
		{
			stmt = conn.prepareStatement(" select isRegistered from student where studentId = ? ");
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			status = rs.getBoolean(1);
			//System.out.println(status);	
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());

		} 
		finally
		{
			stmt.close();
			conn.close();
		}

		return status;
	}
	/**
	 * Method to set Student's registration status
	 * @param studentId
	 * @throws SQLException
	 */
	@Override
	public void setRegistrationStatus(String studentId) throws SQLException
	{
		Connection conn = DBUtils.getConnection();
		try 
		{
			stmt = conn.prepareStatement("update student set isRegistered = true  where studentId=?");
			stmt.setString(1, studentId);
			stmt.executeUpdate();

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());

		} 
		finally
		{
			stmt.close();
			conn.close();
		}

	}

	@Override
	public boolean isReportGenerated(String studentId) throws SQLException
	{
		Connection conn = DBUtils.getConnection();
		boolean status = false;
		try 
		{
			stmt = conn.prepareStatement("select isReportGenerated from student where studentId = ?");
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			status = rs.getBoolean(1);
			//System.out.println(status);	
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
	
		} 
		finally
		{
			stmt.close();
			conn.close();
		}
	
		return status;
	}

	@Override
	public boolean getPaymentStatus(String studentId) throws SQLException 
	{
		{
			Connection conn = DBUtils.getConnection();
			boolean status = false;
			try 
			{
				stmt = conn.prepareStatement(" select isPaid from student where studentId = ? ");
				stmt.setString(1, studentId);
				ResultSet rs = stmt.executeQuery();
				rs.next();
				status = rs.getBoolean(1);
				//System.out.println(status);	
			} 
			catch (SQLException e) 
			{
				System.out.println(e.getMessage());

			} 
			finally
			{
				stmt.close();
				conn.close();
			}

			return status;
	}


	}

	@Override
	public void setPaymentStatus(String studentId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		try 
		{
			stmt = conn.prepareStatement("update student set isPaid = true  where studentId=?");
			stmt.setString(1, studentId);
			stmt.executeUpdate();

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());

		} 
		finally
		{
			stmt.close();
			conn.close();
		}

	}

	@Override
	public List<Grade> viewGradeCard(String studentId) throws SQLException {
		
		Connection conn = DBUtils.getConnection();
		List<Grade> grade_List = new ArrayList<Grade>();
		try
		{
			stmt = conn.prepareStatement("select course.courseCode,course.courseName,registeredcourse.grade from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where registeredcourse.studentId = ?;");
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String courseCode = rs.getString("courseCode");
				String courseName = rs.getString("courseName");
				String grade = rs.getString("grade");
				Grade obj = new Grade(courseCode, courseName,grade);
				grade_List.add(obj);
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
			
		}
		
		return grade_List;
	}

	
}