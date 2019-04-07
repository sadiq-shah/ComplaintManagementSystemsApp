package com.example.complaintmanagementsystem;


import android.os.Bundle;
import android.provider.ContactsContract;
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

import com.example.complaintmanagementsystem.Models.User.Professor;
import com.example.complaintmanagementsystem.Models.User.Staff;
import com.example.complaintmanagementsystem.Models.User.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfessorProfileSettingsFragment extends Fragment {

    private EditText name;
    private EditText officeNo;
    private Spinner faculty;
    private Spinner designation;
    private User user;
    private Professor professor;
    private Button confirm;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    public ProfessorProfileSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_professor_profile_settings, container, false);
        
        //initializations
        user=(User)getArguments().getSerializable("user");
        professor=(Professor)getArguments().getSerializable("professor");
        name=view.findViewById(R.id.psProfessorName);
        officeNo=view.findViewById(R.id.psProfessorOfficeno);
        faculty = view.findViewById(R.id.psProfessorFaculty);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getActivity(), R.array.facultyArray,R.layout.support_simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        faculty.setAdapter(adapter1);
        designation=view.findViewById(R.id.psProfessorDesignation);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this.getActivity(), R.array.designationProfessor,R.layout.support_simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        designation.setAdapter(adapter2);
        confirm=view.findViewById(R.id.psProfessorConfirm);
        
        //setting values per user
        name.setText(user.getName());
        officeNo.setText(professor.getOfficeNo());
        if (professor.getFaculty() != null) {
            int spinnerPosition = adapter1.getPosition(professor.getFaculty());
            faculty.setSelection(spinnerPosition);
        }
        if (professor.getDesignation() != null) {
            int spinnerPosition = adapter2.getPosition(professor.getDesignation());
            designation.setSelection(spinnerPosition);
        }
        
        //spinner functionality
        faculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                professor.setFaculty(parent.getItemAtPosition(pos).toString());
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });
        designation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                professor.setDesignation(parent.getItemAtPosition(pos).toString());
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
                professor.setOfficeNo(officeNo.getText().toString());
                databaseReference.setValue(professor);
                Toast.makeText(getActivity().getBaseContext(), "Changes updated successfully", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    public static ProfessorProfileSettingsFragment newInstance(User u, Professor s) {
        ProfessorProfileSettingsFragment fragment = new ProfessorProfileSettingsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user",u);
        bundle.putSerializable("professor",s);
        fragment.setArguments(bundle);

        return fragment;
    }

}
