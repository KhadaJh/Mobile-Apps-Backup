package com.example.myapplication.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MainActivity2;
import com.example.myapplication.MainActivity3;
import com.example.myapplication.R;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String baseURL = "http://192.168.0.9/FastPricer/Obtain.php";
        String rowurl = "http://192.168.0.9/FastPricer/RowCount.php";

        try {

            JsonObjectRequest rowcountRequest = new JsonObjectRequest
                    (Request.Method.GET, rowurl, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {

                                String rowcount = response.getString("rowcount");
                                int rowint = Integer.parseInt(rowcount);

                                for (int i = 1 ; i <= 11; i++){

                                    int rowid = getResources().getIdentifier("row" + (i), "id", requireContext().getPackageName());
                                    TableRow rowView = rootView.findViewById(rowid);

                                    if (i > rowint) {

                                        rowView.setVisibility(View.GONE);
                                    }
                                }
                                try {
                                    for (int i = 1; i <= rowint; i++) {
                                        String url = baseURL + "?id=" + i;
                                        int prodnameId = getResources().getIdentifier("prodname" + i, "id", requireContext().getPackageName());
                                        int prodpriceId = getResources().getIdentifier("prodprice" + i, "id", requireContext().getPackageName());
                                        int imgId = getResources().getIdentifier("img" + i, "id", requireContext().getPackageName());

                                        if (prodnameId != 0 && prodpriceId != 0 && imgId != 0) {
                                            TextView prodnameView = rootView.findViewById(prodnameId);
                                            TextView prodpriceView = rootView.findViewById(prodpriceId);
                                            ImageView imgView = rootView.findViewById(imgId);

                                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                                        @Override
                                                        public void onResponse(JSONObject response) {
                                                            try {
                                                                String nombre = response.getString("nombre");
                                                                String precio = response.getString("precio");
                                                                String imagenBase64 = response.getString("img");
                                                                byte[] decodedString = Base64.decode(imagenBase64, Base64.DEFAULT);
                                                                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                                                prodnameView.setText(nombre);
                                                                prodpriceView.setText(precio);
                                                                imgView.setImageBitmap(decodedBitmap);
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    }, new Response.ErrorListener() {
                                                        @Override
                                                        public void onErrorResponse(VolleyError error) {
                                                            error.printStackTrace();
                                                            Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                            RequestQueue queue = Volley.newRequestQueue(requireContext());
                                            queue.add(jsonObjectRequest);
                                        }
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(requireContext(), "Error reemplazando info del menu", Toast.LENGTH_SHORT).show();
                                }



                            } catch (Exception e) {

                                e.printStackTrace();
                                Toast.makeText(requireContext(), "Error al manejar los id", Toast.LENGTH_SHORT).show();}}
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Toast.makeText(requireContext(), "Error al obtener la cantidad  de filas", Toast.LENGTH_SHORT).show();
                        }
                    });

            RequestQueue queue = Volley.newRequestQueue(requireContext());
            queue.add(rowcountRequest);
        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(requireContext(), "Error al iniciar el JSONobject request", Toast.LENGTH_SHORT).show();
        }







            Button plusbut1 = rootView.findViewById(R.id.plusbut1);
            Button plusbut2 = rootView.findViewById(R.id.plusbut2);
            Button plusbut3 = rootView.findViewById(R.id.plusbut3);
            Button plusbut4 = rootView.findViewById(R.id.plusbut4);
            Button plusbut5 = rootView.findViewById(R.id.plusbut5);
            Button plusbut6 = rootView.findViewById(R.id.plusbut6);
            Button plusbut7 = rootView.findViewById(R.id.plusbut7);
            Button plusbut8 = rootView.findViewById(R.id.plusbut8);
            Button plusbut9 = rootView.findViewById(R.id.plusbut9);
            Button plusbut10 = rootView.findViewById(R.id.plusbut10);
            Button plusbut11 = rootView.findViewById(R.id.plusbut11);

            plusbut1.setOnClickListener(sumar);
            plusbut2.setOnClickListener(sumar);
            plusbut3.setOnClickListener(sumar);
            plusbut4.setOnClickListener(sumar);
            plusbut5.setOnClickListener(sumar);
            plusbut6.setOnClickListener(sumar);
            plusbut7.setOnClickListener(sumar);
            plusbut8.setOnClickListener(sumar);
            plusbut9.setOnClickListener(sumar);
            plusbut10.setOnClickListener(sumar);
            plusbut11.setOnClickListener(sumar);

            Button minbut1 = rootView.findViewById(R.id.minbut1);
            Button minbut2 = rootView.findViewById(R.id.minbut2);
            Button minbut3 = rootView.findViewById(R.id.minbut3);
            Button minbut4 = rootView.findViewById(R.id.minbut4);
            Button minbut5 = rootView.findViewById(R.id.minbut5);
            Button minbut6 = rootView.findViewById(R.id.minbut6);
            Button minbut7 = rootView.findViewById(R.id.minbut7);
            Button minbut8 = rootView.findViewById(R.id.minbut8);
            Button minbut9 = rootView.findViewById(R.id.minbut9);
            Button minbut10 = rootView.findViewById(R.id.minbut10);
            Button minbut11 = rootView.findViewById(R.id.minbut11);

            minbut1.setOnClickListener(restar);
            minbut2.setOnClickListener(restar);
            minbut3.setOnClickListener(restar);
            minbut4.setOnClickListener(restar);
            minbut5.setOnClickListener(restar);
            minbut6.setOnClickListener(restar);
            minbut7.setOnClickListener(restar);
            minbut8.setOnClickListener(restar);
            minbut9.setOnClickListener(restar);
            minbut10.setOnClickListener(restar);
            minbut11.setOnClickListener(restar);

            Button descbut1 = rootView.findViewById(R.id.descbut1);
            Button descbut2 = rootView.findViewById(R.id.descbut2);
            Button descbut3 = rootView.findViewById(R.id.descbut3);
            Button descbut4 = rootView.findViewById(R.id.descbut4);
            Button descbut5 = rootView.findViewById(R.id.descbut5);
            Button descbut6 = rootView.findViewById(R.id.descbut6);
            Button descbut7 = rootView.findViewById(R.id.descbut7);
            Button descbut8 = rootView.findViewById(R.id.descbut8);
            Button descbut9 = rootView.findViewById(R.id.descbut9);
            Button descbut10 = rootView.findViewById(R.id.descbut10);
            Button descbut11 = rootView.findViewById(R.id.descbut11);

            descbut1.setOnClickListener(desc);
            descbut2.setOnClickListener(desc);
            descbut3.setOnClickListener(desc);
            descbut4.setOnClickListener(desc);
            descbut5.setOnClickListener(desc);
            descbut6.setOnClickListener(desc);
            descbut7.setOnClickListener(desc);
            descbut8.setOnClickListener(desc);
            descbut9.setOnClickListener(desc);
            descbut10.setOnClickListener(desc);
            descbut11.setOnClickListener(desc);

            Button cart = rootView.findViewById(R.id.cartbut);
            cart.setOnClickListener(carrito);

    }





    View.OnClickListener sumar = new View.OnClickListener(){
        @Override
        public void onClick (View v) {

            int buttonid = v.getId();

            String resourceName = getResources().getResourceEntryName(buttonid);
            String[] parts = resourceName.split("plusbut");
            String lastPart = parts[parts.length - 1];
            int idNumber = Integer.parseInt(lastPart);

            int cantId = getResources().getIdentifier("cant" + idNumber, "id", getActivity().getPackageName());

            TextView cant = rootView.findViewById(cantId);

            int cantidad = Integer.parseInt(cant.getText().toString());

            cantidad++;

            cant.setText(String.valueOf((cantidad)));

        }
    };

    View.OnClickListener restar = new View.OnClickListener(){
        @Override
        public void onClick (View v) {

            int buttonid = v.getId();

            String resourceName = getResources().getResourceEntryName(buttonid);
            String[] parts = resourceName.split("minbut");
            String lastPart = parts[parts.length - 1];
            int idNumber = Integer.parseInt(lastPart);

            int cantId = getResources().getIdentifier("cant" + idNumber, "id", getActivity().getPackageName());

            TextView cant = rootView.findViewById(cantId);

            int cantidad = Integer.parseInt(cant.getText().toString());

            if (cantidad > 0) {
                cantidad--;

                cant.setText(String.valueOf((cantidad)));

            } else{

            }
        }
    };


    View.OnClickListener carrito = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {

                Intent c = new Intent(getActivity(), MainActivity3.class);
                Bundle car = new Bundle();

                List<String[]> productList = new ArrayList<>();

                for (int i = 0; i <= 10; i++) {
                    int prodnameId = getResources().getIdentifier("prodname" + (i + 1), "id", requireContext().getPackageName());
                    int prodpriceId = getResources().getIdentifier("prodprice" + (i + 1), "id", requireContext().getPackageName());
                    int prodcantId = getResources().getIdentifier("cant" + (i + 1), "id", requireContext().getPackageName());

                    TextView nameView = rootView.findViewById(prodnameId);
                    TextView priceView = rootView.findViewById(prodpriceId);
                    TextView cantView = rootView.findViewById(prodcantId);

                    String name = nameView.getText().toString();
                    String price = priceView.getText().toString();
                    String cant = cantView.getText().toString();


                    if (!name.isEmpty() && !price.isEmpty() && !cant.isEmpty()) {
                        String[] productInfo = {name, price, cant};
                        productList.add(productInfo);
                    }
                }

                car.putSerializable("test", (Serializable) productList);

                c.putExtras(car);

                startActivity(c);

            } catch (Exception e){

                e.printStackTrace();
                Toast.makeText(requireContext(), "Problema con el bundle", Toast.LENGTH_SHORT).show();

            }
        }
    };
    View.OnClickListener desc = new View.OnClickListener(){
        @Override
        public void onClick (View v) {

            String baseURL = "http://192.168.0.9/FastPricer/Obtain.php";

            int buttonid = v.getId();

            int idNumber = Character.getNumericValue(getResources().getResourceEntryName(buttonid).charAt(getResources().getResourceEntryName(buttonid).length() - 1));

            String url = baseURL + "?id=" + idNumber;

            try {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String nombre = response.getString("nombre");
                                    String descripcion = response.getString("descri");
                                    String otherimg = response.getString("otherimg");

                                    Intent c = new Intent(getActivity(), MainActivity2.class);

                                    Bundle H = new Bundle();

                                    H.putString("nombre", nombre);
                                    H.putString("desc", descripcion);
                                    H.putString("oimg", otherimg);

                                    c.putExtras(H);

                                    startActivity(c);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(requireContext(), "Error al pasar a la nueva actividad", Toast.LENGTH_SHORT).show();

                                }
                            }
                            }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
                            }
                        });

                RequestQueue queue = Volley.newRequestQueue(requireContext());
                queue.add(jsonObjectRequest);

            } catch (Exception e){

                e.printStackTrace();
            }


        }
    };

}




