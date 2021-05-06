package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuizList extends AppCompatActivity {

    ListView quizListView;
    ArrayList<String> quizArrayList = new ArrayList<>();
    DatabaseReference dbRef;
    ArrayAdapter<String> quizArrayAdapter;

    public BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);

        Intent getIntentMainActivity = getIntent();

        // Associate the quiz list with the corresponding ListView
        quizListView = (ListView) findViewById(R.id.idQuizListView);

        // Set the ArrayAdapter to the ListView
        quizArrayAdapter = new ArrayAdapter<String>(this,R.layout.quizlist_item_1,quizArrayList);
        quizListView.setAdapter(quizArrayAdapter);

        // Associate the quiz Firebase Database Reference with the database's quiz object
        dbRef = FirebaseDatabase.getInstance().getReference();

        // Attach a ChildEventListener to the quiz database, so we can retrieve the quiz entries
        dbRef.child("QuizClass").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                // Get the value from the DataSnapshot and add it to the quiz' list
                String quizMapped = snapshot.child("quizNo").getValue(String.class);
                quizArrayList.add("Quiz "+quizMapped);
               // quizArrayList.add(quizMapped);

                // Notify the ArrayAdapter that there was a change
                quizArrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                quizArrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );

        // CLick on ListView items to navigate
        quizListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedQuizNumber = String.valueOf(parent.getItemAtPosition(position));
                final Intent intent = new Intent(getApplicationContext(),ViewQuiz.class);
                intent.putExtra("QUIZ_NUM", clickedQuizNumber);
                startActivity(intent);
            }
        });

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

    }// onCreate ends



    public void gotoCreateQuizActivity (View view) {

        Intent intentCreateQuiz = new Intent(this, CreateQuiz.class);
        startActivity(intentCreateQuiz);

        Toast.makeText(getApplicationContext(), "Navigating....", Toast.LENGTH_SHORT).show();
    }

    /*
    public void gotoViewQuizActivity (View view) {

        Intent intent2 = new Intent(this, ViewQuiz.class);
        startActivity(intent2);

        Toast.makeText(getApplicationContext(), "Navigating....", Toast.LENGTH_SHORT).show();
    }

    public void gotoCreateQuizActivity (View view) {

        Intent intent = new Intent(this, CreateQuiz.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Navigating....", Toast.LENGTH_SHORT).show();
    }
    */
}