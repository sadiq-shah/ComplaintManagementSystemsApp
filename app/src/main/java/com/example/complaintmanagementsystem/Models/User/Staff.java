package com.example.complaintmanagementsystem.Models.User;

public class Staff extends User{
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    private String designation;

    public Staff(String name, String email, int role,String department, String designation){
        super(name,email,role);
        this.department = department;
        this.designation = designation;
    }
}
