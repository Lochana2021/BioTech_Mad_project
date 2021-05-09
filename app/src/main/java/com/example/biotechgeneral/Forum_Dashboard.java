package com.example.biotechgeneral;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Forum_Dashboard extends AppCompatActivity {

    // Declare button variables

    Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum__dashboard);

        // create connection with button and xml widgets

        button1 = findViewById(R.id.FDash_FList);
        button2 = findViewById(R.id.FDash_PostAnswer);
        button3 = findViewById(R.id.FDash_AddQuetion);
        button4 = findViewById(R.id.FDash_DisplayForum);

        // button intent Forum List

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ForumList.class);
                startActivity(i);
                Toast.makeText(Forum_Dashboard.this, "Opening Forum List", Toast.LENGTH_SHORT).show();
            }
        });


        //button intent Post Answer
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Forum_PostAnswer.class);
                startActivity(i);
                Toast.makeText(Forum_Dashboard.this, "Opening Post Answer", Toast.LENGTH_SHORT).show();
            }
        });


        //button intent Add Question
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddQuetion.class);
                startActivity(i);
                Toast.makeText(Forum_Dashboard.this, "Opening Add Quetion", Toast.LENGTH_SHORT).show();
            }
        });

        // button intent Forum Display
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ForumDisplay.class);
                startActivity(i);
                Toast.makeText(Forum_Dashboard.this, "Opening", Toast.LENGTH_SHORT).show();
            }
        });



    }
}