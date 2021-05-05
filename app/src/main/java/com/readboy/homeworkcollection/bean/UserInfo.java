package com.readboy.homeworkcollection.bean;

public class UserInfo {
    private String userAvatar;
    private String userName;
    private String userGender;
    private int userGradeId;
    private int userSchoolId;
    private String userSchoolName;

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public int getUserGradeId() {
        return userGradeId;
    }

    public void setUserGradeId(int userGradeId) {
        this.userGradeId = userGradeId;
    }

    public int getUserSchoolId() {
        return userSchoolId;
    }

    public void setUserSchoolId(int userSchoolId) {
        this.userSchoolId = userSchoolId;
    }

    public String getUserSchoolName() {
        return userSchoolName;
    }

    public void setUserSchoolName(String userSchoolName) {
        this.userSchoolName = userSchoolName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userAvatar='" + userAvatar + '\'' +
                ", userName='" + userName + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userGradeId=" + userGradeId +
                ", userSchoolId=" + userSchoolId +
                ", userSchoolName='" + userSchoolName + '\'' +
                '}';
    }
}
