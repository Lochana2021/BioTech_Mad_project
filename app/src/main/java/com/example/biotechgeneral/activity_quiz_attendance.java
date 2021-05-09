package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_quiz_attendance extends AppCompatActivity {
   EditText quizname,regnum,quizid,quizdate;
   Button attsubmit;
   DatabaseReference reff;
   QuizAtt quizAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_attendance);

        quizname = (EditText)findViewById(R.id.AttQuizName);
        regnum = (EditText)findViewById(R.id.AttQuizReg);
        quizid = (EditText)findViewById(R.id.AttQuizID);
        quizdate = (EditText)findViewById(R.id.AttQuizDate);
        attsubmit = (Button)findViewById(R.id.AttSubB);

        quizAttendance = new QuizAtt();

        //make database reference
        reff = FirebaseDatabase.getInstance().getReference().child("QuizInfo");

        String result = getIntent().getStringExtra("total");

        attsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //assigning EditText data to String variables
                String name = quizname.getText().toString().trim();
                String regNo = regnum.getText().toString().trim();
                String qID = quizid.getText().toString().trim();
                String date = quizdate.getText().toString().trim();

                //set data
                quizAttendance.setQName(name);
                quizAttendance.setQRegNum(regNo);
                quizAttendance.setQID(qID);
                quizAttendance.setQdate(date);
                quizAttendance.setResults(result);

                //push data to the firebase with regnum as ID
                reff.child(regnum.getText().toString().trim()).setValue(quizAttendance);
                Toast.makeText(activity_quiz_attendance.this,"Data insert successfully.",Toast.LENGTH_LONG).show();
            }
        });
    }
}