package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateQuiz extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;

    QuizClass quiz;
    DatePickerDialog picker;
    DatabaseReference dbRef;
    EditText  quizNo, lecturerID, quizPassMark, quizDeadline, quizQ1, quizQ1CorrectAnswer,
            quizQ1A1, quizQ1A2, quizQ1A3, quizQ1A4, quizQ2, quizQ2CorrectAnswer,
            quizQ2A1, quizQ2A2, quizQ2A3, quizQ2A4, quizQ3, quizQ3CorrectAnswer,
            quizQ3A1, quizQ3A2, quizQ3A3, quizQ3A4;
    Button idBtnCancel, idBtnSave;
   // long quizID = 0;
    // Notification variables
    String name = "Notification Channel";
    String CHANNEL_ID = "ID_1";
    String description = "Sample Description";

    // Clear out all user inputs
    public void clearControls() {
        quizNo.setText("");
        //lecturerID.setText("");
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

        /*
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
         */

        // NOTIFICATION
        //checking the API for Notification Channel and Create a new channel

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            ///////  Register the created Notification Channel
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);
        }

        // CALENDAR
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

                // Restrict Past Dates from the Calendar
                picker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                picker.show();
            }
        });

        // CANCEL button onCLick()
        idBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearControls();
                /*
                String storeInputNum = quizNo.getText().toString();
                if (storeInputNum.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Already Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    clearControls();
                }
               */
            }
        });

        // SAVE button onCLick()
        idBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    try {
                        if (TextUtils.isEmpty(quizNo.getText().toString())){
                            quizNo.setError("Please enter Quiz Number.");
                            Toast.makeText(getApplicationContext(), "Please enter Quiz Number.", Toast.LENGTH_LONG).show();
                        }
                        else if (TextUtils.isEmpty(quizPassMark.getText().toString())) {
                            quizPassMark.setError("Please enter Quiz Pass Mark.");
                            Toast.makeText(getApplicationContext(), "Please enter Quiz Pass Mark.", Toast.LENGTH_LONG).show();
                        }
                       /* else if (Integer.parseInt(quizPassMark.getText().toString()) >= 100) {
                            quizPassMark.setError("Please enter Pass Mark less than 100.");
                            Toast.makeText(getApplicationContext(), "Please enter Pass Mark less than 100.", Toast.LENGTH_LONG).show();
                        }*/
                        else if (TextUtils.isEmpty(quizDeadline.getText().toString())){
                            quizDeadline.setError("Please enter Quiz Deadline.");
                            Toast.makeText(getApplicationContext(), "Please enter Quiz Deadline.", Toast.LENGTH_LONG).show();
                        }
                        else if (TextUtils.isEmpty(quizQ1.getText().toString())) {
                            quizQ1.setError("Please enter Question 1");
                            Toast.makeText(getApplicationContext(), "Please enter Question 1.", Toast.LENGTH_LONG).show();
                        }
                        else if (TextUtils.isEmpty(quizQ2.getText().toString())) {
                            quizQ2.setError("Please enter Question 2");
                            Toast.makeText(getApplicationContext(), "Please enter Question 2.", Toast.LENGTH_LONG).show();
                        }
                        else if (TextUtils.isEmpty(quizQ3.getText().toString())) {
                            quizQ3.setError("Please enter Question 3");
                            Toast.makeText(getApplicationContext(), "Please enter Question 3.", Toast.LENGTH_LONG).show();
                        }
                        else if (TextUtils.isEmpty(quizQ1CorrectAnswer.getText().toString())) {
                            quizQ1CorrectAnswer.setError("Please enter Question 1 CORRECT ANSWER");
                            Toast.makeText(getApplicationContext(), "Please enter Question 1 CORRECT ANSWER.", Toast.LENGTH_LONG).show();
                        }
                        else if ( !(quizQ1CorrectAnswer.getText().toString().equals(quizQ1A1.getText().toString()) ) && !(quizQ1CorrectAnswer.getText().toString().equals(quizQ1A2.getText().toString()) ) && !(quizQ1CorrectAnswer.getText().toString().equals(quizQ1A3.getText().toString()) ) && !(quizQ1CorrectAnswer.getText().toString().equals(quizQ1A4.getText().toString()) )){
                            quizQ1CorrectAnswer.setError("Please choose one of the above answers as CORRECT ANSWER.");
                            Toast.makeText(getApplicationContext(),"Please choose one of the above answers as CORRECT ANSWER.", Toast.LENGTH_SHORT).show();
                        }

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
                            //dbRef.child(String.valueOf(quizID+1)).setValue(quiz);
                            dbRef.child("Quiz " + quizNo.getText().toString().trim()).setValue(quiz);

                            //Feedback to the user via a Toast
                            Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_LONG).show();

                            /////////////// NOTIFICATION  ////////////////////

                            // Create Explicit Intent to navigate from Notification --> QuizList Activity
                            Intent intentNotify = new Intent(CreateQuiz.this, login_bio.class);
                            intentNotify.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            ///////    Intent will be in Pending state until we tap on the Notification
                            PendingIntent pendingIntent = PendingIntent.getActivity(CreateQuiz.this, 0, intentNotify, 0);

                            /////      Set the content of the notification. --> Title / Context / Icon
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                    //.setSmallIcon(R.drawable.ic_launcher_background)
                                    .setSmallIcon(R.drawable.ic_baseline_eco_24)
                                    //.setContentTitle("BioTech")
                                    .setContentTitle("BioTech")
                                    //.setContentText(" Deadline is " + quizDeadline.getText())
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                    .setColor(getApplicationContext().getResources().getColor(R.color.notificationGreen))
                                    .setColorized(true)
                                    //.setStyle(androidx.media.app.NotificationCompat.DecoratedMediaCustomViewStyle())
                                    //.setCustomContentView(notificationView)
                                    .setStyle(new NotificationCompat.BigTextStyle()
                                            .bigText("NEW QUIZ UPLOADED - QUIZ "+ quizNo.getText()+"  \nDeadline is " + quizDeadline.getText() + "\n "))
                                    .setVibrate(new long[]{0, 500, 1000})
                                    .setDefaults(Notification.DEFAULT_LIGHTS )
                                    .setContentIntent(pendingIntent)
                                    .setAutoCancel(true);     // when we tap the notification, it automatically disappears.

                            ////////  Call the Notification Manager object
                            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(CreateQuiz.this);

                            // builder object and unique ID
                            notificationManagerCompat.notify(0, builder.build());

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

        // Bottom navigation onClick listner
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


    }// end of onCreate
/*

    public void navigateToTeacherQuizList (View view) {

        Intent btmNavIntent = new Intent(CreateQuiz.this, QuizList.class);
        startActivity(btmNavIntent);

        Toast.makeText(getApplicationContext(), "Navigating....", Toast.LENGTH_SHORT).show();
    }
*/


}