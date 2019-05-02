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



    public void setFirstName(String first)
   {
       firstName = first;
   }

    public void setLastName(String last)
   {
        lastName = last;
   }

    public void setPhoneNumber(String num)
   {
       phoneNumber = num;
   }

    public void setGender(String gen)
   {
       gender = gen;
   }

    public void setMajor(String maj)
   {
       major = maj;
   }

    public void setPassword(String pass) {password = pass;}

    public void setNetID(String id)
   {
       netID = id;
   }

   public void setClassification(String theClass) {classification = theClass;}

   public void setAge(String theAge) {age = theAge;}

   public void setLikes(String like) {likes = like;}


   public void setMatches(String matches) {this.matches = matches;}

   public String getMatches() {return matches; }

    public String getLikes() { return likes;}


    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getGender()
    {
        return gender;
    }

    public String getMajor()
    {
        return major;
    }

    public String getNetID()
    {
        return netID;
    }

    public String getPassword()
    {
        return password;
    }

    public String getClassification()
    {
        return classification;
    }

    public String getAge()
    {
        return age;
    }





}
