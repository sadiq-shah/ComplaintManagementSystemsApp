package com.example.complaintmanagementsystem.Models.User;

public class Student extends User {
    public String hostelNo;
    public int roomNo;
    public Boolean isSingleRoom;
    public String faculty;
    public int regno;

    public Student(String name, String email, int role, String hostelNo, int roomNo, String faculty,  int regno, Boolean isSingleRoom)
    {
        super(name,email,role);
        this.hostelNo = hostelNo;
        this.roomNo = roomNo;
        this.faculty = faculty;
        this.regno = regno;
        this.isSingleRoom = isSingleRoom;
    }



}
