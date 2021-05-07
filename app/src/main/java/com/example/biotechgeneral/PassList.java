package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PassList extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    private static double calcPassPercent = 0.0;

    TextView passPercentage;
    ListView passListView;
    ArrayList<String> passArrayList = new ArrayList<>();
    ArrayList<String> allArrayList = new ArrayList<>();
    DatabaseReference dbRef;
    ArrayAdapter<String> passArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_list);

        Intent getIntentViewQuiz = getIntent();

        String clickedQuizNo = getIntentViewQuiz.getStringExtra("QUIZ_NUM");
        String passMarkExtra = getIntentViewQuiz.getStringExtra("PASS_MARK");
        //int passMark = Integer.parseInt(passMarkExtra);
        passPercentage = findViewById(R.id.idPassPercentage_PassList);


        // Associate the quiz list with the corresponding ListView
        passListView = (ListView) findViewById(R.id.idPassListView);

        // Set the ArrayAdapter to the ListView
        passArrayAdapter = new ArrayAdapter<String>(this,R.layout.passlist_item_1,passArrayList);
        passListView.setAdapter(passArrayAdapter);

        // Associate the quiz Firebase Database Reference with the database's quiz object
        dbRef = FirebaseDatabase.getInstance().getReference().child("QuizStdResults").child(clickedQuizNo);
                //child(clickedQuizNo);

        // Attach a ChildEventListener to the quiz database, so we can retrieve the quiz entries
        dbRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                // Get the value from the DataSnapshot and add it to the quiz' list
                int resultMapped = Integer.parseInt(snapshot.child("result").getValue(String.class));
                int passMark = Integer.parseInt(passMarkExtra);
                String stdIDMapped = snapshot.child("stdID").getValue(String.class);

                allArrayList.add(stdIDMapped);
                int allStdCount = allArrayList.size();

                if(resultMapped >= passMark){
                    passArrayList.add("Student ID: " + stdIDMapped + "  Result: " + resultMapped);

                    // Notify the ArrayAdapter that there was a change
                    passArrayAdapter.notifyDataSetChanged();
                }
                int passStdCount = passArrayList.size();

                // Calculate pass percentage and display

                //passPercentage.setText(Double.toString(((passStdCount*100)/allStdCount))+"%");
                // String.format("%.2f", value)
                //passPercentage.setText(calcPassPercentage(passStdCount, allStdCount) +"%");
                passPercentage.setText(String.format("%.2f", calcPassPercentage(passStdCount, allStdCount))+"%");

            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                passArrayAdapter.notifyDataSetChanged();
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

    } //onCreate ends

    public static double calcPassPercentage(int stdPassCount, int stdAllCount){
        calcPassPercent = (stdPassCount*100.0)/stdAllCount;

        return calcPassPercent;
    }
}