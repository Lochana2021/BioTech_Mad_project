package com.example.biotechgeneral;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Forum_PostAnswer extends AppCompatActivity {

    // declare variables
    EditText ForumName, QuetionNumber, answer;
    Button button;
    DatabaseReference F_dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum__post_answer);

    // create connection variables and xml widgets
        ForumName = findViewById(R.id.Post_FName);
        QuetionNumber = findViewById(R.id.Post_quetionNo);

        answer = findViewById(R.id.Post_PostAnswer);
        button = findViewById(R.id.btn_PostAnswer);


    // create connection with firebase database
        F_dbRef = FirebaseDatabase.getInstance().getReference().child("ForumStudent");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Calling post answer method
                postAnswer();

            }
        });
    }

    // Declare postAnswer Method body
    private void postAnswer(){
        String FName = ForumName.getText().toString();
        String QuetionNo = QuetionNumber.getText().toString();

        String Answer = answer.getText().toString();
        ForumStudent forumStudent = new ForumStudent(FName,QuetionNo, Answer);

        F_dbRef.push().setValue(forumStudent);
        Toast.makeText(Forum_PostAnswer.this,"Answer Added",Toast.LENGTH_SHORT).show();

    }
}