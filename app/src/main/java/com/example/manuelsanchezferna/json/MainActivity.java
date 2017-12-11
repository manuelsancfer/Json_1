package com.example.manuelsanchezferna.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import com.android.volley.toolbox.Volley;
import com.google.gson.stream.JsonReader;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private View linia_top;
    private View linia_vid;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button top = (Button) findViewById(R.id.top_videos);
        Button vid = (Button) findViewById(R.id.videos);

        /*View rectangle = (View) findViewById(R.id.rectangle);
        ImageView search = (ImageView) findViewById(R.id.search);
        ImageView message = (ImageView) findViewById(R.id.mensajes);
        linia_top = (View) findViewById(R.id.linia_top);
        linia_vid = (View) findViewById(R.id.linia_vid);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);*/

        textView = (TextView)findViewById(R.id.editText);

        //c_video(textView);
    }


    public void consulta(View view) {
        makeJsonRequest("https://unguled-flash.000webhostapp.com/Consultas/consulta.php");
    }

    public void c_video(View view){ //Consulta videos
        makeJsonRequest("https://unguled-flash.000webhostapp.com/Consultas/consultavideos.php");
    }
    public void c_topvideo(View view){ //Consulta top videos
        makeJsonRequest("https://unguled-flash.000webhostapp.com/Consultas/topvideos.php");
    }

    public void c_canciones(View view){ //Consulta canciones
        makeJsonRequest("https://unguled-flash.000webhostapp.com/Consultas/canciones.php");
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

                        ConsultaUsers c = gson.fromJson(response.toString(),ConsultaUsers.class);

                        if(c.getSuccess()==1) {
                            textView.setText(c.getUsers().get(0).getName());
                            Toast.makeText(getApplicationContext(),
                                    "funciona", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    response.toString(), Toast.LENGTH_LONG).show();

                        }
                   }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Error: " + error.toString());
                        Log.i("app","makeJsonObj: onErrorResponse");
                    }
                });
        Volley.newRequestQueue(this).add(jsObjRequest);
    }
}
