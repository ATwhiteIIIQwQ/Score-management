package com.atotwhite.domain;

public class Student {
    private Integer studentId;
    private String studentNum;
    private String studentName;
    private Integer studentGrade;
    private String studentClass;

    public Student(Integer studentId, String studentNum, String studentName, Integer studentGrade, String studentClass) {
        this.studentId = studentId;
        this.studentNum = studentNum;
        this.studentName = studentName;
        this.studentGrade = studentGrade;
        this.studentClass = studentClass;
    }

    public Integer getStudentId() {return studentId;}
    public void setStudentId(Integer studentId) {this.studentId = studentId;}
    public String getStudentNum() {return studentNum;}
    public void setStudentNum(String studentNum) {this.studentNum = studentNum;}
    public String getStudentName() {return studentName;}
    public void setStudentName(String studentName) {this.studentName = studentName;}
    public Integer getStudentGrade() {return studentGrade;}
    public void setStudentGrade(Integer studentGrade) {this.studentGrade = studentGrade;}
    public String getStudentClass() {return studentClass;}
    public void setStudentClass(String studentClass) {this.studentClass = studentClass;}

}