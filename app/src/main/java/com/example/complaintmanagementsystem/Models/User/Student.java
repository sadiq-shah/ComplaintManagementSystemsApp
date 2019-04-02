package com.example.complaintmanagementsystem.Models.User;

public class Student extends User {
    private String hostelNo;
    private int roomNo;
    private Boolean isSingleRoom;
    private String faculty;
    private int regno;

    public Student(String password, String name, String email, int role, String hostelNo, int roomNo, String faculty,  int regno, Boolean isSingleRoom)
    {
        super(password, name,email,role);
        this.hostelNo = hostelNo;
        this.roomNo = roomNo;
        this.faculty = faculty;
        this.regno = regno;
        this.isSingleRoom = isSingleRoom;
    }



}
