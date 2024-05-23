/**
 * 
 */
package com.flipkart.dao;

import java.sql.SQLException;



public interface PaymentNotificationDaoInterface {

	public int sendNotification(String type,int studentId,String modeOfPayment,double amount) throws SQLException;
	
}