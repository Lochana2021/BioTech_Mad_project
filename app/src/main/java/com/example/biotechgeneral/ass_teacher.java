package com.example.biotechgeneral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ass_teacher extends AppCompatActivity {

    Button button1,button2,button3,button4;
    EditText etN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ass_teacher);

        button1 = findViewById(R.id.btnMutu);

        //create intent
        final Intent intent = new Intent(this,sigle_type.class);
        String typeName = "Mutualism";

        intent.putExtra("TYPE_01",typeName);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                Toast toast = Toast.makeText(getApplicationContext(),"Navigating to Mutualism",Toast.LENGTH_LONG);
                toast.show();
            }
        });

        button2 = findViewById(R.id.btnCom);

        //create intent
        final Intent intent2 = new Intent(this,sigle_type.class);
        String typeName2 = "Commensolism";

        intent2.putExtra("TYPE_01",typeName2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
                Toast toast = Toast.makeText(getApplicationContext(),"Navigating to Commensolism",Toast.LENGTH_LONG);
                toast.show();
            }
        });

        button3 = findViewById(R.id.btnPar);

        //create intent
        final Intent intent3 = new Intent(this,sigle_type.class);
        String typeName3 = "Parasitism";

        intent3.putExtra("TYPE_01",typeName3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent3);
                Toast toast = Toast.makeText(getApplicationContext(),"Navigating to Parasitism",Toast.LENGTH_LONG);
                toast.show();
            }
        });

        button4 = findViewById(R.id.btnTiti);

        //create intent
        final Intent intent4 = new Intent(this,sigle_type.class);
        String typeName4 = "Competition";

        intent4.putExtra("TYPE_01",typeName4);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent4);
                Toast toast = Toast.makeText(getApplicationContext(),"Navigating to Competition",Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

   /* public void goToTypeAct(View view){
        Intent i = new Intent(this,sigle_type.class);

        etN = findViewById(R.id.editTextweek);

        int week = Integer.parseInt(etN.getText().toString());
        i.putExtra("WEEK",week);
        startActivity(i);
    }*/
}
