package com.example.biotechgeneral;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class sigle_type extends AppCompatActivity {

    private static TextView txtTopicType, txtStdAttCount, txtPercentage;
    TextView etn;

    ListView stdAssListView;
    ArrayList<String> assStdArrayList = new ArrayList<>();
    DatabaseReference dbRef, attCountRef, dbRef2;

    public String studentID;

    int attCount = 0;
    private static float percentage = (float) 0.0;

    public BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigle_type);

        /*intent passing*/
        txtTopicType = findViewById(R.id.txtTopicType);
        txtStdAttCount = findViewById(R.id.stdAttemView);
        txtPercentage = findViewById(R.id.stdAssPerView);

        Intent intent = getIntent();
        String topicName = intent.getStringExtra("TYPE_01");
        //int week = intent.getIntExtra("WEEK",0);

        txtTopicType.setText(topicName);
        //etn.setText(week);

        /*adapter retrieve listView*/
        //ArrayAdapter<String> assStdArrayAdapter = new ArrayAdapter<String>(sigle_type.this.android.R.layout.simple_list_item_1,assStdArrayList);
        stdAssListView = (ListView) findViewById(R.id.stdListAss);
        ArrayAdapter<String> assStdArrayAdapter = new ArrayAdapter<String>(this,R.layout.liststdass_item,assStdArrayList);


        stdAssListView.setAdapter(assStdArrayAdapter);

        //dbRef = FirebaseDatabase.getInstance().getReference().child("Assignment");
        dbRef = FirebaseDatabase.getInstance().getReference().child("Assignment").child(topicName);
        //Toast.makeText(getApplicationContext(),topicName,Toast.LENGTH_LONG).show();



        /*// Attach a ChildEventListener to the quiz database, so we can retrieve the quiz entries
        dbRef.child("QuizClass").addChildEventListener(new ChildEventListener() {*/
        //dbRef.child("Assignment").addChildEventListener(new ChildEventListener()
        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String assMapped = snapshot.child("stdAssID").getValue(String.class);
                assStdArrayList.add(assMapped);
                assStdArrayAdapter.notifyDataSetChanged();

                /*// Get the value from the DataSnapshot and add it to the quiz' list
                String quizMapped = snapshot.child("quizNo").getValue(String.class);
                quizArrayList.add("Quiz "+quizMapped);

                // Notify the ArrayAdapter that there was a change
                quizArrayAdapter.notifyDataSetChanged();*/
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                assStdArrayAdapter.notifyDataSetChanged();
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
        });

        stdAssListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent1 = new Intent(getApplicationContext(),single_student.class);

                //startActivity(new Intent(getApplicationContext(),single_student.class));
                //startActivity(new Intent(getApplicationContext(),single_student.class));
                studentID = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(getApplicationContext(),"Moving to " +studentID,Toast.LENGTH_LONG).show();


                intent1.putExtra("stdID",studentID);

                intent1.putExtra("TOPIC_NAME",topicName);
                //Toast.makeText(getApplicationContext(),topicName,Toast.LENGTH_LONG).show();
                startActivity(intent1);

                /*//create intent
                final Intent intent = new Intent(this,sigle_type.class);
                String typeName = "Mutualism";

                intent.putExtra("TYPE_01",typeName);*/



            }
        });

        /*getting student count*/
        attCountRef = FirebaseDatabase.getInstance().getReference().child("Assignment").child(topicName);

        attCountRef.addValueEventListener(new ValueEventListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    attCount = (int) snapshot.getChildrenCount();
                    String stdCount = String.valueOf(attCount);

                    txtStdAttCount.setText(stdCount);

                    txtPercentage.setText(String.valueOf(attPerCal(attCount)) + "%");

                } else {
                    txtStdAttCount.setText("0");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //catching marks
        /*dbRef2 = FirebaseDatabase.getInstance().getReference().child("Assignment");

       dbRef2.child(topicName).child(studentID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    //String marksVal = snapshot.child("stdMarksAss").getValue().toString();
                    //Toast.makeText(getApplicationContext(),marksVal,Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"No error",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        // Bottom navigation onClick listner
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.idTeacher_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_TQuiz:
                        //Add your action onClick
                        Intent intentCreateQuiz = new Intent(getApplicationContext(), QuizList.class);
                        startActivity(intentCreateQuiz);
                        break;
                    case R.id.action_TForum:
                        Intent intentForum = new Intent(getApplicationContext(), Forum_Dashboard.class);
                        startActivity(intentForum);
                        break;

                    case R.id.action_TAssignment:
                        Intent intentAssT = new Intent(getApplicationContext(), ass_teacher.class);
                        startActivity(intentAssT);

                        break;

                    case R.id.action_TProfile:
                        break;
                }
                return false;
            }
        });//nav end


    }
    /*Calculation*/
    public static float attPerCal (int attCountLocal){

        percentage = (float) Math.round(((attCountLocal / 60.0) * 100));
        return percentage;

    }

}
