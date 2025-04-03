package com.atotwhite.domain;

public class Student {
    private int studentId;
    private String studentNum;
    private String studentName;
    private int studentGrade;
    private String studentClass;

    public Student(int studentId, String studentNum, String studentName, int studentGrade, String studentClass) {
        this.studentId = studentId;
        this.studentNum = studentNum;
        this.studentName = studentName;
        this.studentGrade = studentGrade;
        this.studentClass = studentClass;
    }

    public int getStudentId() {return studentId;}
    public void setStudentId(int studentId) {this.studentId = studentId;}
    public String getStudentNum() {return studentNum;}
    public void setStudentNum(String studentNum) {this.studentNum = studentNum;}
    public String getStudentName() {return studentName;}
    public void setStudentName(String studentName) {this.studentName = studentName;}
    public int getStudentGrade() {return studentGrade;}
    public void setStudentGrade(int studentGrade) {this.studentGrade = studentGrade;}
    public String getStudentClass() {return studentClass;}
    public void setStudentClass(String studentClass) {this.studentClass = studentClass;}

}