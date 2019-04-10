package com.example.demo.biz.dto;

public class User {

    private int id;

    private String userName;

    private String passWord;

    private String gender;

    private String email;

    private String mobile;

    private String identity;

    private String nationality;

    private String description;

    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getIdentity() {
        return identity;
    }

    public String getNationality() {
        return nationality;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", identity='" + identity + '\'' +
                ", nationality='" + nationality + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
