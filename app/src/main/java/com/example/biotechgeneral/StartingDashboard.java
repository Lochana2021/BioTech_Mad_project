package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartingDashboard extends AppCompatActivity {

    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_dashboard);

        button1 = findViewById(R.id.btn_forTeacher);
        button2 = findViewById(R.id.btn_forStudent);

    }

    public void onClickTeacher(View view) {

        Intent i = new Intent(this,lecturer_login.class);
        startActivity(i);
        Toast.makeText(this, "Login As a Teacher", Toast.LENGTH_SHORT).show();
    }


    public void onCliickStudent(View view) {
        Intent i = new Intent(this,login_bio.class);
        startActivity(i);
        Toast.makeText(this, "Login As a Student", Toast.LENGTH_SHORT).show();
    }
}