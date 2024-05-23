package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.utils.DBUtils;

public class UserDaoImpl implements UserDaoInterface{
	private static volatile UserDaoImpl instance=null;
//	private static Logger logger = Logger.getLogger(UserDaoOperation.class);

	/**
	 * Default Constructor
	 */
	private UserDaoImpl()
	{
		
	}
	
	/**
	 * Method to make UserDaoOperation Singleton
	 * @return
	 */
	public static UserDaoImpl getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(UserDaoImpl.class){
				instance=new UserDaoImpl();
			}
		}
		return instance;
	}

	/**
	 * Method to update password of user in DataBase
	 * @param userID
	 * @param newPassword
	 * @return Update Password operation Status
	 */
	@Override
	public boolean updatePassword(String userId, String newPassword) {
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("update User set password=? where userID = ? ");
			
			statement.setString(1, newPassword);
			statement.setString(2, userId);
			
			int row = statement.executeUpdate();
			
			if(row==1)
				return true;
			else
				return false;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * Method to verify credentials of Users from DataBase
	 * @param userId
	 * @param password
	 * @return Verify credentials operation status
	 * @throws UserNotFoundException
	 */
	@Override
	public boolean verifyCredentials(String userId, String password) 
   {
		Connection connection = DBUtils.getConnection();
		try
		{
			//open db connection
			PreparedStatement preparedStatement=connection.prepareStatement("select password from User where userID = ?");
			preparedStatement.setString(1,userId);
			ResultSet resultSet = preparedStatement.executeQuery();
						
			if(!resultSet.next())
			{
				return false;
			}

			else if(password.equals(resultSet.getString("password")))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch(SQLException ex)
		{
			System.out.println("Something went wrong, please try again! "+ ex.getMessage());
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	
	/**
	 * Method to get RoleConstant of User from DataBase
	 * @param userId
	 * @return RoleConstant
	 */
	@Override
	public String getRole(String userId) 
	{
		Connection connection=DBUtils.getConnection();
		try {
			connection=DBUtils.getConnection();
			
			PreparedStatement statement = connection.prepareStatement("select role from User where userID = ?;");
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();
			
						
			if(rs.next())
			{
//				System.out.println(rs.getString("role"));
				return rs.getString("role");
			}
				
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Method to get name of User from DataBase
	 * @param userId
	 * @return name
	 */
	@Override
	public String getName(String userId) 
	{
		Connection connection=DBUtils.getConnection();
		try {
			connection=DBUtils.getConnection();
			
			PreparedStatement statement = connection.prepareStatement("select name from User where userID = ?;");
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();
			
						
			if(rs.next())
			{
//				System.out.println(rs.getString("role"));
				return rs.getString("name");
			}
				
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean updatePassword(String userID) {
		// TODO Auto-generated method stub
		return false;
	}

	
}