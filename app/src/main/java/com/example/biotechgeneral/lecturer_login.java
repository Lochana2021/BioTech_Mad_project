package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class lecturer_login extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button btnLogIn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_login);

        txtEmail = findViewById(R.id.lecLoginEmail);
        txtPassword = findViewById(R.id.lecLoginPassword);

        btnLogIn = findViewById(R.id.btnLecLogin);
        fAuth = FirebaseAuth.getInstance();

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                //validate
                if(TextUtils.isEmpty(email)){
                    txtEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    txtPassword.setError("Password is Required");
                    return;
                }
                if(password.length() < 6){
                    txtPassword.setError("Password must be >= 6 characters");
                    return;
                }
                //authenticate the user...
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(lecturer_login.this,"Logged in Successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),navigation.class));
                        }
                        else {
                            Toast.makeText(lecturer_login.this,"Logged in Unsuccessful",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}