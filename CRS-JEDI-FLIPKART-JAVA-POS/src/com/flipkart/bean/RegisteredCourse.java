package com.flipkart.bean;

public class RegisteredCourse {
    private String courseId;
    private int semester;
    private String studentId;
    private String grade;

    RegisteredCourse(String courseId, int semester, String studentId, String grade) {
        this.courseId = courseId;
        this.semester = semester;
        this.studentId = studentId;
        this.grade = grade;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}