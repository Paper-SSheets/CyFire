package com.example.loginwithregisterpage;

public class UserAccount {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String major;
    private String netID;
    private String password;
    private String classification;
    private String age;
    private String likes = "";
    private String matches = "";

    public String getMatches() {
        return matches;
    }

    public void setMatches(String matches) {
        this.matches = matches;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String like) {
        likes = like;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first) {
        firstName = first;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last) {
        lastName = last;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String num) {
        phoneNumber = num;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gen) {
        gender = gen;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String maj) {
        major = maj;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String id) {
        netID = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        password = pass;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String theClass) {
        classification = theClass;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String theAge) {
        age = theAge;
    }


}
