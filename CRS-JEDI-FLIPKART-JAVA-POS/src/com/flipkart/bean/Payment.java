package com.flipkart.bean;

public class Payment {
    private String studentId;
    private String referenceId;
    private int amount;
    private String status;

    Payment(String studentId, String referenceId, int amount, String status) {
        this.studentId = studentId;
        this.referenceId = referenceId;
        this.amount = amount;
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}