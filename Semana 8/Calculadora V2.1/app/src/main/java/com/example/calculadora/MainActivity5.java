package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;



public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);




        TextView ResText = findViewById(R.id.Result);


        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        if (extras != null) {

            if (extras.containsKey("obj")) {

                double Result = extras.getDouble("obj");

                String fracc;

                if (Result % 1 == 0) {

                    fracc = String.format("%.0f", Result);
                    ResText.setText(fracc);

                } else {

                    ResText.setText(String.valueOf(Result));

                }
            }
        }

        Button retroceso = findViewById(R.id.backb);

        retroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}