package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView otherimgView = findViewById(R.id.imageView);
        TextView nombreView = findViewById(R.id.textView);
        TextView prodDescView = findViewById(R.id.textView2);

        Bundle H = getIntent().getExtras();

        Button backButton = findViewById(R.id.button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (H != null){

            String nombre = H.getString("nombre");
            String descripcion = H.getString("desc");
            String otherimg = H.getString("oimg");

            byte[] decodedstring = Base64.decode(otherimg, Base64.DEFAULT);
            Bitmap decodedimg = BitmapFactory.decodeByteArray(decodedstring,0, decodedstring.length);

            otherimgView.setImageBitmap(decodedimg);
            nombreView.setText(nombre);
            prodDescView.setText(descripcion);
        } else{

            Toast.makeText(MainActivity2.this, "Hubo un error cargando la descripcion", Toast.LENGTH_SHORT);
        }

    }
}