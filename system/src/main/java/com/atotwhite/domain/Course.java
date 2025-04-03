package com.atotwhite.domain;

public class Course {
    private int courseId;
    private String courseName;
    private int courseCredit;

    public Course(int courseId, String courseName, int courseCredit) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
    }

    public int getCourseCredit() {return courseCredit;}
    public void setCourseCredit(int courseCredit) {this.courseCredit = courseCredit;}
    public String getCourseName() {return courseName;}
    public void setCourseName(String courseName) {this.courseName = courseName;}
    public int getCourseId() {return courseId;}
    public void setCourseId(int courseId) {this.courseId = courseId;}
}
