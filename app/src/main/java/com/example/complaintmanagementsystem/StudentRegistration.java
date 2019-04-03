package com.example.complaintmanagementsystem;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.complaintmanagementsystem.Models.User.Student;

public class StudentRegistration extends AppCompatActivity {
    private static String faculty = "Computer Engg";
    private static String hostel = "Hostel-1";
    private static String name;
    private static String email;
    private static String pass;
    private static boolean isSingle=true;

    private TextInputLayout textRegNo;
    private TextInputLayout textRoomNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        textRegNo=findViewById(R.id.regNo);
        textRoomNo=findViewById(R.id.roomNo);

        Spinner spinner1 = (Spinner) findViewById(R.id.hostelNo);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.hostelNoArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);


        Spinner spinner2 = (Spinner) findViewById(R.id.faculty);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.facultyArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);


        Spinner spinner3 = (Spinner) findViewById(R.id.roomType);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.roomType, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner3.setAdapter(adapter3);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                faculty = parent.getItemAtPosition(pos).toString();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String singleRoom = parent.getItemAtPosition(pos).toString();
                if(singleRoom != "Double Room"){
                    isSingle = false;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                hostel = parent.getItemAtPosition(pos).toString();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });
        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        email = intent.getExtras().getString("email");
        pass = intent.getExtras().getString("pass");
    }


    public void registerStudent(View v)
    {

        String value= textRegNo.getEditText().getText().toString();
        String room = textRoomNo.getEditText().getText().toString();

        if(value.isEmpty())
        {
            textRegNo.setError("Field cannot be empty");
            return;
        }
        else{
            textRegNo.setError(null);
        }
        if(room.isEmpty())
        {
            textRoomNo.setError("Field cannot be empty");
            return;
        }
        else{
            textRoomNo.setError(null);
        }
        int regNo=Integer.parseInt(value);
        int roomNo=Integer.parseInt(room);

        Student student = new Student(pass,name,email,1, hostel,roomNo,faculty,regNo,isSingle);
        Log.e("pass", pass);
        Log.e("name", name);
        Log.e("email", email);
        Log.e("role", String.valueOf(1));
        Log.e("roomNo", String.valueOf(roomNo));
        Log.e("faculty", faculty);
        Log.e("regNo", String.valueOf(regNo));
        Log.e("isSingle", String.valueOf(isSingle));
        return;
    }



}
