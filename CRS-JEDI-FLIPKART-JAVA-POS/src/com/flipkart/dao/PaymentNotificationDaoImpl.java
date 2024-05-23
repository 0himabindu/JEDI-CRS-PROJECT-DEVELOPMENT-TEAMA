/**
 * 
 */

package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.flipkart.utils.DBUtils;


public class PaymentNotificationDaoImpl implements PaymentNotificationDaoInterface{

	
	private static volatile PaymentNotificationDaoImpl instance=null;

	private PaymentNotificationDaoImpl()
	{

	}
	
	public static PaymentNotificationDaoImpl getInstance()
	{
		if(instance==null)
		{
			synchronized(PaymentNotificationDaoImpl.class){
				instance=new PaymentNotificationDaoImpl();
			}
		}
		return instance;
	}
	@Override
	public int sendNotification(String type, int studentId,String modeOfPayment,double amount) throws SQLException{
		int notificationId=0;
		Connection connection=DBUtils.getConnection();
		try
		{
			PreparedStatement ps = connection.prepareStatement("insert into notification(studentId,type,referenceId) values(?,?,?);");
			ps.setInt(1, studentId);
			ps.setString(2,type.toString());
			if(type=="PAID")
			{
				UUID referenceId=addPayment(studentId, modeOfPayment,amount);
				ps.setString(3, referenceId.toString());	
			}
			else
				ps.setString(3,"");
				
			ps.executeUpdate();
			ResultSet results=ps.getGeneratedKeys();
			if(results.next())
				notificationId=results.getInt(1);
			
			switch(type)
			{
			case "REGISTRATION":
				System.out.println("Registration successfull. Administration will verify the details and approve it!");
				break;
			case "APPROVED":
				System.out.println("Student with id "+studentId+" has been approved!");
				break;
			case "PAID":
				System.out.println("Student with id "+studentId+" fee has been paid");
			}
			
		}
		catch(SQLException ex)
		{
			throw ex;
		}
		return notificationId;
	}

	public UUID addPayment(int studentId, String modeOfPayment,double amount) throws SQLException
	{
		UUID referenceId;
		Connection connection=DBUtils.getConnection();
		try
		{
			referenceId=UUID.randomUUID();
			PreparedStatement statement = connection.prepareStatement("insert into payment(studentId,modeofPayment,referenceId,amount) values(?,?,?,?);");
			statement.setInt(1, studentId);
			statement.setString(2, modeOfPayment.toString());
			statement.setString(3,referenceId.toString());
			statement.setDouble(4, amount);
			statement.executeUpdate();
		}
		catch(SQLException ex)
		{
			throw ex;
		}
		return referenceId;
	}
	
}