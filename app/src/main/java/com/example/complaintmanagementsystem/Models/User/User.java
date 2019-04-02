package com.example.complaintmanagementsystem.Models.User;

public class User {
    private String name;
    private String email;
    private String password;
    private int role;

    public User(String name, String email,String password, int role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;

    }

    public boolean login(String username, String password)
    {
        //1-Check this user exists
        //2-If not, Show an error message
        //3-Login into his dashboard
        return true;
    }


}
