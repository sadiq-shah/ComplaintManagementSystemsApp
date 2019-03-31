package com.example.complaintmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout textUserID;
    private TextInputLayout textPassword;
    private Button logIn;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textUserID=findViewById(R.id.text_userID);
        textPassword=findViewById(R.id.text_password);
        logIn=findViewById(R.id.LLogin);
        progressDialog= new ProgressDialog(this);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateUserID() | !validatePassword()){
                    return;
                }
                registerUser();
            }
        });
    }
    private boolean validateUserID(){
        String uID=textUserID.getEditText().getText().toString().trim();
        if(uID.isEmpty())
        {
            textUserID.setError("Field cannot be empty");
            return false;
        }
        else{
            textUserID.setError(null);
            return true;
        }
    }
    private boolean validatePassword(){
        String uID=textPassword.getEditText().getText().toString().trim();
        if(uID.isEmpty())
        {
            textPassword.setError("Field cannot be empty");
            return false;
        }
        else{
            textPassword.setError(null);
            return true;
        }
    }
    private void registerUser(){
        String userID=textUserID.getEditText().getText().toString();
        String password=textPassword.getEditText().getText().toString();

        progressDialog.setMessage("Registering User...\n");
        progressDialog.show();
    }
}
