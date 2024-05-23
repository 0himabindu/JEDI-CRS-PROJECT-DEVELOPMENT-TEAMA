package com.flipkart.bean;

public class Payment {
    private int studentId;
    private int referenceId;
    private int amount;
    private String status;

    Payment(int studentId, int referenceId, int amount, String status) {
        this.studentId = studentId;
        this.referenceId = referenceId;
        this.amount = amount;
        this.status = status;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}