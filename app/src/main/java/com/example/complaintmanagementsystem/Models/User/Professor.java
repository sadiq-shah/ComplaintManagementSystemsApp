package com.example.complaintmanagementsystem.Models.User;

public class Professor extends User {
    private String faculty;
    private String designation;
    private String officeNo;


    public Professor(String password, String name, String email, int role,String faculty, String designation, String officeNo){
        super(password,name,email,role);
        this.faculty = faculty;
        this.designation = designation;
        this.officeNo = officeNo;
    }

}
