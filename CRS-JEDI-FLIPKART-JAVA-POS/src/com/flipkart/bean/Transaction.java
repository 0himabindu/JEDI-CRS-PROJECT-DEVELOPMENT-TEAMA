package com.flipkart.bean;

public class Transaction {
    private int transactionId;
    private String paymentMethod;
    private String paymentDetails; // this is a json file, stored in a string format;

    Transaction(int transactionId, String paymentMethod, String paymentDetails) {
        this.transactionId = transactionId;
        this.paymentMethod = paymentMethod;
        this.paymentDetails = paymentDetails;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}