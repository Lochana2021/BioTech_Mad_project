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

        QuizNum = (EditText) findViewById(R.id.ResQuizNo);
        stuID = (EditText) findViewById(R.id.ResStuID);

        OutName = (TextView) findViewById(R.id.RStuName);
        OutRes = (TextView) findViewById(R.id.ResResults);
        submit = (Button) findViewById(R.id.ResSubmit);
        can = (Button) findViewById(R.id.ResCancel);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reff = FirebaseDatabase.getInstance().getReference().child("QuizInfo").child(stuID.getText().toString().trim());
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //Assigning database values to variables
                        String ResName = snapshot.child("qid").getValue().toString();
                        String regNum = snapshot.child("qregNum").getValue().toString();
                        String Res = snapshot.child("results").getValue().toString();


                        //check database variable and user entered variable
                        if (regNum.equals(stuID.getText().toString().trim())) {
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


    }
}