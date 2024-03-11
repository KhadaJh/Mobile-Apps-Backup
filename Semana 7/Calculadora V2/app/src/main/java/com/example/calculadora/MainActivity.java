package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button boton = findViewById(R.id.button);
        Button boton2 = findViewById(R.id.button2);
        Button boton3 = findViewById(R.id.button3);
        Button boton5 = findViewById(R.id.button5);
        Button boton6 = findViewById(R.id.button6);
        Button boton7 = findViewById(R.id.button7);
        Button boton8 = findViewById(R.id.button8);
        Button boton9 = findViewById(R.id.button9);
        Button boton10 = findViewById(R.id.button10);
        Button boton11 = findViewById(R.id.button11);
        Button boton12 = findViewById(R.id.button12);
        Button boton13 = findViewById(R.id.button13);
        Button boton14 = findViewById(R.id.button14);
        Button boton15 = findViewById(R.id.button15);
        Button boton16 = findViewById(R.id.button16);
        Button boton17 = findViewById(R.id.button17);
        Button boton18 = findViewById(R.id.button18);

        Button cambiarmeme = findViewById(R.id.button20);
        Button cambiarfibo = findViewById(R.id.button21);
        Button cambiarfacto = findViewById(R.id.button22);

        boton.setOnClickListener(listener);
        boton2.setOnClickListener(listener);
        boton3.setOnClickListener(listener);
        boton5.setOnClickListener(listener);
        boton6.setOnClickListener(listener);
        boton7.setOnClickListener(listener);
        boton8.setOnClickListener(listener);
        boton9.setOnClickListener(listener);
        boton10.setOnClickListener(listener);
        boton11.setOnClickListener(listener);
        boton12.setOnClickListener(listener);
        boton13.setOnClickListener(listener);
        boton14.setOnClickListener(listener);
        boton15.setOnClickListener(listener);
        boton16.setOnClickListener(listener);
        boton17.setOnClickListener(listener);
        boton18.setOnClickListener(listener);

        cambiarfibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent c = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(c);

                } catch (Exception e){

                    Toast.makeText(getApplicationContext(), "Error al abrir la calculadora de factoriales", Toast.LENGTH_SHORT).show();


                }
            }
        });

        cambiarfacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent c = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(c);

                } catch (Exception e){

                    Toast.makeText(getApplicationContext(), "Error al abrir la calculadora de posicion en fibonacci", Toast.LENGTH_SHORT).show();


                }
            }
        });

        cambiarmeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent c = new Intent(MainActivity.this, MainActivity4.class);
                    startActivity(c);

                } catch (Exception e){

                    Toast.makeText(getApplicationContext(), "Error al abrir el meme", Toast.LENGTH_SHORT).show();


                }
            }
        });

    }


    public void BorrarTodo (View view){
        TextView EcuRes = findViewById(R.id.ecuRes);
        String ini = "";

        EcuRes.setText(ini);

    }
    public void BorrarUno (View view){
        TextView ecuRes = findViewById(R.id.ecuRes);
        String textoActual = ecuRes.getText().toString();

        if (textoActual.length() > 0) {
            String nuevoTexto = textoActual.substring(0, textoActual.length() - 1);

            ecuRes.setText(nuevoTexto);
        }
    }
    public void calcular (View view){
        TextView ecuRes = findViewById(R.id.ecuRes);
        String EcuString = ecuRes.getText().toString();

        try {

            Expression ecuacion = new ExpressionBuilder (EcuString).build();
            Double Result = ecuacion.evaluate();

            String fracc;

            if (Result % 1 == 0) {
                fracc = String.format("%.0f",Result);
                ecuRes.setText(String.valueOf(fracc));
            } else {
                ecuRes.setText(String.valueOf(Result));
            }
        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Error al calcular la expresión", Toast.LENGTH_SHORT).show();
        }
    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            TextView EcuRes = findViewById(R.id.ecuRes);


            Button tapped = (Button) v;
            String textoboton = tapped.getText().toString();

            String ActualText = EcuRes.getText().toString();

            String agregado = ActualText + textoboton;

            EcuRes.setText(agregado);
        }
    };


}