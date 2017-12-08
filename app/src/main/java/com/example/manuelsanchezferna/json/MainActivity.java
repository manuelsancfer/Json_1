package com.example.manuelsanchezferna.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button top = (Button) findViewById(R.id.top_videos);
        Button vid = (Button) findViewById(R.id.videos);

        textView = (TextView)findViewById(R.id.editText);
    }


    /**public void consulta(View view) {
        makeJsonRequest("https://unguled-flash.000webhostapp.com/Consultas/consulta.php");
    }

    public void c_video(View view){ //Consulta videos
        makeJsonRequest("https://unguled-flash.000webhostapp.com/Consultas/consultavideos.php");
    }
    public void c_topvideo(View view){ //Consulta top videos
        makeJsonRequest("https://unguled-flash.000webhostapp.com/Consultas/topvideos.php");
    }

    private void makeJsonRequest(String url) {
        Log.i("app","makeJsonRequest");

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("app","makeJsonRequest: onResponse");

                        Gson gson = new Gson();
                        Log.i("app","makeJsonRequest: onResponse - newGson");

                        String json = gson.toJson(response);
                        ConsultaResponse c = gson.fromJson(json,ConsultaResponse.class);
                        Log.i("app","makeJsonRequest: onResponse - gson.fromJson");

                        textView.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                         textView.setText("Error: " + error.toString());
                        Log.i("app","makeJsonObj: onErrorResponse");
                    }
                });
        Volley.newRequestQueue(this).add(jsObjRequest);
    }**/
}
