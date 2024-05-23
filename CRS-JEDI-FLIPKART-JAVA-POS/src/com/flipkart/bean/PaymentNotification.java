package com.flipkart.bean;

public class PaymentNotification {
    private int notificationId;
    private String notificationMessage;

    PaymentNotification(int notificationId, String notificationMessage) {
        this.notificationId = notificationId;
        this.notificationMessage = notificationMessage;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }
}