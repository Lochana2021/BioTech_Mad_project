package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class std_ass_input extends AppCompatActivity {

    EditText txtStdID, txtWeek, txtDate, txtWeather, txtPlace, txtDescription;
    Button btnSubmit;
    DatabaseReference dbRef;


    long assID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_ass_input);

        Spinner mySpinnerType = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapterType = new ArrayAdapter<String>(std_ass_input.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.types));

        myAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerType.setAdapter(myAdapterType);

        Assignment ass;
        dbRef = FirebaseDatabase.getInstance().getReference().child("Assignment");

        /*assigning to id*/
        txtStdID = findViewById(R.id.AssIDinput);
        txtWeek = findViewById(R.id.AssWeekinput);
        txtDate = findViewById(R.id.AssDateinput);
        txtWeather = findViewById(R.id.AssWeatherinput);
        txtPlace = findViewById(R.id.AssPlaceinput);
        txtDescription = findViewById(R.id.AssDescriptionkinput);

        btnSubmit = findViewById(R.id.stdSubmitbtn);

        // auto increment if any changes made to the data in database
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    assID = (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ass = new Assignment();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                     if (TextUtils.isEmpty(txtStdID.getText().toString()))
                         Toast.makeText(getApplicationContext(),"Please enter your Student ID",Toast.LENGTH_LONG).show();
                     else if (TextUtils.isEmpty(txtDate.getText().toString()))
                         Toast.makeText(getApplicationContext(),"Please enter the Date",Toast.LENGTH_LONG).show();
                     else if (TextUtils.isEmpty(txtWeather.getText().toString()))
                         Toast.makeText(getApplicationContext(),"Please enter the Weather",Toast.LENGTH_LONG).show();
                     else if (TextUtils.isEmpty(txtPlace.getText().toString()))
                         Toast.makeText(getApplicationContext(),"Please enter the Place",Toast.LENGTH_LONG).show();
                     else if (TextUtils.isEmpty(txtDescription.getText().toString()))
                         Toast.makeText(getApplicationContext(),"Please enter the Place",Toast.LENGTH_LONG).show();
                     else{
                         //Take inputs from the user and assigning them to this instance (ass) of the Assignment...
                         ass.setStdAssID(txtStdID.getText().toString().trim());
                         ass.setWeek(Integer.parseInt(txtWeek.getText().toString().trim()));
                         ass.setDate(txtDate.getText().toString().trim());
                         ass.setWeather(txtWeather.getText().toString().trim());
                         ass.setPlace(txtPlace.getText().toString().trim());
                         ass.setDescription(txtDescription.getText().toString().trim());

                         //Insert into the database...
                         //dbRef.push().setValue(ass);
                         //dbRef.child("ASS2").setValue(ass);
                         dbRef.child(String.valueOf(assID+1)).setValue(ass);

                         //Feedback to the user via a Toast...
                         Toast.makeText(getApplicationContext(),"Submitted Successfully",Toast.LENGTH_LONG).show();
                         clearControls();
                     }

                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Invalid input for Week", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //Method to clear all user inputs
    private void clearControls(){
        txtStdID.setText("");
        txtWeek.setText("");
        txtDate.setText("");
        txtWeather.setText("");
        txtPlace.setText("");
        txtDescription.setText("");
    }
}