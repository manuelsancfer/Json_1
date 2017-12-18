package com.example.manuelsanchezferna.json;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private String url = "https://unguled-flash.000webhostapp.com/Consultas/consultavideos.php";
    private ImageView fotoperfil;
    private TextView username, email, gustos, amigos, siguiendo;
    private VideoView favoritos;
    private int[] videos = {R.id.vid0, R.id.vid1, R.id.vid2, R.id.vid3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_usuari);
        //makeJsonVideo(url);

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
                        Toast.makeText(getApplicationContext(),
                                response.toString(), Toast.LENGTH_LONG).show();

                        if(c.getSuccess()==1) {

                            //LISTA DE 4 VIDEOS

                            favoritos = (VideoView) findViewById(R.id.vid0);
                            Uri v0 = Uri.parse(c.getVideos().get(0).getUrl());
                            String score0 = Float.toString(
                                    c.getVideos().get(0).getScore());

                            /*TextView vid0_name = (TextView) findViewById(R.id.vid0_name);
                            TextView vid0_art = (TextView) findViewById(R.id.vid0_art);

                            vid0_name.setText(c.getVideos().get(0).getTittle());
                            vid0_art.setText(c.getVideos().get(0).getName());*/

                            favoritos.setVideoURI(v0);


                            favoritos = (VideoView) findViewById(R.id.vid1);
                            Uri v1 = Uri.parse(c.getVideos().get(1).getUrl());
                            String score1 = Float.toString(
                                    c.getVideos().get(1).getScore());

                            /*TextView vid1_name = (TextView) findViewById(R.id.vid1_name);
                            TextView vid1_art = (TextView) findViewById(R.id.vid1_art);

                            vid_name.setText(c.getVideos().get(1).getTittle());
                            vid1_art.setText(c.getVideos().get(1).getName());*/


                            favoritos.setVideoURI(v1);

                            favoritos = (VideoView) findViewById(R.id.vid2);
                            Uri v2 = Uri.parse(c.getVideos().get(2).getUrl());
                            String score2 = Float.toString(
                                    c.getVideos().get(2).getScore());

                            /*TextView vid2_name = (TextView) findViewById(R.id.vid2_name);
                            TextView vid2_art = (TextView) findViewById(R.id.vid2_art);

                            vid1_name.setText(c.getVideos().get(2).getTittle());
                            vid1_art.setText(c.getVideos().get(2).getName());*/

                            favoritos.setVideoURI(v2);


                            favoritos = (VideoView) findViewById(R.id.vid3);
                            Uri v3 = Uri.parse(c.getVideos().get(3).getUrl());
                            String score3 = Float.toString(
                                    c.getVideos().get(3).getScore());


                            favoritos.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.setLooping(true);
                                    favoritos.start();
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
                        Log.i("app","makeJsonObj: onErrorResponse");
                    }
                });

        Volley.newRequestQueue(this).add(jsObjRequest);

    }


}



