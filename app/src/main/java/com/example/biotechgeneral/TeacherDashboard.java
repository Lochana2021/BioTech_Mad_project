package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TeacherDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
    }

    public void TeacherDashtoQuizList (View view) {
        Intent intentQuizList = new Intent(this, QuizList.class);
        startActivity(intentQuizList);
        Toast.makeText(getApplicationContext(), "Navigating to QuizList", Toast.LENGTH_SHORT).show();
    }

    /*
    public void TeacherDashtoForum (View view) {
        Intent intentQuizList = new Intent(this, PassList.class);
        startActivity(intentQuizList);
        Toast.makeText(getApplicationContext(), "Navigating to QuizList", Toast.LENGTH_SHORT).show();
    }

    public void TeacherDashtoAssignment (View view) {
        Intent intentQuizList = new Intent(this, PassList.class);
        startActivity(intentQuizList);
        Toast.makeText(getApplicationContext(), "Navigating to QuizList", Toast.LENGTH_SHORT).show();
    }

    public void TeacherDashtoTeacherProfle (View view) {
        Intent intentQuizList = new Intent(this, PassList.class);
        startActivity(intentQuizList);
        Toast.makeText(getApplicationContext(), "Navigating to QuizList", Toast.LENGTH_SHORT).show();
    }

     */
}