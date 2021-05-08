
package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizStudent extends AppCompatActivity {

    int count, wrong;
    private int right;
    String qn;
    EditText QuizNo;
    TextView q1,q2,q3;
    Button q1a1,q1a2,q1a3,q1a4,q2a1,q2a2,q2a3,q2a4,q3a1,q3a2,q3a3,q3a4,submit,attQuiz;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_student);

        QuizNo = (EditText)findViewById(R.id.QuizNumber);
        qn = QuizNo.getText().toString();

       /* StringBuilder builder = new StringBuilder("");
        builder.append("Quiz ").append(qn);
        String text =builder.toString();*/

        q1 = (TextView)findViewById(R.id.que1);
        q2 = (TextView)findViewById(R.id.que2);
        q3 = (TextView)findViewById(R.id.que3);
        q1a1 = (Button)findViewById(R.id.q1a1);
        q1a2 = (Button)findViewById(R.id.q1a2);
        q1a3 = (Button)findViewById(R.id.q1a3);
        q1a4 = (Button)findViewById(R.id.q1a4);
        q2a1 = (Button)findViewById(R.id.q2a1);
        q2a2 = (Button)findViewById(R.id.q2a2);
        q2a3 = (Button)findViewById(R.id.q2a3);
        q2a4 = (Button)findViewById(R.id.q2a4);
        q3a1 = (Button)findViewById(R.id.q3a1);
        q3a2 = (Button)findViewById(R.id.q3a2);
        q3a3 = (Button)findViewById(R.id.q3a3);
        q3a4 = (Button)findViewById(R.id.q3a4);
        submit = (Button)findViewById(R.id.submitQS);
        attQuiz = (Button)findViewById(R.id.attemptQuiz);

        attQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("QuizClass").child("Quiz "+qn);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String qe1 = snapshot.child("quizQ1").getValue().toString();
                        String Q1a1 = snapshot.child("quizQ1A1").getValue().toString();
                        String Q1a2 = snapshot.child("quizQ1A2").getValue().toString();
                        String Q1a3 = snapshot.child("quizQ1A3").getValue().toString();
                        String Q1a4 = snapshot.child("quizQ1A4").getValue().toString();
                        String Q1CA = snapshot.child("quizQ1CorrectAnswer").getValue().toString();


                        String qe2 = snapshot.child("quizQ2").getValue().toString();
                        String Q2a1 = snapshot.child("quizQ2A1").getValue().toString();
                        String Q2a2 = snapshot.child("quizQ2A2").getValue().toString();
                        String Q2a3 = snapshot.child("quizQ2A3").getValue().toString();
                        String Q2a4 = snapshot.child("quizQ2A4").getValue().toString();
                        String Q2CA = snapshot.child("quizQ2CorrectAnswer").getValue().toString();


                        String qe3 = snapshot.child("quizQ3").getValue().toString();
                        String Q3a1 = snapshot.child("quizQ3A1").getValue().toString();
                        String Q3a2 = snapshot.child("quizQ3A2").getValue().toString();
                        String Q3a3 = snapshot.child("quizQ3A3").getValue().toString();
                        String Q3a4 = snapshot.child("quizQ3A4").getValue().toString();
                        String Q3CA = snapshot.child("quizQ3CorrectAnswer").getValue().toString();



                        q1.setText(qe1);

                        q1a1.setText(Q1a1);
                        q1a2.setText(Q1a2);
                        q1a3.setText(Q1a3);
                        q1a4.setText(Q1a4);

                        q2.setText(qe2);
                        q2a1.setText(Q2a1);
                        q2a2.setText(Q2a2);
                        q2a3.setText(Q2a3);
                        q2a4.setText(Q2a4);


                        q3.setText(qe3);
                        q3a1.setText(Q3a1);
                        q3a2.setText(Q3a2);
                        q3a3.setText(Q3a3);
                        q3a4.setText(Q3a4);



                        /*question 01*/
                            q1a1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Q1a1.equals(Q1CA))
                                    {
                                        right ++;
                                        count ++;
                                    }
                                    else
                                    {
                                        wrong ++;
                                        count ++;
                                    }
                                }
                            });

                            q1a2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Q1a2.equals(Q1CA))
                                    {
                                        right ++;
                                        count ++;
                                    }
                                    else
                                    {
                                        wrong ++;
                                        count ++;
                                    }
                                }
                            });

                            q1a3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Q1a3.equals(Q1CA))
                                    {
                                        right ++;
                                        count ++;
                                    }
                                    else
                                    {
                                        wrong ++;
                                        count ++;
                                    }
                                }
                            });

                            q1a4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Q1a4.equals(Q1CA))
                                    {
                                        right ++;
                                        count ++;
                                    }
                                    else
                                    {
                                        wrong ++;
                                        count ++;
                                    }
                                }
                            });



                        /*Question 02*/
                            q2a1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Q2a1.equals(Q2CA))
                                    {
                                        right ++;
                                        count ++;
                                    }
                                    else
                                    {
                                        wrong ++;
                                        count ++;
                                    }
                                }
                            });

                            q2a2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Q2a2.equals(Q2CA))
                                    {
                                        right ++;
                                        count ++;
                                    }
                                    else
                                    {
                                        wrong ++;
                                        count ++;
                                    }
                                }
                            });

                            q2a3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Q2a3.equals(Q2CA))
                                    {
                                        right ++;
                                        count ++;
                                    }
                                    else
                                    {
                                        wrong ++;
                                        count ++;
                                    }
                                }
                            });

                            q2a4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Q2a4.equals(Q2CA))
                                    {
                                        right ++;
                                        count ++;
                                    }
                                    else
                                    {
                                        wrong ++;
                                        count ++;
                                    }
                                }
                            });



                        /*question 03*/
                            q3a1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Q3a1.equals(Q3CA))
                                    {
                                        right ++;
                                        count ++;
                                    }
                                    else
                                    {
                                        wrong ++;
                                        count ++;
                                        Toast.makeText(QuizStudent.this,qn,Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                            q3a2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Q3a2.equals(Q3CA))
                                    {
                                        right ++;
                                        count ++;
                                    }
                                    else
                                    {
                                        wrong ++;
                                        count ++;
                                        Toast.makeText(QuizStudent.this,qn,Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                            q3a3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Q3a3.equals(Q3CA))
                                    {
                                        right ++;
                                        count ++;
                                    }
                                    else
                                    {
                                        wrong ++;
                                        count ++;
                                        Toast.makeText(QuizStudent.this,qn,Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                            q3a4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Q3a4.equals(Q3CA))
                                    {
                                        right ++;
                                        count ++;
                                    }
                                    else
                                    {
                                        wrong ++;
                                        count ++;
                                        Toast.makeText(QuizStudent.this,qn,Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passValue = String.valueOf(right);
                Intent intent = new Intent(QuizStudent.this,activity_quiz_attendance.class);
                intent.putExtra("total", passValue);
                startActivity(intent);
            }
        });


    }


}