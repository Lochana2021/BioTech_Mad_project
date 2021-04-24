package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class QuizList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);

        Intent getIntentMainActivity = getIntent();
    }

    public void gotoViewQuizActivity (View view) {

        Intent intent2 = new Intent(this, ViewQuiz.class);
        startActivity(intent2);

        Toast.makeText(getApplicationContext(), "Navigating....", Toast.LENGTH_SHORT).show();
    }

    public void gotoCreateQuizActivity (View view) {

        Intent intent = new Intent(this, CreateQuiz.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Navigating....", Toast.LENGTH_SHORT).show();
    }
}