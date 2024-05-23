package com.flipkart.bean;

import java.util.ArrayList;

public class Catalog {
    private int semester;
    private ArrayList<Course> courseList;

    Catalog(int semester, ArrayList<Course> courseList) {
        this.semester = semester;
        this.courseList = courseList;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }
}