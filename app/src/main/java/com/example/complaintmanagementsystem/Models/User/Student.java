package com.example.complaintmanagementsystem.Models.User;

public class Student extends User {
    private String hostelNo;
    private int roomNo;
    private Boolean isSingleRoom;
    private String faculty;
    private int batch;
    private int regno;

    public Student(String username,String password, String name, String email, int role, String hostelNo, int roomNo, String faculty, int batch, int regno, Boolean isSingleRoom)
    {
        super(username, password, name,email,role);
        this.hostelNo = hostelNo;
        this.roomNo = roomNo;
        this.faculty = faculty;
        this.batch = batch;
        this.regno = regno;
        this.isSingleRoom = isSingleRoom;
    }



}
