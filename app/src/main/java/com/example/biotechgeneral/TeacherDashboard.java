package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class TeacherDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
    }

    public void logoutLec(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),StartingDashboard.class));
        finish();
    }

    public void TeacherDashtoQuizList (View view) {
        Intent intentQuizList = new Intent(this, QuizList.class);
        startActivity(intentQuizList);
        Toast.makeText(getApplicationContext(), "Navigating to QuizList", Toast.LENGTH_SHORT).show();
    }


    public void TeacherDashtoForum (View view) {
        Intent intentQuizList = new Intent(this, Forum_Dashboard.class);
        startActivity(intentQuizList);
        Toast.makeText(getApplicationContext(), "Navigating to Forum", Toast.LENGTH_SHORT).show();
    }

    public void TeacherDashtoAssignment (View view) {
        Intent intentQuizList = new Intent(this, ass_teacher.class);
        startActivity(intentQuizList);
        Toast.makeText(getApplicationContext(), "Navigating to Assignment", Toast.LENGTH_SHORT).show();
    }

    public void TeacherDashtoTeacherProfle (View view) {
        Intent intentQuizList = new Intent(this, LecturerProfile.class);
        startActivity(intentQuizList);
        Toast.makeText(getApplicationContext(), "Navigating to Lecturer Profile", Toast.LENGTH_SHORT).show();
    }


}