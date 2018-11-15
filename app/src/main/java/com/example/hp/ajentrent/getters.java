package com.example.hp.ajentrent;

public class getters {
    private String Fname;
    private String Lname;
    private String Email;
    private String password;
    private String Gender;
    private String profession;


    public getters(String fname, String lname, String email, String passwrd, String gender, String profession) {
        Fname = fname;
        Lname = lname;
        this.Email = email;
        this.password = passwrd;
        Gender = gender;
        this.profession = profession;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
       this.Email = email;
    }

    public String getPasswrd() {
        return password;
    }

    public void setPasswrd(String passwrd) {
        this.password = passwrd;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
