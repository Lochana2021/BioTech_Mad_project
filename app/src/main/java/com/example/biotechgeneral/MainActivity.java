package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText txtFull_name, txtPassword, txtEmail;
    Button btnSignUp;
    DatabaseReference dbRef;
    Student std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFull_name = findViewById(R.id.regFullName);
        txtEmail = findViewById(R.id.regEmail);
        txtPassword = findViewById(R.id.regPassword);

        btnSignUp = findViewById(R.id.btnRegSignUp);

        std = new Student();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Student");

                if (TextUtils.isEmpty(txtFull_name.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Please enter your Full Name",Toast.LENGTH_LONG).show();
                else if (TextUtils.isEmpty(txtEmail.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Please enter your email Address",Toast.LENGTH_LONG).show();
                else if (TextUtils.isEmpty(txtPassword.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Please enter the Password",Toast.LENGTH_LONG).show();
                else{
                    //Take inputs from the user and assigning them to this instance (ass) of the Assignment...
                    std.setFull_name(txtFull_name.getText().toString().trim());
                    std.setEmail(txtEmail.getText().toString().trim());
                    std.setPassword(txtPassword.getText().toString());

                    //Insert into the database...
                    //dbRef.push().setValue(ass);
                    dbRef.child("STD1").setValue(std);

                    //Feedback to the user via a Toast...
                    Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                    clearControls();
                }
            }
        });
    }

    //Method to clear all user inputs
    private void clearControls(){
        txtFull_name.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
    }
}