package com.example.complaintmanagementsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.complaintmanagementsystem.Models.Complaint.Complaint;
import com.example.complaintmanagementsystem.utils.Helper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class WriteComplaint extends AppCompatActivity {
    private static String complaintType;
    private static Button submit;
    private static String complaintDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_complaint);

        Intent intent = getIntent();
        complaintType = intent.getExtras().getString("complaintType");




        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitComplaint();
            }
        });
    }

    private void submitComplaint(){
        TextInputLayout complaintDetailsText= findViewById(R.id.complaintDetails);
        complaintDetails = complaintDetailsText.getEditText().getText().toString();

        if(complaintDetails.isEmpty()){
            complaintDetailsText.setError("Field cannot be empty");
            return;
        }
        else if(complaintDetails.length() < 20){
            complaintDetailsText.setError("Minimum 20 characters");
            return;
        }
        else if(complaintDetails.length() > 1000) {
            complaintDetailsText.setError("Maximum 30 characters");
            return;
        }
        Complaint newComplaint = new Complaint(complaintType, complaintDetails);
        Log.e("s:", "SubmitComplaint");
        Log.e("Cab:", complaintDetails);
        Log.e("Type", complaintType);
        Log.e("status","Pending");
        Log.e("date", newComplaint.getDate());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference restaurantRef = FirebaseDatabase
                .getInstance()
                .getReference("User")
                .child(uid);

        DatabaseReference pushRef = restaurantRef.child("complaints").push();
//        String pushId = pushRef.getKey();
//        newComplaint.setPushId(pushId);
        pushRef.setValue(newComplaint);
        Toast.makeText(WriteComplaint.this, "Complaint Registered Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, Home.class));


    }
}
