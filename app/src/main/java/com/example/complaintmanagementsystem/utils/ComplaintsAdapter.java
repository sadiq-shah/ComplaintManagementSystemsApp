package com.example.complaintmanagementsystem.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.complaintmanagementsystem.Models.Complaint.Complaint;
import com.example.complaintmanagementsystem.Models.User.User;
import com.example.complaintmanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class ComplaintsAdapter  extends BaseAdapter {
    private Context mContext;
    private List<Complaint> mComplaintList;


    public ComplaintsAdapter(Context mContext, List<Complaint> mComplaintList) {
        this.mContext = mContext;
        this.mComplaintList = mComplaintList;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<Complaint> getmComplaintList() {
        return mComplaintList;
    }

    public void setmComplaintList(List<Complaint> mComplaintList) {
        this.mComplaintList = mComplaintList;
    }

    @Override
    public int getCount() {
        return mComplaintList.size();
    }

    @Override
    public Object getItem(int position) {
        return mComplaintList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.custom_complaint_view, null);
        TextView complaintType = (TextView) v.findViewById(R.id.complaintType);
        TextView complaintDate = (TextView) v.findViewById(R.id.complaintDate);
        TextView complaintDetails = (TextView) v.findViewById(R.id.complaintDetails);

        complaintType.setText(mComplaintList.get(position).getComplaintType());
        complaintDate.setText(mComplaintList.get(position).getDate());
        complaintDetails.setText(mComplaintList.get(position).getDetails());

        v.setTag(mComplaintList.get(position).getPushId());
        return v;

    }
}
