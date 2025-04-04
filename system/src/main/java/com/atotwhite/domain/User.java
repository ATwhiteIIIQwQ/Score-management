package com.atotwhite.domain;

public class User {
    private Integer userId;
    private String userName;
    private String userPassword;

    public User(Integer userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public Integer getUserId() {return userId;}
    public void setUserId(Integer userId) {this.userId = userId;}
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getUserPassword() {return userPassword;}
    public void setUserPassword(String userPassword) {this.userPassword = userPassword;}
}
