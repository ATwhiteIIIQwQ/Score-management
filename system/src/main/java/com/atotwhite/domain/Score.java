package com.atotwhite.domain;

public class Score {
    private int scoreId;
    private int studentId;
    private int courseId;
    private int score;

    public Score(int scoreId, int studentId, int courseId, int score) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
    }

    public int getScoreId() {return scoreId;}
    public void setScoreId(int scoreId) {this.scoreId = scoreId;}
    public int getStudentId() {return studentId;}
    public void setStudentId(int studentId) {this.studentId = studentId;}
    public int getCourseId() {return courseId;}
    public void setCourseId(int courseId) {this.courseId = courseId;}
    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}

}
