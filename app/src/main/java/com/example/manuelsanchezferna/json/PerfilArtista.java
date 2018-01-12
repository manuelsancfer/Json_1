package com.example.manuelsanchezferna.json;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PerfilArtista extends AppCompatActivity {

    private ImageView fotoperfil;
    private TextView user, email, descripcion, numamigos, numsiguiendo,numseguidores, score;

    private RecyclerView recyclerViewMis, recyclerFav;
    private VideoInfoAdapter adapter1,adapter2;
    private List<VideoInfo> videoList = new ArrayList<VideoInfo>();
    private List<VideoInfo> videoList2 = new ArrayList<VideoInfo>();


    private String[] vidf =  new String[4];
    private String[] vidt = new String[4];
    private String[] vidv = new String[4];

    private String[] videosURLs= new String[10];
    private String[] videosURLsTop= new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_artista);

        user = (TextView) findViewById(R.id.username_artista);
        email = (TextView) findViewById(R.id.emailArtista);
        descripcion = (TextView) findViewById(R.id.descripcion);
        numamigos =(TextView) findViewById(R.id.amigos1);
        numsiguiendo = (TextView) findViewById(R.id.siguiendo1);
        numseguidores = (TextView) findViewById(R.id.seguidores);
        fotoperfil = (ImageView) findViewById(R.id.imagen_perfilArtista);
        score = (TextView) findViewById(R.id.puntuacion);

        Intent intent1 = getIntent();
        String artistName = intent1.getStringExtra("KEY_ARTISTA_NAME");

        Toast.makeText(getApplicationContext(),artistName, Toast.LENGTH_LONG).show();

       makeJsonUser("https://unguled-flash.000webhostapp.com/Consultas/consultaperfilartista.php?user="
               +artistName);
        makeVideo("https://unguled-flash.000webhostapp.com/Consultas/consultamisvideos.php?user="
            +artistName);

//        makeJsonNumAmigos("https://unguled-flash.000webhostapp.com/Consultas/ConsultaAmigos.php?user="
//                +artistName);
//        makeJsonNumSeguidos("https://unguled-flash.000webhostapp.com/Consultas/ConsultaSeguidoresU.php?user="
//                +artistName);
//        makeJsonNumSeguidores("https://unguled-flash.000webhostapp.com/Consultas/ConsultaSeguidoresU.php?user="
//                +artistName); //s'ha de fer la consulta

        //createSpinner();
    }

    private void makeVideo(String url){
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();

                        ConsultaVideos c = gson.fromJson(response.toString(),ConsultaVideos.class);
                        Log.i("holaa", "holaaa"+c.getVideos().size());

                        if(c.getSuccess()==1) {
                            for (int i = 0; i < c.getVideos().size(); i++) {
                                Log.i("holaa", "holaaa funciona2"+ c.getVideos().get(i).getUrl());
                                videosURLs[i] = c.getVideos().get(i).getUrl();
                                videoList2.add(new VideoInfo(c.getVideos().get(i).getName().toString(),
                                        c.getVideos().get(i).getTittle().toString()+" - "
                                                +c.getVideos().get(0).getScore(),
                                        Uri.parse(videosURLs[i%videosURLs.length])));
                            }

                            misvideosRecyclerVid();
                        }
                        else {

                            Toast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.i_videos),
                                    Toast.LENGTH_LONG).show();
                            Log.i("holaa", "holaa fallo");

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.i_json),
                                Toast.LENGTH_LONG).show();
                        Log.i("app","makeJsonObj: onErrorResponse List22");
                    }
                });
        Volley.newRequestQueue(this).add(jsObjRequest);

    }

    private void misvideosRecyclerVid(){
        recyclerViewMis = (RecyclerView) findViewById(R.id.RecylerMisVideos2);
        adapter2 = new VideoInfoAdapter(this,videoList2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewMis.setHasFixedSize(true);
        recyclerViewMis.setLayoutManager(mLayoutManager);
        recyclerViewMis.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMis.setAdapter(adapter2);

    }

    private void makeJsonNumAmigos(String url){

        Log.i("Perfil","makeJsonUser");

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Perfil", "onResponse");

                        Gson gson = new Gson();

                        ConsultaNumAmigos c = gson.fromJson(response.toString(), ConsultaNumAmigos.class);

                        if (c.getSuccess() == 1) {

                            Log.i("Perfil", "makeJsonRequest: onResponse - get Success");
                            Log.i("Perfil", "aham"+c.getNumAmigos().get(0).getAmigos());
                            numamigos.setText(Integer.toString(c.getNumAmigos().get(0).getAmigos()));
                            int sum = c.getNumAmigos().get(0).getAmigos();
                            sum = sum+4;

                        }
                        else{

                            Log.i("Perfil","makeJsonRequest: onResponse - NOT Success");
                            Toast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.i_json),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Perfil","makeJsonObj: onErrorResponse - no funciona volley");
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.i_json),
                                Toast.LENGTH_LONG).show();
                    }
                });

        Volley.newRequestQueue(this).add(jsObjRequest);
    }

    private void makeJsonNumSeguidos(String url){
        Log.i("Perfil","makeJsonUser");

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Perfil", "onResponse");

                        Gson gson = new Gson();

                        ConsultaNumSeguidos c = gson.fromJson(response.toString(), ConsultaNumSeguidos.class);


                        if (c.getSuccess() == 1) {
                            Log.i("Perfil", "makeJsonRequest: onResponse - get Success");
                            Log.i("Perfil", "aham2 -"+c.getNumSeguidos().get(0).getSeguidos());
                            numsiguiendo.setText(Integer.toString(c.getNumSeguidos().get(0).getSeguidos()));

                        }
                        else{
                            Log.i("Perfil","makeJsonRequest: onResponse - NOT Success");
                            Toast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.i_json),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Perfil","makeJsonObj: onErrorResponse - no funciona volley");
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.i_json),
                                Toast.LENGTH_LONG).show();
                    }
                });

        Volley.newRequestQueue(this).add(jsObjRequest);
        Log.i("Perfil","volley");
    }

    private void makeJsonUser(String url2) {
        Log.i("Perfil","makeJsonUser");

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Perfil","onResponse");

                        Gson gson = new Gson();

                        ConsultaArtista c = gson.fromJson(response.toString(),ConsultaArtista.class);

                        if(c.getSuccess()==1){
                            Log.i("Perfil","makeJsonRequest: onResponse - get Success");

                            user.setText(c.getUsers().get(0).getUser());
                            email.setText(c.getUsers().get(0).getEmail());
                            descripcion.setText(c.getUsers().get(0).getDescripcion());
                            score.setText(Float.toString(c.getUsers().get(0).getScore()));



                            vidf[0] = c.getUsers().get(0).getF1();
                            vidf[1] = c.getUsers().get(0).getF2();
                            vidf[2] = c.getUsers().get(0).getF3();
                            vidf[3] = c.getUsers().get(0).getF3();

                            vidv[0] = c.getUsers().get(0).getV1();
                            vidv[1] = c.getUsers().get(0).getV2();
                            vidv[2] = c.getUsers().get(0).getV3();
                            vidv[3] = c.getUsers().get(0).getF4();

                            vidt[0] = c.getUsers().get(0).getT1();
                            vidt[1] = c.getUsers().get(0).getT2();
                            vidt[2] = c.getUsers().get(0).getT3();
                            vidt[3] = c.getUsers().get(0).getT4();

                            favoritosRecyclerVid();

                            makeImageRequest(c.getUsers().get(0).getFoto_perfil());

                        }
                        else{
                            Log.i("Perfil","makeJsonRequest: onResponse - NOT Success");
                            Toast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.i_videos),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Perfil","makeJsonObj: onErrorResponse - no funciona volley");
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.i_json),
                                Toast.LENGTH_LONG).show();
                    }
                });

        Volley.newRequestQueue(this).add(jsObjRequest);
    }

    private void makeJsonNumSeguidores(String url){}

    private void favoritosRecyclerVid() {

        videoList.add(new VideoInfo(vidt[0],vidv[0], Uri.parse(vidf[0%vidf.length])));
        videoList.add(new VideoInfo(vidt[1],vidv[1], Uri.parse(vidf[1%vidf.length])));
        videoList.add(new VideoInfo(vidt[2],vidv[2], Uri.parse(vidf[2%vidf.length])));
        videoList.add(new VideoInfo(vidt[3],vidv[3], Uri.parse(vidf[3%vidf.length])));


        recyclerFav = (RecyclerView) findViewById(R.id.RecylerFavoritos1);
        adapter1 = new VideoInfoAdapter(this,videoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerFav.setLayoutManager(mLayoutManager);
        recyclerFav.setItemAnimator(new DefaultItemAnimator());
        recyclerFav.setAdapter(adapter1);

    }


    private ProgressDialog pDialogImage;

    private void makeImageRequest(String url) {
        pDialogImage = new ProgressDialog(this);
        pDialogImage.setMessage("Loading...");
        pDialogImage.show();

        final ImageRequest imageReq = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {

                        fotoperfil.setImageBitmap(response);
                        pDialogImage.hide();
                    }
                },
                400,350,
                Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        fotoperfil.setImageResource(R.mipmap.ic_launcher);
                        pDialogImage.hide();
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.i_foto),
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
        Volley.newRequestQueue(this).add(imageReq);
        Log.i("PerfilArtista","Error imagen imagen bien");
    }
}
