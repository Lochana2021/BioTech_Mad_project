package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewQuiz extends AppCompatActivity {
    QuizClass quiz;
    DatabaseReference dbRef;
    TextView quizNo, lecturerID, quizPassMark, quizDeadline, quizQ1, quizQ1CorrectAnswer,
            quizQ1A1, quizQ1A2, quizQ1A3, quizQ1A4, quizQ2, quizQ2CorrectAnswer,
            quizQ2A1, quizQ2A2, quizQ2A3, quizQ2A4, quizQ3, quizQ3CorrectAnswer,
            quizQ3A1, quizQ3A2, quizQ3A3, quizQ3A4;

    long quizID = 0;

    public BottomNavigationView bottomNavigationView;
    private String clickedQuizNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quiz);

        Intent getIntentQuizListActivity = getIntent();
        clickedQuizNo = getIntentQuizListActivity.getStringExtra("QUIZ_NUM");

        quiz = new QuizClass();
        dbRef = FirebaseDatabase.getInstance().getReference().child("QuizClass").child(clickedQuizNo);

        quizNo = findViewById(R.id.idInputQuizNumber_View);
        quizPassMark = findViewById(R.id.idInputPassMark_View);
        quizDeadline = findViewById(R.id.idInputDeadline_View);

        quizQ1 = findViewById(R.id.idInputQ1_View);
        quizQ1CorrectAnswer = findViewById(R.id.inputCorrectQ1_View);
        quizQ1A1 = findViewById(R.id.inputQ1Ans1_View);
        quizQ1A2 = findViewById(R.id.inputQ1Ans2_View);
        quizQ1A3 = findViewById(R.id.inputQ1Ans3_View);
        quizQ1A4 = findViewById(R.id.inputQ1Ans4_View);
        quizQ2 = findViewById(R.id.idInputQ2_View);
        quizQ2CorrectAnswer = findViewById(R.id.inputCorrectQ2_View);
        quizQ2A1 = findViewById(R.id.inputQ2Ans1_View);
        quizQ2A2 = findViewById(R.id.inputQ2Ans2_View);
        quizQ2A3 = findViewById(R.id.inputQ2Ans3_View);
        quizQ2A4 = findViewById(R.id.inputQ2Ans4_View);
        quizQ3 = findViewById(R.id.idInputQ3_View);
        quizQ3CorrectAnswer = findViewById(R.id.inputCorrectQ3_View);
        quizQ3A1 = findViewById(R.id.inputQ3Ans1_View);
        quizQ3A2 = findViewById(R.id.inputQ3Ans2_View);
        quizQ3A3 = findViewById(R.id.inputQ3Ans3_View);
        quizQ3A4 = findViewById(R.id.inputQ3Ans4_View);

        //Add a click listener to ListView items
        //.child("QuizClass").orderByChild("quizNo").equalTo(clickedQuizNo)
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){

                    //Toast.makeText(getApplicationContext(), clickedQuizNo , Toast.LENGTH_SHORT).show();

                    quizNo.setText(snapshot.child("quizNo").getValue().toString());
                    quizPassMark.setText(snapshot.child("quizPassMark").getValue().toString());
                    quizDeadline.setText(snapshot.child("quizDeadline").getValue().toString());
                    quizQ1.setText(snapshot.child("quizQ1").getValue().toString());
                    quizQ1CorrectAnswer.setText(snapshot.child("quizQ1CorrectAnswer").getValue().toString());
                    quizQ1A1.setText(snapshot.child("quizQ1A1").getValue().toString());
                    quizQ1A2.setText(snapshot.child("quizQ1A2").getValue().toString());
                    quizQ1A3.setText(snapshot.child("quizQ1A3").getValue().toString());
                    quizQ1A4.setText(snapshot.child("quizQ1A4").getValue().toString());
                    quizQ2.setText(snapshot.child("quizQ2").getValue().toString());
                    quizQ2CorrectAnswer.setText(snapshot.child("quizQ2CorrectAnswer").getValue().toString());
                    quizQ2A1.setText(snapshot.child("quizQ2A1").getValue().toString());
                    quizQ2A2.setText(snapshot.child("quizQ2A2").getValue().toString());
                    quizQ2A3.setText(snapshot.child("quizQ2A3").getValue().toString());
                    quizQ2A4.setText(snapshot.child("quizQ2A4").getValue().toString());
                    quizQ3.setText(snapshot.child("quizQ3").getValue().toString());
                    quizQ3CorrectAnswer.setText(snapshot.child("quizQ3CorrectAnswer").getValue().toString());
                    quizQ3A1.setText(snapshot.child("quizQ3A1").getValue().toString());
                    quizQ3A2.setText(snapshot.child("quizQ3A2").getValue().toString());
                    quizQ3A3.setText(snapshot.child("quizQ3A3").getValue().toString());
                    quizQ3A4.setText(snapshot.child("quizQ3A4").getValue().toString());
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Quiz records are found." , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // Bottom navigation onClick listener
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.idTeacher_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_TQuiz:
                        //Add your action onClick
                        Intent intentQuiz = new Intent(getApplicationContext(), QuizList.class);
                        startActivity(intentQuiz);
                        break;
                    case R.id.action_TForum:
                        Intent intentForum = new Intent(getApplicationContext(), Forum_Dashboard.class);
                        startActivity(intentForum);
                        break;

                    case R.id.action_TAssignment:
                        Intent intentAssign = new Intent(getApplicationContext(), ass_teacher.class);
                        startActivity(intentAssign);
                        break;

                    case R.id.action_TProfile:
                        break;
                }
                return false;
            }
        });

    } //onCreate ends

    public void gotoPassListActivity (View view) {

        Intent intentPassList = new Intent(this, PassList.class);
        intentPassList.putExtra("QUIZ_NUM", clickedQuizNo);
        intentPassList.putExtra("PASS_MARK", quizPassMark.getText().toString());
        startActivity(intentPassList);

        Toast.makeText(getApplicationContext(), clickedQuizNo +" and "+ quizPassMark.getText().toString(), Toast.LENGTH_SHORT).show();

    }
}