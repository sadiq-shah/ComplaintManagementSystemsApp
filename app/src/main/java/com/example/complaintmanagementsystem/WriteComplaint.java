package com.example.complaintmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class WriteComplaint extends AppCompatActivity {
    private static String complaintType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_complaint);

        Intent intent = getIntent();
        complaintType = intent.getExtras().getString("complaintType");
        Log.d("complaint",complaintType);
    }
}
