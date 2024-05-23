/**
 *
 */
package com.flipkart.business;


import java.util.UUID;


public interface PaymentNotificationInterface {

  

    public int sendNotification(String type,String studentId,String modeOfPayment,double amount);

	
	UUID getReferenceId(int notificationId);

	int sendNotification(String type, int studentId, String modeOfPayment, double amount);



}