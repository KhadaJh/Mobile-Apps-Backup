package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button boton1 = findViewById(R.id.button1);
        Button boton2 = findViewById(R.id.button2);
        Button boton3 = findViewById(R.id.button3);
        Button boton4 = findViewById(R.id.button4);
        Button boton5 = findViewById(R.id.button5);
        Button boton6 = findViewById(R.id.button6);
        Button boton7 = findViewById(R.id.button7);
        Button boton8 = findViewById(R.id.button8);
        Button boton9 = findViewById(R.id.button9);
        Button boton10 = findViewById(R.id.button10);

        boton1.setOnClickListener(listener);
        boton2.setOnClickListener(listener);
        boton3.setOnClickListener(listener);
        boton4.setOnClickListener(listener);
        boton5.setOnClickListener(listener);
        boton6.setOnClickListener(listener);
        boton7.setOnClickListener(listener);
        boton8.setOnClickListener(listener);
        boton9.setOnClickListener(listener);
        boton10.setOnClickListener(listener);

        Button retroceso = findViewById(R.id.backb);

        retroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void BorrarTodo (View view){
        TextView EcuRes = findViewById(R.id.FiboCalc);
        String ini = "";

        EcuRes.setText(ini);

    }
    public void BorrarUno (View view){
        TextView ecuRes = findViewById(R.id.FiboCalc);
        String textoActual = ecuRes.getText().toString();

        if (textoActual.length() > 0) {
            String nuevoTexto = textoActual.substring(0, textoActual.length() - 1);

            ecuRes.setText(nuevoTexto);
        }
    }
    public long fibocalc (long rounded){
        if (rounded == 0){
            return 0;
        } if (rounded == 1){
            return 1;
        } if (rounded > 1) {
            return fibocalc(rounded-1) + fibocalc(rounded-2);
        } else {
            return -1;
        }

    }
    public void fibonacci (View view) {
        TextView ecuRes = findViewById(R.id.FiboCalc);
        String EcuString = ecuRes.getText().toString();
        int rounded = Integer.valueOf(EcuString);

        long res1 = fibocalc(rounded);
        double Result = (double) res1;

        try {

            Intent c = new Intent(MainActivity3.this, MainActivity5.class);
            Bundle res = new Bundle();
            res.putSerializable("obj", Result);
            c.putExtras(res);
            startActivity(c);

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Error al calcular la expresión", Toast.LENGTH_SHORT).show();
        }
    };



    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            TextView EcuRes = findViewById(R.id.FiboCalc);


            Button tapped = (Button) v;
            String textoboton = tapped.getText().toString();

            String ActualText = EcuRes.getText().toString();

            String agregado = ActualText + textoboton;

            EcuRes.setText(agregado);
        }
    };
}