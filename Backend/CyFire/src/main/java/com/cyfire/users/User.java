package com.cyfire.users;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Column(name = "first_name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String firstName;

    @Column(name = "last_name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String lastName;

    @Column(name = "phone_number")
    @NotFound(action = NotFoundAction.IGNORE)
    private String phoneNumber;

    @Column(name = "gender")
    @NotFound(action = NotFoundAction.IGNORE)
    private String gender;

    @Column(name = "major")
    @NotFound(action = NotFoundAction.IGNORE)
    private String major;

    @Column(name = "user_password")
    @NotFound(action = NotFoundAction.IGNORE)
    private String userPassword;

    @Column(name = "classification")
    @NotFound(action = NotFoundAction.IGNORE)
    private String classification;

    @Id
    @Column(name = "net_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private String netID;

    @Column(name = "age")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer age;

    @Column(name = "code")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer code;

    @Column(name = "entered_code")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer enteredCode;

    @Column(name = "verified")
    @NotFound(action = NotFoundAction.IGNORE)
    private boolean verified;

    // MAKE A DIDDLY DARN DEFAULT AND CONSTRUCTOR FOR THE MOCKITO TESTS
    public User(String first_name, String last_name, String phone_number, String gender, String major,
                String user_password, String classification, String net_id, Integer age, Integer code, Integer entered_code,
                boolean verified) {
        super();
        this.firstName = first_name;
        this.lastName = last_name;
        this.phoneNumber = phone_number;
        this.gender = gender;
        this.major = major;
        this.userPassword = user_password;
        this.classification = classification;
        this.netID = net_id;
        this.age = age;
        this.code = code;
        this.enteredCode = entered_code;
        this.verified = verified;
    }

    public User() {

    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("net_id: ", this.getNetID()).append("first_name: ", this.getFirstName())
                .append("\nlast_name: ", this.getLastName()).append("phone_number: ", this.getPhoneNumber())
                .append("gender: ", this.getGender()).append("\nmajor: ", this.getMajor())
                .append("user_password: ", this.getUserPassword()).append("classification: ", this.getClassification())
                .append("age: ", this.getAge()).append("\ncode: ", this.getCode())
                .append("entered_code: ", this.getEnteredCode()).append("verified: ", this.isVerified()).toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String user_password) {
        this.userPassword = user_password;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String net_id) {
        this.netID = net_id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getEnteredCode() {
        return enteredCode;
    }

    public void setEnteredCode(Integer entered_code) {
        this.enteredCode = entered_code;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

}
