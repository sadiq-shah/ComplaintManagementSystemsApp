package com.example.complaintmanagementsystem.utils;


import com.example.complaintmanagementsystem.Models.User.Professor;
import com.example.complaintmanagementsystem.Models.User.Staff;
import com.example.complaintmanagementsystem.Models.User.Student;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Helper {

    public static String getTodaysDate(){
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        return date;
    }
}
