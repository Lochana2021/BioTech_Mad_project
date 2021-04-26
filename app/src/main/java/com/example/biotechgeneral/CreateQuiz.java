package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Calendar;

public class CreateQuiz extends AppCompatActivity {

    QuizClass quiz;
    DatePickerDialog picker;
    DatabaseReference dbRef;
    EditText  quizNo, lecturerID, quizPassMark, quizDeadline, quizQ1, quizQ1CorrectAnswer,
            quizQ1A1, quizQ1A2, quizQ1A3, quizQ1A4, quizQ2, quizQ2CorrectAnswer,
            quizQ2A1, quizQ2A2, quizQ2A3, quizQ2A4, quizQ3, quizQ3CorrectAnswer,
            quizQ3A1, quizQ3A2, quizQ3A3, quizQ3A4;
    Button idBtnCancel, idBtnSave;
    long quizID = 0;

    // Clear out all user inputs
    public void clearControls() {
        quizNo.setText("");
        lecturerID.setText("");
        quizPassMark.setText("");
        quizDeadline.setText("");
        quizQ1.setText("");
        quizQ1CorrectAnswer.setText("");
        quizQ1A1.setText("");
        quizQ1A2.setText("");
        quizQ1A3.setText("");
        quizQ1A4.setText("");
        quizQ2.setText("");
        quizQ2CorrectAnswer.setText("");
        quizQ2A1.setText("");
        quizQ2A2.setText("");
        quizQ2A3.setText("");
        quizQ2A4.setText("");
        quizQ3.setText("");
        quizQ3CorrectAnswer.setText("");
        quizQ3A1.setText("");
        quizQ3A2.setText("");
        quizQ3A3.setText("");
        quizQ3A4.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        Intent getIntentQList = getIntent();

        quiz = new QuizClass();
        dbRef = FirebaseDatabase.getInstance().getReference().child("QuizClass");
        idBtnCancel = findViewById(R.id.idBtnCancel);
        idBtnSave = findViewById(R.id.idBtnSave);

        quizNo = findViewById(R.id.idInputQuizNumber);
        quizPassMark = findViewById(R.id.idInputPassMark);
        quizDeadline = findViewById(R.id.idInputDeadline);

        quizQ1 = findViewById(R.id.idInputQ1);
        quizQ1CorrectAnswer = findViewById(R.id.inputCorrectQ1);
        quizQ1A1 = findViewById(R.id.inputQ1Ans1);
        quizQ1A2 = findViewById(R.id.inputQ1Ans2);
        quizQ1A3 = findViewById(R.id.inputQ1Ans3);
        quizQ1A4 = findViewById(R.id.inputQ1Ans4);
        quizQ2 = findViewById(R.id.idInputQ2);
        quizQ2CorrectAnswer = findViewById(R.id.inputCorrectQ2);
        quizQ2A1 = findViewById(R.id.inputQ2Ans1);
        quizQ2A2 = findViewById(R.id.inputQ2Ans2);
        quizQ2A3 = findViewById(R.id.inputQ2Ans3);
        quizQ2A4 = findViewById(R.id.inputQ2Ans4);
        quizQ3 = findViewById(R.id.idInputQ3);
        quizQ3CorrectAnswer = findViewById(R.id.inputCorrectQ3);
        quizQ3A1 = findViewById(R.id.inputQ3Ans1);
        quizQ3A2 = findViewById(R.id.inputQ3Ans2);
        quizQ3A3 = findViewById(R.id.inputQ3Ans3);
        quizQ3A4 = findViewById(R.id.inputQ3Ans4);

        // auto incement if any changes made to the data in database
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    quizID = (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // calender
        quizDeadline.setInputType(InputType.TYPE_NULL);
        quizDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(CreateQuiz.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                quizDeadline.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        // CANCEL button onCLick()
        idBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String storeInputNum = quizNo.getText().toString();
                if (storeInputNum.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Already Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    clearControls();
                }

            }
        });

        // SAVE button onCLick()
        idBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    try {
                        if (TextUtils.isEmpty(quizNo.getText().toString()))
                            Toast.makeText(getApplicationContext(), "Please enter Quiz Number.", Toast.LENGTH_LONG).show();
                        else if (TextUtils.isEmpty(quizPassMark.getText().toString()))
                            Toast.makeText(getApplicationContext(), "Please enter Quiz Pass Mark.", Toast.LENGTH_LONG).show();
                        else if (TextUtils.isEmpty(quizDeadline.getText().toString()))
                            Toast.makeText(getApplicationContext(), "Please enter Quiz Deadline.", Toast.LENGTH_LONG).show();
                        else if (TextUtils.isEmpty(quizQ1.getText().toString()))
                            Toast.makeText(getApplicationContext(), "Please enter Question 1.", Toast.LENGTH_LONG).show();
                        else if (TextUtils.isEmpty(quizQ2.getText().toString()))
                            Toast.makeText(getApplicationContext(), "Please enter Question 2.", Toast.LENGTH_LONG).show();
                        else if (TextUtils.isEmpty(quizQ3.getText().toString()))
                            Toast.makeText(getApplicationContext(), "Please enter Question 3.", Toast.LENGTH_LONG).show();
                        else if (TextUtils.isEmpty(quizQ1CorrectAnswer.getText().toString()))
                            Toast.makeText(getApplicationContext(), "Please enter Question 1 correct answer.", Toast.LENGTH_LONG).show();
                        else {
                            // Take user inputs and Assign them into this Instance(quiz) of the QuizClass
                            quiz.setQuizNo(quizNo.getText().toString().trim());
                            // quiz.setLecturerID(lecturerID.getText().toString().trim());
                            quiz.setQuizPassMark(quizPassMark.getText().toString().trim());
                            quiz.setQuizDeadline(quizDeadline.getText().toString().trim());

                            quiz.setQuizQ1(quizQ1.getText().toString().trim());
                            quiz.setQuizQ1CorrectAnswer(quizQ1CorrectAnswer.getText().toString().trim());
                            quiz.setQuizQ1A1(quizQ1A1.getText().toString().trim());
                            quiz.setQuizQ1A2(quizQ1A2.getText().toString().trim());
                            quiz.setQuizQ1A3(quizQ1A3.getText().toString().trim());
                            quiz.setQuizQ1A4(quizQ1A4.getText().toString().trim());

                            quiz.setQuizQ2(quizQ2.getText().toString().trim());
                            quiz.setQuizQ2CorrectAnswer(quizQ2CorrectAnswer.getText().toString().trim());
                            quiz.setQuizQ2A1(quizQ2A1.getText().toString().trim());
                            quiz.setQuizQ2A2(quizQ2A2.getText().toString().trim());
                            quiz.setQuizQ2A3(quizQ2A3.getText().toString().trim());
                            quiz.setQuizQ2A4(quizQ2A4.getText().toString().trim());

                            quiz.setQuizQ3(quizQ3.getText().toString().trim());
                            quiz.setQuizQ3CorrectAnswer(quizQ3CorrectAnswer.getText().toString().trim());
                            quiz.setQuizQ3A1(quizQ3A1.getText().toString().trim());
                            quiz.setQuizQ3A2(quizQ3A2.getText().toString().trim());
                            quiz.setQuizQ3A3(quizQ3A3.getText().toString().trim());
                            quiz.setQuizQ3A4(quizQ3A4.getText().toString().trim());

                            // Insert data to the database
                            //dbRef.push().setValue(quiz);
                            dbRef.child(String.valueOf(quizID+1)).setValue(quiz);

                            //Feedback to the user via a Toast
                            Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_LONG).show();

                            // Reset input fields
                            clearControls();
                        } // else ends
                    } // try ends
                    catch (Exception e) {
                    /*
                    Writer writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));
                    String errorMsg = writer.toString();

                     */
                        Toast.makeText(getApplicationContext(), "errorMsg", Toast.LENGTH_LONG).show();
                    }
            }// onCLick() ends
        }); //setOnClickListener() SAVE ends


    }// end of onCreate

/*
    public void gotoQuizListActivity (View view) {

        Intent intent = new Intent(this, QuizList.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Saving the Quiz....", Toast.LENGTH_LONG).show();
    }
    */
}