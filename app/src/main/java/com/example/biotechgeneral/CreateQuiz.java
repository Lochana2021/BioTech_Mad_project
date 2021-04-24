package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        Intent getIntentQList = getIntent();

        //Set Cancel button function

        EditText inputNum = findViewById(R.id.idInputQuizNumber);
        Button btnCancel = findViewById(R.id.idBtnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String storeInputNum = inputNum.getText().toString();
                if (storeInputNum.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Already Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    inputNum.setText("");
                }

            }
        });
    }// end of onCreate

    ////// Set SAVE button function

    public void gotoQuizListActivity (View view) {

        Intent intent = new Intent(this, QuizList.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Saving the Quiz....", Toast.LENGTH_LONG).show();
    }
}