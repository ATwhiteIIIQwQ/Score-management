package com.atotwhite.domain;

public class Score {
    private Integer scoreId;
    private Integer studentId;
    private String studentName;
    private Integer courseId;
    private String courseName;
    private Integer score;

    public Score(Integer scoreId, Integer studentId, String studentName, Integer courseId, String courseName, Integer score) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.score = score;
    }

    public Integer getScoreId() {return scoreId;}
    public void setScoreId(Integer scoreId) {this.scoreId = scoreId;}
    public Integer getStudentId() {return studentId;}
    public void setStudentId(Integer studentId) {this.studentId = studentId;}
    public String getStudentName() {return studentName;}
    public void setStudentName(String studentName) {this.studentName = studentName;}    
    public Integer getCourseId() {return courseId;}
    public void setCourseId(Integer courseId) {this.courseId = courseId;}
    public String getCourseName() {return courseName;}
    public void setCourseName(String courseName) {this.courseName = courseName;}
    public Integer getScore() {return score;}
    public void setScore(Integer score) {this.score = score;}

}
