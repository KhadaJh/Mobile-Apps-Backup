package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Parcel;
import android.os.Parcelable;


import java.util.LinkedList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView subtotalV = findViewById(R.id.subtotal);
        TextView totalV = findViewById(R.id.total);
        TextView ivaV = findViewById(R.id.iva);

        int subtotal = 0;

        try {

            Intent c = getIntent();
            Bundle extras = c.getExtras();
            if (extras != null) {

                List<String[]> productList = (List<String[]>) extras.getSerializable("test");

                if (productList != null) {

                    for (int i = 0; i <= 10; i++) {

                        String[] productInfo = productList.get(i);

                        int prodnameId = getResources().getIdentifier("prodname" + (i+1), "id", getPackageName());
                        int prodpriceId = getResources().getIdentifier("prodprice" + (i+1), "id", getPackageName());
                        int prodcantId = getResources().getIdentifier("cant" + (i+1), "id", getPackageName());
                        int tpunitId = getResources().getIdentifier("tpunits" + (i+1), "id", getPackageName());
                        int rowid = getResources().getIdentifier("row" + (i+1), "id", getPackageName());


                        TextView nameView = findViewById(prodnameId);
                        TextView priceView = findViewById(prodpriceId);
                        TextView cantView = findViewById(prodcantId);
                        TextView tpunitView = findViewById(tpunitId);
                        TableRow rowV = findViewById(rowid);

                        String name = productInfo[0];
                        String price = productInfo[1];
                        String cantString = productInfo[2];

                        int cant = Integer.parseInt(cantString);

                        if (cant == 0) {

                            rowV.setVisibility(View.GONE);
                        } else {

                            nameView.setText(name);
                            priceView.setText(price);
                            cantView.setText(cantString);

                            String r_pricetag = price.replace("$", "");

                            int precio = Integer.parseInt(r_pricetag);

                            int tpunit = precio * cant;

                            subtotal = subtotal + tpunit;

                            String tpunitString = "$" + tpunit;

                            tpunitView.setText(tpunitString);

                        }
                    }

                    double iva = subtotal * 0.19;
                    int ivarounded = (int) Math.round(iva);
                    int total = ivarounded + subtotal;

                    String subtotalString = "$" + subtotal;
                    String ivaString = "$" + ivarounded;
                    String totalString = "$" + total;


                    subtotalV.setText(subtotalString);
                    ivaV.setText(ivaString);
                    totalV.setText(totalString);
                }
            }
        } catch (Exception e){

            e.printStackTrace();
            Toast.makeText(MainActivity3.this, "Problema con el paquete.", Toast.LENGTH_SHORT).show();
        }







        Button backButton = findViewById(R.id.backb);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}