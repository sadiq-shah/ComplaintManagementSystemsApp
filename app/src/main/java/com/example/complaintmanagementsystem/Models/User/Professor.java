package com.example.complaintmanagementsystem.Models.User;

public class Professor extends User {
    public String faculty;
    public String designation;
    public String officeNo;


    public Professor(String name, String email, int role,String faculty, String designation, String officeNo){
        super(name,email,role);
        this.faculty = faculty;
        this.designation = designation;
        this.officeNo = officeNo;
    }

}
