package com.flipkart.business;

import java.util.UUID;

import com.flipkart.dao.PaymentNotificationDaoImpl;


public class PaymentNotificationTasks implements PaymentNotificationInterface{
	
	private static volatile PaymentNotificationTasks instance=null;
	PaymentNotificationDaoImpl notificationDaoInterface=PaymentNotificationDaoImpl.getInstance();
	private PaymentNotificationTasks() {}
	
	public static PaymentNotificationTasks getInstance()
	{
		if(instance==null)
		{
			synchronized(PaymentNotificationTasks.class){
				instance=new PaymentNotificationTasks();
			}
		}
		return instance;
	}
	@Override
	public int sendNotification(String type, int studentId,String modeOfPayment,double amount) {
		return 0;
		
	}

	@Override
	public int sendNotification(String type, String studentId, String modeOfPayment, double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UUID getReferenceId(int notificationId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}