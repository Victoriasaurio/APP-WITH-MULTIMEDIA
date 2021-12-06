package com.example.mediaplayer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Temperature extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void showActualTemperature(View view) {
        getActualTemperature("http://192.168.100.220:3800/v1/temperature/");
    }

    public void getActualTemperature(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TextView actualTemp = findViewById(R.id.actualTempText);
                actualTemp.setText("Temperatura: " + response.substring(10,18));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        requestQueue.add((stringRequest));
    }

    public void activateRele(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.100.220:3800/v1/temperature/";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Temperature.this, "Relevador activado", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("command", "Activate");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void deactivateRele(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.100.220:3800/v1/temperature/";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Temperature.this, "Relevador Desactivado", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("command", "Deactivate");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
