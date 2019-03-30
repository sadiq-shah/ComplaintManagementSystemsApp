package com.example.complaintmanagementsystem;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout textUserID;
    private TextInputLayout textPassword;
    private Button logIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textUserID=findViewById(R.id.text_userID);
        textPassword=findViewById(R.id.text_password);
        logIn=findViewById(R.id.LLogin);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateUserID() | !validatePassword()){
                    return;
                }
                String input="UserID: "+textUserID.getEditText().getText().toString();
                input+="\n";
                input +="Password: "+textPassword.getEditText().getText().toString();
                Toast.makeText(LoginActivity.this, input, Toast.LENGTH_SHORT).show();
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
}
