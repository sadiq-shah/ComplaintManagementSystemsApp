package com.example.complaintmanagementsystem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.complaintmanagementsystem.Models.User.Staff;
import com.example.complaintmanagementsystem.Models.User.Student;
import com.example.complaintmanagementsystem.Models.User.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentProfileSettingsFragment extends Fragment {

    private EditText name;
    private EditText regNo;
    private Spinner faculty;
    private Spinner hostelNo;
    private EditText roomNo;
    private Spinner roomType;
    private User user;
    private Student student;
    private Button confirm;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    public StudentProfileSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_profile_settings, container, false);
        user=(User)getArguments().getSerializable("user");
        student=(Student)getArguments().getSerializable("student");
        name=view.findViewById(R.id.psStudentName);
        regNo=view.findViewById(R.id.psStudentRegno);
        roomNo=view.findViewById(R.id.psStudentRoomno);
        faculty = view.findViewById(R.id.psStudentFaculty);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getActivity(), R.array.facultyArray,R.layout.support_simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        faculty.setAdapter(adapter1);
        hostelNo=view.findViewById(R.id.psStudentHostelno);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this.getActivity(), R.array.hostelNoArray,R.layout.support_simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        hostelNo.setAdapter(adapter2);
        roomType=view.findViewById(R.id.psStudentRoomType);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this.getActivity(), R.array.roomType,R.layout.support_simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        roomType.setAdapter(adapter3);
        confirm=view.findViewById(R.id.psStudentConfirm);

        //setting values per user
        name.setText(user.getName());
        roomNo.setText(""+student.getRoomNo());
        regNo.setText(""+student.getRegno());
        if (student.getFaculty() != null) {
            int spinnerPosition = adapter1.getPosition(student.getFaculty());
            faculty.setSelection(spinnerPosition);
        }
        if (student.getHostelNo() != null) {
            int spinnerPosition = adapter2.getPosition(student.getHostelNo());
            hostelNo.setSelection(spinnerPosition);
        }
        if (student.getSingleRoom() != null) {
            int spinnerPosition = adapter2.getPosition(student.getSingleRoom().toString());
            roomType.setSelection(spinnerPosition);
        }

        //spinner functionality
        faculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                student.setFaculty(parent.getItemAtPosition(pos).toString());
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });
        hostelNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                student.setHostelNo(parent.getItemAtPosition(pos).toString());
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });
        roomType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String check=parent.getItemAtPosition(pos).toString();
                if(check=="true")
                    student.setSingleRoom(true);
                else
                    student.setSingleRoom(false);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });
        //button functionality
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference=firebaseDatabase.getInstance().getReference("User")
                        .child(firebaseAuth.getInstance().getCurrentUser().getUid());
                user.setName(name.getText().toString());
                databaseReference.setValue(user);
                databaseReference=databaseReference.child("type");
                student.setRegno(Integer.parseInt(regNo.getText().toString()));
                student.setRoomNo(Integer.parseInt(roomNo.getText().toString()));
                databaseReference.setValue(student);
                Toast.makeText(getActivity().getBaseContext(), "Changes updated successfully", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    public static StudentProfileSettingsFragment newInstance(User u, Student s) {
        StudentProfileSettingsFragment fragment = new StudentProfileSettingsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user",u);
        bundle.putSerializable("student",s);
        fragment.setArguments(bundle);

        return fragment;
    }
}
