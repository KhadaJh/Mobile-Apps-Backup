package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileInputStream;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity4 extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private SharedPreferences sharedPreferences;

    EditText email, password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        TextView create = findViewById(R.id.textView3);
        Button login = findViewById(R.id.button2);

        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String EmailUser = email.getText().toString().trim();
                String PasswUser = password.getText().toString().trim();

                if (EmailUser.isEmpty() || PasswUser.isEmpty()){

                    Toast.makeText(MainActivity4.this, "Rellene los campos necesarios.", Toast.LENGTH_SHORT).show();
                }else{

                    loginUser(EmailUser,PasswUser);
                }

            }
        });
        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent c = new Intent(MainActivity4.this, MainActivity5.class);
                startActivity(c);
            }
        });

        sharedPreferences = getSharedPreferences("permissions", MODE_PRIVATE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permisos de ubicación concedidos", Toast.LENGTH_SHORT).show();
        } else {
            requestLocationPermission();
        }

    }


    private void loginUser(String EmailUser, String PasswUser) {
        mAuth.signInWithEmailAndPassword(EmailUser, PasswUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(MainActivity4.this, MainActivity.class));
                    Toast.makeText(MainActivity4.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(MainActivity4.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(MainActivity4.this, "Error al iniciar sesión.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if (!sharedPreferences.getBoolean("location_permission_granted", false)) {
            requestLocationPermission();
        }

        if (user != null){
            startActivity(new Intent(MainActivity4.this, MainActivity.class));
            finish();

        }
    }


    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permisos de Ubicación")
                    .setMessage("Esta aplicación necesita acceder a su ubicación para proporcionar funcionalidades relacionadas con la ubicación.")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions(MainActivity4.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "Permisos de ubicación concedidos", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("location_permission_granted", true);
                editor.apply();
            } else {

                Toast.makeText(this, "Permisos de ubicación denegados", Toast.LENGTH_SHORT).show();
            }
        }
    }


}