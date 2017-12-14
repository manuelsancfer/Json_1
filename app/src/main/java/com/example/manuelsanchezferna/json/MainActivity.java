package com.example.manuelsanchezferna.json;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.widget.Toast;
import android.widget.VideoView;

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
    private VideoView videoView;

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


        /*videoView = (VideoView) findViewById(R.id.vid1);
        Uri prueba = Uri.parse("https://unguled-flash.000webhostapp.com/Videos/Ricky_freedom.mp4");

        videoView.setVideoURI(prueba);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                videoView.start();
            }
        });*/
        makeJsonVideo("https://unguled-flash.000webhostapp.com/Consultas/consultavideos.php");
    }


    public void consulta(View view) {
        makeJsonRequest("https://unguled-flash.000webhostapp.com/Consultas/consulta.php");
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

    private void makeJsonVideo(String url){

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
                            /*for (int i=1; i<11; i++) {
       //TODO estas variables se deberan reutilizar, textView hay que cambiar ese nombre
                                textView.setText(c.getVideos().get(i).getTittle());
                                textView.setText(c.getVideos().get(i).getName());
                                textView.setText(c.getVideos().get(i).getUrl());

                                String score = Float.toString(
                                        c.getVideos().get(i).getScore());
                                textView.setText(score);

                                TextView vidi_name = (TextView) findViewById(R.id.vidi_name);
                                TextView vidi_art = (TextView) findViewById(R.id.vidi_art);

                                vidi_name.setText(c.getVideos().get(i).getTittle());
                                vidi_art.setText(c.getVideos().get(i).getName());

                                videoView = (VideoView) findViewById(R.id.vidi);
                                Uri uri = Uri.parse(c.getVideos().get(i).getUrl());

                                videoView.setVideoURI(uri);
                                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mp) {
                                        mp.setLooping(true);
                                        videoView.start();
                                }
                            });
                            }*/


                            videoView = (VideoView) findViewById(R.id.vid1);
                            Uri prueba = Uri.parse(c.getVideos().get(0).getUrl());

                            TextView vid1_name = (TextView) findViewById(R.id.vid1_name);
                            TextView vid1_art = (TextView) findViewById(R.id.vid1_art);

                            vid1_name.setText(c.getVideos().get(0).getTittle());
                            vid1_art.setText(c.getVideos().get(0).getName());


                            videoView.setVideoURI(prueba);

                                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.setLooping(true);
                                    videoView.start();
                                }
                           });
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    response.toString(), Toast.LENGTH_LONG).show();
                            Log.i("app","makeJsonRequest: onResponse - no vaaaaaaaaa");

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
