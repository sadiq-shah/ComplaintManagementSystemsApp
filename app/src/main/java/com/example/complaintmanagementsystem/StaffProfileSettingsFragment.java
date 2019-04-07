package com.example.complaintmanagementsystem;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
import com.example.complaintmanagementsystem.Models.User.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class StaffProfileSettingsFragment extends Fragment {

    private EditText name;
    private EditText designation;
    private Spinner department;
    private User user;
    private Staff staff;
    private Button confirm;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    public StaffProfileSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_staff_profile_settings, container, false);
        //getting the object
        user=(User)getArguments().getSerializable("user");
        staff=(Staff)getArguments().getSerializable("staff");
        //initializing the values
        confirm=view.findViewById(R.id.psStaffConfirm);
        name= view.findViewById(R.id.psStaffName);
        designation= view.findViewById(R.id.psStaffDesignation);

        department = view.findViewById(R.id.psStaffDepartment);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.department,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        department.setAdapter(adapter);

        //setting values per user
        name.setText(user.getName());
        designation.setText(staff.getDesignation());
        if (staff.getDepartment() != null) {
            int spinnerPosition = adapter.getPosition(staff.getDepartment());
            department.setSelection(spinnerPosition);
        }
        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                staff.setDepartment(parent.getItemAtPosition(pos).toString());
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference=firebaseDatabase.getInstance().getReference("User").child(firebaseAuth.getInstance().getCurrentUser().getUid());
                user.setName(name.getText().toString());
                databaseReference.setValue(user);
                databaseReference=databaseReference.child("type");
                staff.setDesignation(designation.getText().toString());
                databaseReference.setValue(staff);
                Toast.makeText(getActivity().getBaseContext(), "Changes updated successfully", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    public static StaffProfileSettingsFragment newInstance(User u,Staff s) {
        StaffProfileSettingsFragment fragment = new StaffProfileSettingsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user",u);
        bundle.putSerializable("staff",s);
        fragment.setArguments(bundle);

        return fragment;
    }

}
