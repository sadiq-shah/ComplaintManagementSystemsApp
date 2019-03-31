package com.example.complaintmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
    }

    public void continueRegistration(View v)
    {
        EditText nameEditText = (EditText) findViewById(R.id.name);
        final String name = nameEditText.getText().toString();

        EditText usernameEditText = (EditText) findViewById(R.id.username);
        final String username = (EditText) findViewById(R.id.username);

        if(!name){
            nameEditText.setError("Required Field.");
            return;
        }
        if(!username){
            ((EditText) username).setError("Required Field");
            return;
        }
        EditText emailEditText = (EditText) findViewById(R.id.email);
        final String email = emailEditText.getText().toString();

        EditText passEditText = (EditText) findViewById(R.id.text_password);
        final String pass = passEditText.getText().toString();
        if (!isValidPassword(pass)) {
            passEditText.setError("Password Length should be greater than 6.");
            Log.d("Password", "Password Invalid");
            return;
        }

        if (!isValidEmail(email)) {
            emailEditText.setError("Invalid Email");
            Log.d("Email", "Email Invalid");
            return;
        }

        Intent intent = new Intent(this, StudentRegistration.class);
        startActivity(intent);
    }




    //Helpers Function
    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }


}
