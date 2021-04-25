package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class std_ass_input extends AppCompatActivity {

    EditText txtWeek, txtDate, txtWeather, txtPlace, txtDescription;
    Button btnSubmit;
    DatabaseReference dbRef;
    Assignment ass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_ass_input);

        Spinner mySpinnerType = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapterType = new ArrayAdapter<String>(std_ass_input.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.types));

        myAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerType.setAdapter(myAdapterType);

        /*assigning to id*/
        txtWeek = findViewById(R.id.AssWeekinput);
        txtDate = findViewById(R.id.AssDateinput);
        txtWeather = findViewById(R.id.AssWeatherinput);
        txtPlace = findViewById(R.id.AssPlaceinput);
        txtDescription = findViewById(R.id.AssDescriptionkinput);

        btnSubmit = findViewById(R.id.stdSubmitbtn);

        ass = new Assignment();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Assignment");
                try{
                     if (TextUtils.isEmpty(txtDate.getText().toString()))
                         Toast.makeText(getApplicationContext(),"Please enter the Date",Toast.LENGTH_LONG).show();
                     else if (TextUtils.isEmpty(txtWeather.getText().toString()))
                         Toast.makeText(getApplicationContext(),"Please enter the Weather",Toast.LENGTH_LONG).show();
                     else if (TextUtils.isEmpty(txtPlace.getText().toString()))
                         Toast.makeText(getApplicationContext(),"Please enter the Place",Toast.LENGTH_LONG).show();
                     else if (TextUtils.isEmpty(txtDescription.getText().toString()))
                         Toast.makeText(getApplicationContext(),"Please enter the Place",Toast.LENGTH_LONG).show();
                     else{
                         //Take inputs from the user and assigning them to this instance (ass) of the Assignment...
                         ass.setWeek(Integer.parseInt(txtWeek.getText().toString().trim()));
                         ass.setDate(txtDate.getText().toString().trim());
                         ass.setWeather(txtWeather.getText().toString().trim());
                         ass.setPlace(txtPlace.getText().toString().trim());
                         ass.setDescription(txtDescription.getText().toString().trim());

                         //Insert into the database...
                         //dbRef.push().setValue(ass);
                         dbRef.child("ASS1").setValue(ass);

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
        txtWeek.setText("");
        txtDate.setText("");
        txtWeather.setText("");
        txtPlace.setText("");
        txtDescription.setText("");
    }
}