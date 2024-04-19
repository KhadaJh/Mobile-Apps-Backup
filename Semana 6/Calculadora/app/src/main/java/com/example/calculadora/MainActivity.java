package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Context;
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
        Button boton16 = findViewById(R.id.button16);
        Button boton17 = findViewById(R.id.button17);


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
        boton16.setOnClickListener(listener);
        boton17.setOnClickListener(listener);


    }

    public void BorrarTodo (View view){
        TextView EcuRes = findViewById(R.id.EcuRes);
        String ini = "";

        EcuRes.setText(ini);

    }
    public void BorrarUno (View view){
        TextView ecuRes = findViewById(R.id.EcuRes);
        String textoActual = ecuRes.getText().toString();

        if (textoActual.length() > 0) {
            String nuevoTexto = textoActual.substring(0, textoActual.length() - 1);

            ecuRes.setText(nuevoTexto);
        }
    }
    public void calcular (View view){
        TextView ecuRes = findViewById(R.id.EcuRes);
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
    public long factcalc (long rounded){
        if (rounded == 1){
            return 1;
        } if (rounded > 1) {
            return rounded * factcalc(rounded - 1);
        } else {
            return -1;
        }

    }
    public void factorial (View view) {
        TextView ecuRes = findViewById(R.id.EcuRes);
        String EcuString = ecuRes.getText().toString();

        try {

            Expression ecuacion = new ExpressionBuilder (EcuString).build();
            Double Result = ecuacion.evaluate();

            long rounded = Math.round(Result);

            long resfact = factcalc(rounded);

            if (resfact != -1) {
                ecuRes.setText(String.valueOf(resfact));
            } else {
                ecuRes.setText("Error");
            }

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Error al calcular la expresión", Toast.LENGTH_SHORT).show();
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
        TextView ecuRes = findViewById(R.id.EcuRes);
        String EcuString = ecuRes.getText().toString();

        try {

            Expression ecuacion = new ExpressionBuilder (EcuString).build();
            Double Result = ecuacion.evaluate();

            long rounded = Math.round(Result);

            long resfibo = fibocalc(rounded);

            if (resfibo != -1) {
                ecuRes.setText(String.valueOf(resfibo));
            } else {
                ecuRes.setText("Error");
            }

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Error al calcular la expresión", Toast.LENGTH_SHORT).show();
        }

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            TextView EcuRes = findViewById(R.id.EcuRes);
            String err = "Error";
            String ini = "";

            if (EcuRes.equals(err)){

                EcuRes.setText(ini);

            }

            Button tapped = (Button) v;
            String textoboton = tapped.getText().toString();

            String ActualText = EcuRes.getText().toString();

            String agregado = ActualText + textoboton;

            EcuRes.setText(agregado);
        }
    };


}