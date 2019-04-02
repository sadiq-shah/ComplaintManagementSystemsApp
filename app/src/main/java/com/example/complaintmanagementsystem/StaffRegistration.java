package com.example.complaintmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.complaintmanagementsystem.Models.User.Staff;

public class StaffRegistration extends AppCompatActivity {
    private static String name;
    private static String email;
    private static String pass;
    private static String department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_registration);


        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        email = intent.getExtras().getString("email");
        pass = intent.getExtras().getString("pass");


        Spinner spinner1 = (Spinner) findViewById(R.id.department);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.department, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                department = parent.getItemAtPosition(pos).toString();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });


    }
    public void staffRegistration(View v){

        EditText designationEditText = (EditText) findViewById(R.id.designation);
        final String designation = designationEditText.getText().toString();

        Staff staff = new Staff(pass,name, email, 3, department,  designation);
        Log.e("Pass", pass);
        Log.e("name", name);
        Log.e("email", email);
        Log.e("role", String.valueOf(3));
        Log.e("department", department);
        Log.e("designation", designation);
        return;
    }
}
