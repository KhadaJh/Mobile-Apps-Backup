 package com.example.mysqlex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

 public class MainActivity extends AppCompatActivity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         Button cambiar;
         String baseURL = "http://192.168.0.3/obtener/obtain.php";

         cambiar = findViewById(R.id.button);

         cambiar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 TextView fronttext = findViewById(R.id.editTextNumber);
                 String idString = fronttext.getText().toString().trim();

                 try {
                     int id = Integer.parseInt(idString);
                     String url = baseURL + "?id=" + idString;

                     JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                             (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                 @Override
                                 public void onResponse(JSONObject response) {
                                     try {

                                         String nombre = response.getString("nombre");

                                         Intent c = new Intent(MainActivity.this, MainActivity2.class);
                                         c.putExtra("nombre", nombre);
                                         startActivity(c);

                                     } catch (Exception e) {
                                         e.printStackTrace();
                                         Toast.makeText(getApplicationContext(), "No hay variables tipo nombre para esta id.", Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             }, new Response.ErrorListener() {

                                 @Override
                                 public void onErrorResponse(VolleyError error) {

                                     Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                                 }
                             });

                     Volley.newRequestQueue(MainActivity.this).add(jsonObjectRequest);
                 } catch (NumberFormatException e) {

                     Toast.makeText(MainActivity.this, "Por favor, ingresa un número válido", Toast.LENGTH_SHORT).show();
                 }
             }
         });
     }
 }