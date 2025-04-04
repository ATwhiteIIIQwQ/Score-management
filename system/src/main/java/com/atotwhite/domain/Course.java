package com.atotwhite.domain;

public class Course {
    private Integer courseId;
    private String courseName;
    private Integer courseCredit;

    public Course(Integer courseId, String courseName, Integer courseCredit) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
    }

    public Integer getCourseCredit() {return courseCredit;}
    public void setCourseCredit(Integer courseCredit) {this.courseCredit = courseCredit;}
    public String getCourseName() {return courseName;}
    public void setCourseName(String courseName) {this.courseName = courseName;}
    public Integer getCourseId() {return courseId;}
    public void setCourseId(Integer courseId) {this.courseId = courseId;}
}
