package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class navigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),login_bio.class));
        finish();
    }

    public void assignmentNav(View view){
        startActivity(new Intent(getApplicationContext(),std_ass_input.class));
    }

    public void quizNav(View view){
        startActivity(new Intent(getApplicationContext(),QuizStudent.class));
    }

    public void forumNav(View view){
        startActivity(new Intent(getApplicationContext(),Forum_Dashboard.class));
    }

    public void profileNav(View view){
        startActivity(new Intent(getApplicationContext(),std_profile.class));
    }
}