package com.atotwhite.domain;

public class Score {
    private int id;
    private String name;
    private int age;
    private String address;
    private int score;

    public Score(int id, String name, int age, String address,int score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.score = score;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}
}
