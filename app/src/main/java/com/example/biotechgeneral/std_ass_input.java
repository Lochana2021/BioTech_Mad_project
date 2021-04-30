package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
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
    Spinner mySpinnerType;
    Button btnSubmit;
    DatabaseReference dbRef;

    //notification
    String name = "Notification_channel";
    String CHANNEL_ID = "ID_1";
    String description = "Project Notification";


    //long assID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_ass_input);

        mySpinnerType = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapterType = new ArrayAdapter<String>(std_ass_input.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.types));

        myAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerType.setAdapter(myAdapterType);

        Assignment ass;
        dbRef = FirebaseDatabase.getInstance().getReference().child("Assignment");

        //notification
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            //Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }

        /*assigning to id*/
        txtStdID = findViewById(R.id.AssIDinput);
        txtWeek = findViewById(R.id.AssWeekinput);
        txtDate = findViewById(R.id.AssDateinput);
        txtWeather = findViewById(R.id.AssWeatherinput);
        txtPlace = findViewById(R.id.AssPlaceinput);
        txtDescription = findViewById(R.id.AssDescriptionkinput);

        btnSubmit = findViewById(R.id.stdSubmitbtn);

        // auto increment if any changes made to the data in database
       /* dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    assID = (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        ass = new Assignment();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String inputStdID = txtStdID.getText().toString().trim();
                    String inputSpinner = mySpinnerType.getSelectedItem().toString().trim();
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
                         ass.setStdAssID(inputStdID);
                         ass.setWeek(Integer.parseInt(txtWeek.getText().toString().trim()));
                         ass.setType(inputSpinner);
                         ass.setDate(txtDate.getText().toString().trim());
                         ass.setWeather(txtWeather.getText().toString().trim());
                         ass.setPlace(txtPlace.getText().toString().trim());
                         ass.setDescription(txtDescription.getText().toString().trim());

                         //Insert into the database...
                         //dbRef.push().setValue(ass);
                         //dbRef.child("ASS2").setValue(ass);
                         //dbRef.child(String.valueOf(assID+1)).setValue(ass);
                         dbRef.child(inputSpinner).child(inputStdID).setValue(ass);

                         //Feedback to the user via a Toast...
                         Toast.makeText(getApplicationContext(),"Submitted Successfully",Toast.LENGTH_LONG).show();

                         //notification
                         Intent intent = new Intent(std_ass_input.this, ass_teacher.class);
                         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                         PendingIntent pendingIntent = PendingIntent.getActivity(std_ass_input.this, 0,intent,0);

                         NotificationCompat.Builder builder = new NotificationCompat.Builder(std_ass_input.this,CHANNEL_ID)
                                 .setSmallIcon(R.drawable.ic_launcher_background)
                                 .setContentTitle("BioTech")
                                 .setContentText(txtStdID.getText()+" has attempted to "+mySpinnerType.getSelectedItem())
                                 .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                 .setContentIntent(pendingIntent)
                                 .setAutoCancel(true);

                         NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(std_ass_input.this);
                         notificationManagerCompat.notify(0,builder.build());

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