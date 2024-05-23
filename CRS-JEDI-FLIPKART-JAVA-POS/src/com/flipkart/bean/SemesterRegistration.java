package com.flipkart.bean;

public class SemesterRegistration {
    private int studentId;
    private int semester;

    SemesterRegistration(int studentId, int semester) {
        this.studentId = studentId;
        this.semester = semester;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}