package com.example.biotechgeneral;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddQuetion extends AppCompatActivity {

// Declare Attributes

    EditText fname, quetion, quetionNo;
    Button submit, cancel;
    DatabaseReference F_dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quetion);

    // initialize variables for xml widgets

        fname = findViewById(R.id.txt_FName);
        quetion = findViewById(R.id.F_Addquetion);
        submit = findViewById(R.id.btn_FsubmitQ);
        quetionNo = findViewById(R.id.txt_quetionNo);
        cancel = findViewById(R.id.Add_QuetionCancel);

    // create connection with firebase database

        F_dbRef = FirebaseDatabase.getInstance().getReference().child("ForumQuetion");

        // Create Notification

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Forum Response", "Forum Response", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Declare Insert Function

                insertQuetion();

                // Create notification when click the button

                NotificationCompat.Builder builder = new NotificationCompat.Builder(AddQuetion.this,"Forum Response");
                builder.setContentTitle("New Question");
                builder.setContentText("New Question is added by student");
                builder.setSmallIcon(R.drawable.ic_baseline_eco_24);
                builder.setAutoCancel(true);
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AddQuetion.this);
                managerCompat.notify(1,builder.build());


            }
        });



    }

    //Create insert function body

   private void insertQuetion(){
        String ForumName = fname.getText().toString();
        String Quetion = quetion.getText().toString();
       String QuetionNo = quetionNo.getText().toString();
        ForumStudent forumStudent = new ForumStudent(ForumName, Quetion, QuetionNo);


        F_dbRef.push().setValue(forumStudent);
        Toast.makeText(AddQuetion.this,"Quetion added",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,Forum_Dashboard.class);
        startActivity(i);
    }

    // Cancel Button

    public void onCancel(View view) {
        Intent i = new Intent(this,Forum_Dashboard.class);
        startActivity(i);

    }
}