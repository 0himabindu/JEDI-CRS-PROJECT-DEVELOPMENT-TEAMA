package com.flipkart.bean;

public class RegisteredCourse {
    private int courseId;
    private int semester;
    private int studentId;
    private String grade;

    RegisteredCourse(int courseId, int semester, int studentId, String grade) {
        this.courseId = courseId;
        this.semester = semester;
        this.studentId = studentId;
        this.grade = grade;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}