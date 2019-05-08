package com.example.complaintmanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

public class TotalComplaint extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<Complaint> complaintsList = new ArrayList<>();;
    private FirebaseAuth mAuth;



    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;
    private ListView mListView;
    private ComplaintsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_complaint);

        database = FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        myRef = database.getReference("User");


        mContext = getApplicationContext();
        mActivity = TotalComplaint.this;
        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mListView = (ListView) findViewById(R.id.list_view);



        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 :dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("complaints").getChildren()){

                    Complaint value = dataSnapshot1.getValue(Complaint.class);
                    Log.d("complaints",value.getComplaintType());
                    complaintsList.add(value);


                }
                adapter = new ComplaintsAdapter(getApplicationContext(),complaintsList);
                mListView.setAdapter(adapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Do Something
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
