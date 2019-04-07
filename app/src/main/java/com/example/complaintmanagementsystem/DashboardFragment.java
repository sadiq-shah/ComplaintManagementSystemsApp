package com.example.complaintmanagementsystem;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


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

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        totalComplaints = (TextView) v.findViewById(R.id.totalComplaints);
        totalComplaints.setText("100");

        openComplaints = (TextView) v.findViewById(R.id.openComplaints);
        openComplaints.setText("10");

        closedComplaints = (TextView) v.findViewById(R.id.closeComplaints);
        closedComplaints.setText("20");

        positiveFeedbacks = (TextView) v.findViewById(R.id.positiveFeebacks);
        positiveFeedbacks.setText("30");

        negativeFeedbacks = (TextView) v.findViewById(R.id.negativeFeedbacks);
        negativeFeedbacks.setText("50");

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

        return v;
    }

    public void startOpenComplaintsActivity(){
        Log.d("Activity", "OpenComplaintClicked");
    }

    public void startClosedComplaintsActivity(){
        Log.d("Activity", "Closed Complaint Clicked");
    }
    public void startNegativeFeedbacksActivity(){
        Log.d("Activity", "Negative Feedback Activity");
    }

    public void startLayoutTotalComplaints(){
        Log.d("Activity", "Total Compalints");
    }
    public void startPositiveFeedbacksActivity(){
        Log.d("Activity", "Positive Feedback Activity");
    }

}
