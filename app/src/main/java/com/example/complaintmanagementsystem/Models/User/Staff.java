package com.example.complaintmanagementsystem.Models.User;

public class Staff extends User{
    public  String department;
    public  String designation;

    public Staff(String name, String email, int role,String department, String designation){
        super(name,email,role);
        this.department = department;
        this.designation = designation;
    }
}
