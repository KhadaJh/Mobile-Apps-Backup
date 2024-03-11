package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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
        TextView EcuRes = findViewById(R.id.FactoCalc);
        String ini = "";

        EcuRes.setText(ini);

    }
    public void BorrarUno (View view){
        TextView ecuRes = findViewById(R.id.FactoCalc);
        String textoActual = ecuRes.getText().toString();

        if (textoActual.length() > 0) {
            String nuevoTexto = textoActual.substring(0, textoActual.length() - 1);

            ecuRes.setText(nuevoTexto);
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
        TextView ecuRes = findViewById(R.id.FactoCalc);
        String EcuString = ecuRes.getText().toString();

        try {

            Expression ecuacion = new ExpressionBuilder(EcuString).build();
            Double Result = ecuacion.evaluate();

            long rounded = Math.round(Result);

            long resfact = factcalc(rounded);

            ecuRes.setText(String.valueOf(resfact));


        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Error al calcular la expresión", Toast.LENGTH_SHORT).show();
        }

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            TextView EcuRes = findViewById(R.id.FactoCalc);


            Button tapped = (Button) v;
            String textoboton = tapped.getText().toString();

            String ActualText = EcuRes.getText().toString();

            String agregado = ActualText + textoboton;

            EcuRes.setText(agregado);
        }
    };
}