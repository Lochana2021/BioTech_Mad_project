package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class ViewQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quiz);

        Intent getIntentQuizListActivity = getIntent();
    }

    public void gotoPassListActivity (View view) {

        Intent intent2 = new Intent(this, PassList.class);
        startActivity(intent2);

        Toast.makeText(getApplicationContext(), "Navigating....", Toast.LENGTH_SHORT).show();
    }
}