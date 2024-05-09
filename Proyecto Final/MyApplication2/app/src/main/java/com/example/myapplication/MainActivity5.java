package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity5 extends AppCompatActivity {



    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        mFirestore =FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        EditText pass1 = findViewById(R.id.Pass1);
        EditText pass2 = findViewById(R.id.Pass2);
        Button regis = findViewById(R.id.button);
        Button backButton = findViewById(R.id.backb);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameUser = name.getText().toString().trim();
                String emailUser = email.getText().toString().trim();
                String pass1User = pass1.getText().toString().trim();
                String pass2User = pass2.getText().toString().trim();

                if (nameUser.isEmpty() || emailUser.isEmpty() || pass1User.isEmpty() || pass2User.isEmpty()) {

                    Toast.makeText(MainActivity5.this, "Rellene los campos necesarios.", Toast.LENGTH_SHORT).show();
                } else {

                    if (pass1User.equals(pass2User)) {

                        register(nameUser, emailUser, pass1User);
                    } else {

                        Toast.makeText(MainActivity5.this, "Las contraseñas no son iguales.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }



    private void register (String nameUser, String emailUser, String pass1User){
        mAuth.createUserWithEmailAndPassword(emailUser, pass1User).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                String id = mAuth.getCurrentUser().getUid();
                Map<String,Object> map = new HashMap<>();
                map.put("id", id);
                map.put("name", nameUser);
                map.put("email", emailUser);
                map.put("password", pass1User);

                mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        finish();
                        startActivity(new Intent(MainActivity5.this, MainActivity4.class));
                        Toast.makeText(MainActivity5.this, "Usuario registrado. Inicie sesion.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(MainActivity5.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(MainActivity5.this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });

    }

}