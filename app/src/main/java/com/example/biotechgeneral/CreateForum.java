package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateForum extends AppCompatActivity {
    Button button01, button02;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_forum);

        // cancel button

        button02 = findViewById(R.id.btn_FcreateCancel);
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ForumList.class);
                startActivity(i);
            }
        });

        // create button
        button01 = findViewById(R.id.btn_forumcreate);
        button01.setOnClickListener(new View.OnClickListener() {/*Setting The Click Listener*/
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForumList.class);/*Defining The Intent*/
                intent.putExtra("ForumName","ForumName");/*Putting The Data To Pass To The Next Activity*/
                startActivity(intent);/*Starting The Activity*/
            }
        });








    }

}