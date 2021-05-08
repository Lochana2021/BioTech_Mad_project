package com.example.biotechgeneral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText txtFull_name, txtPassword, txtEmail;
    Button btnSignUp;
    DatabaseReference dbRef;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFull_name = findViewById(R.id.regFullName);
        txtEmail = findViewById(R.id.regEmail);
        txtPassword = findViewById(R.id.regPassword);

        btnSignUp = findViewById(R.id.btnRegSignUp);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),navigation.class));
            finish();
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    txtEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    txtPassword.setError("Password is Required");
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    txtEmail.setError("Please provide valid email");
                    return;
                }
                if(password.length() < 6){
                    txtPassword.setError("Password must be >= 6 characters");
                    return;
                }

                //register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Student std = new Student();

                            std.setFull_name(txtFull_name.getText().toString().trim());
                            std.setEmail(txtEmail.getText().toString().trim());
                            std.setPassword(txtPassword.getText().toString().trim());

                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(std);

                            Toast.makeText(getApplicationContext(),"User created Successfully",Toast.LENGTH_LONG).show();

                            startActivity(new Intent(getApplicationContext(),navigation.class));
                        }else{
                            Toast.makeText(getApplicationContext(),"Error !"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });

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