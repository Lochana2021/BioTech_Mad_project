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
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    Animation scaleUp, scaleDown;

    //notification
    String name = "Notification_channel";
    String CHANNEL_ID = "ID_1";
    String description = "Project Notification";

    //long assID = 0;

    @SuppressLint("ClickableViewAccessibility")
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

        //button animation
        /*scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this,R.anim.scale_down);

        btnSubmit.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    btnSubmit.startAnimation(scaleUp);
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    btnSubmit.startAnimation(scaleDown);
                }
                return true;
            }
        });*/

        //notification
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            //Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }

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


        ass = new Assignment();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String inputStdID = txtStdID.getText().toString().trim();
                    String inputSpinner = mySpinnerType.getSelectedItem().toString().trim();
                try{
                     if (TextUtils.isEmpty(txtStdID.getText().toString())) {
                         txtStdID.setError("Student ID Date must be filled out");
                         return;
                     }
                     else if (TextUtils.isEmpty(txtWeek.getText().toString())){
                         txtWeek.setError("Week must be filled out");
                         return;
                     }
                     else if (TextUtils.isEmpty(txtDate.getText().toString())){
                         txtDate.setError("Date must be filled out");
                         return;
                     }
                     else if (TextUtils.isEmpty(txtWeather.getText().toString())){
                         txtWeather.setError("Weather must be filled out");
                         return;
                     }

                     else if (TextUtils.isEmpty(txtPlace.getText().toString())){
                         txtPlace.setError("Place must be filled out");
                         return;
                     }

                     else if (TextUtils.isEmpty(txtDescription.getText().toString())){
                         txtDescription.setError("Description must be filled out");
                         return;
                     }

                     else{
                         //Take inputs from the user and assigning them to this instance (ass) of the Assignment...
                         ass.setStdAssID(inputStdID);
                         ass.setWeek(Integer.parseInt(txtWeek.getText().toString().trim()));
                         ass.setType(inputSpinner);
                         ass.setDate(txtDate.getText().toString().trim());
                         ass.setWeather(txtWeather.getText().toString().trim());
                         ass.setPlace(txtPlace.getText().toString().trim());
                         ass.setDescription(txtDescription.getText().toString().trim());
                        // ass.setStdMarksAss("0");

                         //Insert into the database...
                         //dbRef.push().setValue(ass);
                         //dbRef.child("ASS2").setValue(ass);
                         //dbRef.child(String.valueOf(assID+1)).setValue(ass);
                         dbRef.child(inputSpinner).child(inputStdID).setValue(ass);

                         //Feedback to the user via a Toast...
                         Toast.makeText(getApplicationContext(),"Submitted Successfully",Toast.LENGTH_LONG).show();

                         //notification
                         Intent intent = new Intent(std_ass_input.this, lecturer_login.class);
                         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                         PendingIntent pendingIntent = PendingIntent.getActivity(std_ass_input.this, 0,intent,0);

                         NotificationCompat.Builder builder = new NotificationCompat.Builder(std_ass_input.this,CHANNEL_ID)
                                 .setSmallIcon(R.drawable.baseline_eco_black_36dp)
                                 .setContentTitle("BioTech")
                                 .setContentText(txtStdID.getText()+" has attempted "+mySpinnerType.getSelectedItem())

                                 .setColor(getApplicationContext().getResources().getColor(R.color.notificationGreen))
                                 .setColorized(true)
                                 //.setStyle(androidx.media.app.NotificationCompat.DecoratedMediaCustomViewStyle())
                                 //.setCustomContentView(notificationView)

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