package com.example.complaintmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.complaintmanagementsystem.Models.User.Professor;
import com.example.complaintmanagementsystem.Models.User.Staff;
import com.example.complaintmanagementsystem.Models.User.Student;
import com.example.complaintmanagementsystem.Models.User.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private TextView textNameNav;
    private TextView textEmailNav;
    private FirebaseDatabase database;
    private NavigationView navigationView;
    private View headerView;
    boolean doubleBackToExitPressedOnce = false;
    private User user;
    private FloatingActionButton fab;
    private Student typeStudent;
    private Professor typeProfessor;
    private Staff typeStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView=findViewById(R.id.nav_view);
        headerView=navigationView.getHeaderView(0);
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        textNameNav=headerView.findViewById(R.id.nameTextViewHome);
        textEmailNav=headerView.findViewById(R.id.emailTextViewHome);
        loadUserInformation();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),ComplaintCategory.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //default Fragment
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutHome,new DashboardFragment());
        ft.commit();

        navigationView.setCheckedItem(R.id.nav_dashboard);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);return;
        } else if(doubleBackToExitPressedOnce){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            super.onBackPressed();
        }
        this.doubleBackToExitPressedOnce=true;
        Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()==null)
        {
            startActivity(new Intent(this,MainActivity.class));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id== R.id.nav_dashboard)
        {
            fab.show();
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayoutHome,new DashboardFragment());
            ft.commit();
        }
        else if (id == R.id.nav_profileSettings)
        {
            fab.hide();
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            if(user.getRole()==1)
            {
                StudentProfileSettingsFragment studentProfileSettingsFragment=StudentProfileSettingsFragment.newInstance(user,typeStudent);
                ft.replace(R.id.frameLayoutHome,studentProfileSettingsFragment);
            }
            else if(user.getRole()==2)
            {
                ProfessorProfileSettingsFragment professorProfileSettingsFragment=ProfessorProfileSettingsFragment.newInstance(user,typeProfessor);
                ft.replace(R.id.frameLayoutHome,professorProfileSettingsFragment);
            }
            else if(user.getRole()==3)
            {
                StaffProfileSettingsFragment staffProfileSettingsFragment=StaffProfileSettingsFragment.newInstance(user,typeStaff);
                ft.replace(R.id.frameLayoutHome,staffProfileSettingsFragment);
            }
            ft.commit();
        } else if (id == R.id.nav_logout) {
            mAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(getBaseContext(),MainActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void loadUserInformation(){
        DatabaseReference userDb=FirebaseDatabase.getInstance().getReference("User");

        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("name").getValue().toString();
                String email=dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("email").getValue().toString();
                int role=Integer.parseInt(dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("role").getValue().toString());

                user=new User(name,email,role,"");
                textNameNav.setText(user.getName());
                textEmailNav.setText(user.getEmail());
                //for student
                if(user.getRole()==1)
                {
                    String faculty=dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("type").child("faculty").getValue().toString();
                    String hostelNo=dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("type").child("hostelNo").getValue().toString();
                    int regno=Integer.parseInt(dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("type").child("regno").getValue().toString());
                    int roomNo=Integer.parseInt(dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("type").child("roomNo").getValue().toString());
                    String roomType=dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("type").child("singleRoom").getValue().toString();
                    boolean singleRoom=true;
                    if(roomType!="true")
                        singleRoom=false;

                    typeStudent=new Student(hostelNo,roomNo,faculty,regno,singleRoom);
                }
                //for professor
                else if(user.getRole()==2)
                {
                    String designation=dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("type").child("designation").getValue().toString();
                    String faculty=dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("type").child("faculty").getValue().toString();
                    String officeNo=dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("type").child("officeNo").getValue().toString();
                    typeProfessor=new Professor(faculty,designation,officeNo);
                }
                //for staff
                else
                {
                    String department=dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("type").child("department").getValue().toString();
                    String designation=dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("type").child("designation").getValue().toString();
                    typeStaff=new Staff(department,designation);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
