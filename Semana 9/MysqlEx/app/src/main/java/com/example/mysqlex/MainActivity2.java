package com.example.mysqlex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView Bienvenida = findViewById(R.id.textView2);

        String ChangeBienvenida;

        Bundle extras = getIntent().getExtras();



        if (extras != null){

            String nombre = extras.getString("nombre");
            ChangeBienvenida = "Bienvenido de vuelta, "+ nombre + "." ;
            Bienvenida.setText(ChangeBienvenida);
        } else {

            ChangeBienvenida = "Como llegaste aqui? ";
            Bienvenida.setText(ChangeBienvenida);
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