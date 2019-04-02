package com.example.complaintmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.complaintmanagementsystem.Models.User.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistration extends AppCompatActivity {
    private static int role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        Intent intent = getIntent();

        role = intent.getExtras().getInt("Role");
    }

    public void continueRegistration(View v)
    {
        EditText nameEditText = (EditText) findViewById(R.id.name);
        final String name = nameEditText.getText().toString();


        if(name == null){
            nameEditText.setError("Required Field.");
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

        Log.e("Validation", "All validation checked" + role);
        if(role == 1) {
            Log.e("User", "User Registration");
            Intent intent = new Intent(this, StudentRegistration.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("pass", pass);
            startActivity(intent);
        }

        if(role == 2) {
            Log.e("Profes", "Prof Registration");
            Intent intent = new Intent(this, ProfessorRegistration.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("pass", pass);
            startActivity(intent);
        }


        if(role == 3) {
            Intent intent = new Intent(this, StaffRegistration.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("pass", pass);
            startActivity(intent);
        }
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
