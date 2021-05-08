package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ResultQuizStu extends AppCompatActivity {

    EditText QuizNum,stuID;
    TextView OutName,OutRes;
    Button can,submit;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_quiz_stu);

        QuizNum = (EditText)findViewById(R.id.ResQuizNo);
        String qNumber = QuizNum.getText().toString().trim();

        stuID = (EditText)findViewById(R.id.ResStuID);
        String studentID = stuID.getText().toString().trim();

        OutName = (TextView)findViewById(R.id.RStuName);
        OutRes = (TextView)findViewById(R.id.ResResults);
        submit = (Button)findViewById(R.id.ResSubmit);
        can = (Button)findViewById(R.id.ResCancel);

        reff = FirebaseDatabase.getInstance().getReference().child("QuizInfo");

        String qno = QuizNum.getText().toString().trim();
        String sid = stuID.getText().toString().trim();

        Query checkUser = reff.orderByChild("qno").equalTo(qno);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String ResName = snapshot.child("qid").getValue().toString();
                        String regNum = snapshot.child("qregNum").getValue().toString();
                        String Res = snapshot.child("results").getValue().toString();
                        String qNum = snapshot.child("qname").getValue().toString();

                        if(regNum.equals("112")){
                            OutName.setText(ResName);
                            OutRes.setText(Res);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });



/*
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    QuizNum.setError(null);

                    String stuID = snapshot.child(sid).child("qregNum").getValue(String.class);

                    if (stuID.equals(sid)){

                        QuizNum.setError(null);

                        String stuName = snapshot.child(sid).child("qid").getValue(String.class);
                        String stuRes = snapshot.child(sid).child("result").getValue(String.class);

                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                OutName.setText(stuName);
                                OutRes.setText(stuRes);
                            }
                        });

                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

            }
        });*/




    }
}