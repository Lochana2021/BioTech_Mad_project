package com.example.biotechgeneral;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class single_student extends AppCompatActivity {

    Assignment ass, ass1;
    DatabaseReference dbRef, dbRef1;
    TextView txtStdID, txtWeek, txtDate, txtWeather, txtPlace, txtDescription, txtTopic;
    EditText txtStdMarksAss;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_student);

        txtTopic = findViewById(R.id.singleStdTopic);


        Intent intent = getIntent();
        String stdID = intent.getStringExtra("stdID");
        String topicName = intent.getStringExtra("TOPIC_NAME");

        txtTopic.setText(topicName);

        //Toast.makeText(getApplicationContext(),stdID,Toast.LENGTH_LONG).show();

        ass = new Assignment();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Assignment").child(topicName).child(stdID);
        //dbRef = FirebaseDatabase.getInstance().getReference();
        //.child("Assignment").child(stdID)
        //.child("Assignment").child("1")
        /*assigning variables*/
        txtStdID = findViewById(R.id.StdNameView);
        txtDate = findViewById(R.id.TechDateView);
        txtWeather = findViewById(R.id.TechWeatherView);
        txtPlace = findViewById(R.id.TechPlaceView);
        txtDescription = findViewById(R.id.TechDescriptionView);


        //.child("Assignment").orderByChild("stdAssID").equalTo(stdID)
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Toast.makeText(getApplicationContext(),stdID,Toast.LENGTH_LONG).show();
                if (snapshot.hasChildren()){

                    txtStdID.setText(snapshot.child("stdAssID").getValue().toString());
                    txtWeek.setText(snapshot.child("week").getValue().toString());
                    txtDate.setText(snapshot.child("date").getValue().toString());
                    txtWeather.setText(snapshot.child("weather").getValue().toString());
                    txtPlace.setText(snapshot.child("place").getValue().toString());
                    txtDescription.setText(snapshot.child("description").getValue().toString());
                }
                else
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //insert student marks
        txtStdMarksAss = findViewById(R.id.techMarksEnter);
        btnSave = findViewById(R.id.asstechmarksbtn);

        //dbRef = FirebaseDatabase.getInstance().getReference().child("Assignment").child(topicName);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Assignment");

       /* dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(stdID)){
                    ass.setStdMarksAss(Integer.parseInt(txtStdMarksAss.getText().toString().trim()));

                    //dbRef = FirebaseDatabase.getInstance().getReference().child("Assignment").child(topicName).child(stdID);
                    //dbRef.setValue(ass);
                    dbRef.child(topicName).child(stdID).child("stdMarksAss").push().setValue(ass);
                    Toast.makeText(getApplicationContext(), "Marks saved", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        //ass = new Assignment();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Take inputs from the user and assigning them to this instance (ass) of the Assignment...
                //dbRef.child("Assignment").child(topicName).child(stdID).child("stdMarksAss").setValue(Integer.parseInt(txtStdMarksAss.getText().toString().trim()));
                ass.setStdMarksAss(Integer.parseInt(txtStdMarksAss.getText().toString().trim()));
                dbRef.child(topicName).child(stdID).child("Marks").push().setValue(ass);

                Toast.makeText(getApplicationContext(), "Marks saved", Toast.LENGTH_LONG).show();
                /*dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild("Marks")){
                            Toast.makeText(getApplicationContext(), "Marks already exists", Toast.LENGTH_LONG).show();
                        }
                        else{
                            //dbRef.child("Assignment").child(topicName).child(stdID).child("stdMarksAss").setValue(Integer.parseInt(txtStdMarksAss.getText().toString().trim()));
                            ass.setStdMarksAss(Integer.parseInt(txtStdMarksAss.getText().toString().trim()));
                            dbRef.child(topicName).child(stdID).child("Marks").push().setValue(ass);

                            Toast.makeText(getApplicationContext(), "Marks saved", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/




            }

        });
    }
}