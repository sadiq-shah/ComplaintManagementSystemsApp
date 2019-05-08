package com.example.complaintmanagementsystem;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.complaintmanagementsystem.Models.Complaint.Complaint;
import com.example.complaintmanagementsystem.utils.ComplaintsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    private static TextView totalComplaints;
    private static TextView openComplaints;
    private static TextView closedComplaints;
    private static TextView positiveFeedbacks;
    private static TextView negativeFeedbacks;

    private static LinearLayout layoutOpenComplaints;
    private static LinearLayout layoutClosedComplaints;
    private static LinearLayout layoutTotalComplaints;
    private static LinearLayout layoutPositiveFeedbacks;
    private static LinearLayout layoutNegativeFeedbacks;

    FirebaseDatabase database;
    DatabaseReference myRef;
    List<Complaint> complaintsList = new ArrayList<>();;
    private FirebaseAuth mAuth;
    int numOfComplaints = 0;
    int positiveComplaints = 0;
    int negativeComplaints= 0;



    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        database = FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        myRef = database.getReference("User");

        totalComplaints = (TextView) v.findViewById(R.id.totalComplaints);
        Log.e("Fragment", "Loaded");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 :dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("complaints").getChildren()){
                    Complaint value = dataSnapshot1.getValue(Complaint.class);
                    if(value.getPositiveFeedback() > 0){
                        positiveComplaints += 1;
                    }
                    else{
                        negativeComplaints += 1;
                    }
                    numOfComplaints+= 1;
                }
                Log.e("Number Complaints", String.valueOf(numOfComplaints));

                totalComplaints.setText(String.valueOf(numOfComplaints));
                positiveFeedbacks.setText(String.valueOf(positiveComplaints));
                negativeFeedbacks.setText(String.valueOf(negativeComplaints));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        openComplaints = (TextView) v.findViewById(R.id.openComplaints);
        openComplaints.setText("10");

        closedComplaints = (TextView) v.findViewById(R.id.closeComplaints);
        closedComplaints.setText("20");

        positiveFeedbacks = (TextView) v.findViewById(R.id.positiveFeebacks);


        negativeFeedbacks = (TextView) v.findViewById(R.id.negativeFeedbacks);


        layoutOpenComplaints = v.findViewById(R.id.layoutOpenComplaints);
        layoutOpenComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOpenComplaintsActivity();
            }
        });

        layoutClosedComplaints = v.findViewById(R.id.layoutClosedComplaints);
        layoutClosedComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClosedComplaintsActivity();
            }
        });

        layoutNegativeFeedbacks = v.findViewById(R.id.layoutNegativeFeedbacks);
        layoutNegativeFeedbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNegativeFeedbacksActivity();
            }
        });

        layoutPositiveFeedbacks = v.findViewById(R.id.layoutPositiveFeebacks);
        layoutPositiveFeedbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPositiveFeedbacksActivity();
            }
        });

        layoutTotalComplaints = v.findViewById(R.id.layoutTotalComplaints);
        layoutTotalComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLayoutTotalComplaints();
            }
        });


        Log.e("Finally", String.valueOf(numOfComplaints));
        return v;
    }

    public void startLayoutTotalComplaints(){
        Log.d("Activity", "Total Compalints");
        Intent intent = new Intent(getActivity().getBaseContext(), TotalComplaint.class);
        startActivity(intent);
    }

    public void startOpenComplaintsActivity(){
        Log.d("Activity", "OpenComplaintClicked");
        Intent intent = new Intent(getActivity().getBaseContext(), OpenComplaint.class);
        startActivity(intent);
    }

    public void startClosedComplaintsActivity(){
        Log.d("Activity", "Closed Complaint Clicked");
        Intent intent = new Intent(getActivity().getBaseContext(), ClosedComplaint.class);
        startActivity(intent);
    }

    public void startPositiveFeedbacksActivity(){
        Log.d("Activity", "Positive Feedback Activity");
        Intent intent = new Intent(getActivity().getBaseContext(), PositiveFeedback.class);
        startActivity(intent);
    }

    public void startNegativeFeedbacksActivity(){
        Log.d("Activity", "Negative Feedback Activity");
        Intent intent = new Intent(getActivity().getBaseContext(), NegativeFeedback.class);
        startActivity(intent);
    }

}
