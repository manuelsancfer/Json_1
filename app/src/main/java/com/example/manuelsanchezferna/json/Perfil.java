package com.example.manuelsanchezferna.json;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Perfil extends AppCompatActivity {
    public static String  KEY_NAME = "KEY_NAME";
    public static int REQUEST_NAME = 1;



   /* private String url = "https://unguled-flash.000webhostapp.com/Consultas/consultavideos.php";
    private String url2 = "https://unguled-flash.000webhostapp.com/Consultas/consultaperfilpropio.php";
    private String url3 = "https://unguled-flash.000webhostapp.com/Consultas/ConsultaSeguidoresU.php";
    private String url4 = "https://unguled-flash.000webhostapp.com/Consultas/ConsultaAmigos.php";*/
    private ImageView fotoperfil;
    private TextView user, email, gustos, amigos, siguiendo;
    private VideoView favoritos;
    private int[] videosid = {R.id.vid0, R.id.vid1, R.id.vid2, R.id.vid3};

    VideoView videoView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_usuari);
        //makeJsonVideo(url);

        user = (TextView) findViewById(R.id.usernamee);

        String usuario = "cristina";
        //makeJsonUser("https://unguled-flash.000webhostapp.com/Consultas/consultaperfilpropio.php?user=cristina");
        makeJsonUser("https://unguled-flash.000webhostapp.com/Consultas/consultaperfilpropio.php?user=cristina");



        //makeJsonFollowers(url3);
        //makeJsonFriends(url4);

        makeJsonVideo("https://unguled-flash.000webhostapp.com/Consultas/consultavideos.php");


    }

    private void makeJsonFriends(String url4) {
    }

    private void makeJsonFollowers(String url3) {
    }

    private void makeJsonUser(String url2) {
        Log.i("Perfil","makeJsonUser");

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Perfil","onResponse");

                Gson gson = new Gson();

                ConsultaUserPropi c = gson.fromJson(response.toString(),ConsultaUserPropi.class);

                if(c.getSuccess()==1){
                    Log.i("Perfil","makeJsonRequest: onResponse - get Success");

                    //TODO conseguir que funcione el settext de user
                    // user.setText(c.getUsers().get(0).getUser());

                    Log.i("Perfil","toaster");
                }
                else{
                    Log.i("Perfil","makeJsonRequest: onResponse - NOT Success");
                    Toast.makeText(getApplicationContext(),
                                    response.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Perfil","makeJsonObj: onErrorResponse - no funciona volley");
            }
        });

        Volley.newRequestQueue(this).add(jsObjRequest);
        Log.i("Perfil","volley");
    }


    public void makeJsonVideo(String url){

        Log.i("app","makeJsonVideo");
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("app","makeJsonRequest: onResponse");

                        Gson gson = new Gson();
                        Log.i("app","makeJsonRequest: onResponse - video video");

                        ConsultaVideos c = gson.fromJson(response.toString(),ConsultaVideos.class);

                        if(c.getSuccess()==1) {
                            final Uri va = Uri.parse(c.getVideos().get(9).getUrl()); //Prueba!

                            for (int i=0; i<videosid.length;i++){

                                videoView = (VideoView) findViewById(videosid[i]);  //Falta Videoview1,2...
                                Uri v = Uri.parse(c.getVideos().get(i).getUrl());
                                Log.i("app","makeJsonRequest: onResponse - queme");


                            }
                            videoView.setOnTouchListener(new View.OnTouchListener()
                            {
                                @Override
                                public boolean onTouch(View v, MotionEvent motionEvent)
                                {
                                    if (videoView.isPlaying())
                                    {
                                        Toast.makeText(getApplicationContext(),
                                                "touch", Toast.LENGTH_LONG).show();
                                        videoView.pause();

                                        return false;
                                    }
                                    else
                                    { Toast.makeText(getApplicationContext(),
                                            "touchi", Toast.LENGTH_LONG).show();

                                        videoView.setVideoURI(va);  //prueba
                                        return false;

                                    }

                                }});


                            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    videoView.start();
                                    //videoView.stopPlayback();
                                }
                            });


                        }
                        else {
                            /*Toast.makeText(getApplicationContext(),
                                    response.toString(), Toast.LENGTH_LONG).show();*/
                            Log.i("app","makeJsonRequest: onResponse - no vaaaaaaaaa");

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Error: " + error.toString());
                        Log.i("app","makeJsonObj: onErrorResponse List2");
                    }
                });

        Volley.newRequestQueue(this).add(jsObjRequest);

    }


}



