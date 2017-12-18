package com.example.manuelsanchezferna.json;

import android.content.Intent;
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
        Button button = (Button) findViewById(R.id.button);


        textView = (TextView)findViewById(R.id.editText);

        //el boto serà el de la llista desplegable
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //makeJsonRequest();
                Perfil(view);
            }
        });

        makeJsonVideo("https://unguled-flash.000webhostapp.com/Consultas/consultavideos.php");
    }


    public void Perfil(View view){
        Intent intent = new Intent(this,Perfil.class);
        startActivity(intent);
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

                            //LISTA DE 10 VIDEOS

                            videoView = (VideoView) findViewById(R.id.vid0);
                            Uri v0 = Uri.parse(c.getVideos().get(0).getUrl());
                            String score0 = Float.toString(
                                        c.getVideos().get(0).getScore());
                                textView.setText(score0);   //para meter el score

                            TextView vid0_name = (TextView) findViewById(R.id.vid0_name);
                            TextView vid0_art = (TextView) findViewById(R.id.vid0_art);

                            vid0_name.setText(c.getVideos().get(0).getTittle());
                            vid0_art.setText(c.getVideos().get(0).getName());

                            videoView.setVideoURI(v0);


                            videoView = (VideoView) findViewById(R.id.vid1);
                            Uri v1 = Uri.parse(c.getVideos().get(1).getUrl());
                            String score1 = Float.toString(
                                    c.getVideos().get(1).getScore());
                            textView.setText(score1);

                            /*TextView vid1_name = (TextView) findViewById(R.id.vid1_name);
                            TextView vid1_art = (TextView) findViewById(R.id.vid1_art);

                            vid_name.setText(c.getVideos().get(1).getTittle());
                            vid1_art.setText(c.getVideos().get(1).getName());*/


                            videoView.setVideoURI(v1);

                            videoView = (VideoView) findViewById(R.id.vid2);
                            Uri v2 = Uri.parse(c.getVideos().get(2).getUrl());
                            String score2 = Float.toString(
                                    c.getVideos().get(2).getScore());
                            textView.setText(score2);

                            /*TextView vid2_name = (TextView) findViewById(R.id.vid2_name);
                            TextView vid2_art = (TextView) findViewById(R.id.vid2_art);

                            vid1_name.setText(c.getVideos().get(2).getTittle());
                            vid1_art.setText(c.getVideos().get(2).getName());*/

                            videoView.setVideoURI(v2);


                            videoView = (VideoView) findViewById(R.id.vid3);
                            Uri v3 = Uri.parse(c.getVideos().get(3).getUrl());
                            String score3 = Float.toString(
                                    c.getVideos().get(3).getScore());
                            textView.setText(score3);

                            /*TextView vid3_name = (TextView) findViewById(R.id.vid3_name);
                            TextView vid3_art = (TextView) findViewById(R.id.vid3_art);

                            vid3_name.setText(c.getVideos().get(3).getTittle());
                            vid3_art.setText(c.getVideos().get(3).getName());*/

                            videoView.setVideoURI(v3);


                            videoView = (VideoView) findViewById(R.id.vid4);
                            Uri v4 = Uri.parse(c.getVideos().get(4).getUrl());
                            String score4 = Float.toString(
                                    c.getVideos().get(4).getScore());
                            textView.setText(score4);

                            /*TextView vid4_name = (TextView) findViewById(R.id.vid4_name);
                            TextView vid4_art = (TextView) findViewById(R.id.vid4_art);

                            vid3_name.setText(c.getVideos().get(4).getTittle());
                            vid3_art.setText(c.getVideos().get(4).getName());*/

                            videoView.setVideoURI(v4);


                            videoView = (VideoView) findViewById(R.id.vid5);
                            Uri v5 = Uri.parse(c.getVideos().get(5).getUrl());
                            String score5 = Float.toString(
                                    c.getVideos().get(5).getScore());
                            textView.setText(score5);

                            /*TextView vid5_name = (TextView) findViewById(R.id.vid5_name);
                            TextView vid5_art = (TextView) findViewById(R.id.vid5_art);

                            vid3_name.setText(c.getVideos().get(5).getTittle());
                            vid3_art.setText(c.getVideos().get(5).getName());*/

                            videoView.setVideoURI(v5);


                            videoView = (VideoView) findViewById(R.id.vid6);
                            Uri v6 = Uri.parse(c.getVideos().get(6).getUrl());
                            String score6 = Float.toString(
                                    c.getVideos().get(6).getScore());
                            textView.setText(score6);

                            /*TextView vid6_name = (TextView) findViewById(R.id.vid6_name);
                            TextView vid6_art = (TextView) findViewById(R.id.vid6_art);

                            vid6_name.setText(c.getVideos().get(6).getTittle());
                            vid6_art.setText(c.getVideos().get(6).getName());*/

                            videoView.setVideoURI(v6);


                            videoView = (VideoView) findViewById(R.id.vid7);
                            Uri v7 = Uri.parse(c.getVideos().get(7).getUrl());
                            String score7 = Float.toString(
                                    c.getVideos().get(7).getScore());
                            textView.setText(score7);

                            /*TextView vid7_name = (TextView) findViewById(R.id.vid7_name);
                            TextView vid7_art = (TextView) findViewById(R.id.vid7_art);

                            vid7_name.setText(c.getVideos().get(7).getTittle());
                            vid7_art.setText(c.getVideos().get(7).getName());*/

                            videoView.setVideoURI(v7);


                            videoView = (VideoView) findViewById(R.id.vid8);
                            Uri v8 = Uri.parse(c.getVideos().get(8).getUrl());
                            String score8 = Float.toString(
                                    c.getVideos().get(8).getScore());
                            textView.setText(score8);

                            /*TextView vid8_name = (TextView) findViewById(R.id.vid8_name);
                            TextView vid8_art = (TextView) findViewById(R.id.vid8_art);

                            vid8_name.setText(c.getVideos().get(8).getTittle());
                            vid8_art.setText(c.getVideos().get(8).getName());*/

                            videoView.setVideoURI(v8);


                            videoView = (VideoView) findViewById(R.id.vid9);
                            Uri v9 = Uri.parse(c.getVideos().get(9).getUrl());
                            String score9 = Float.toString(
                                    c.getVideos().get(9).getScore());
                            textView.setText(score9);

                            /*TextView vid9_name = (TextView) findViewById(R.id.vid9_name);
                            TextView vid9_art = (TextView) findViewById(R.id.vid9_art);

                            vid9_name.setText(c.getVideos().get(9).getTittle());
                            vid9_art.setText(c.getVideos().get(9).getName());*/

                            videoView.setVideoURI(v9);



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
