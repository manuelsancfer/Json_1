package com.example.manuelsanchezferna.json;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**Layout individual del vídeo (horizontal)**/


public class Video extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    private List<VideoInfo> videoList = new ArrayList<VideoInfo>();

    private String[] videosURLs= new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent intent1 = getIntent();
        String cancionName = intent1.getStringExtra("KEY_CANCION_NAME");

        Toast.makeText(getApplicationContext(),cancionName, Toast.LENGTH_LONG).show();

        //makeJsonUser con la KEY_CANCION_NAME (mirar VideoInfoAdapter para ver que retorna)

    }


    private void makeJsonUser(String url2) {
        Log.i("Perfil","makeJsonUser");

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Perfil","onResponse");

                        Gson gson = new Gson();

                        ConsultaVideos c = gson.fromJson(response.toString(),ConsultaVideos.class);

                        if(c.getSuccess()==1){
                            Log.i("Video","makeJsonRequest: onResponse - get Success");

                            videoList.add(new VideoInfo(c.getVideos().get(0).getName().toString(),
                                    c.getVideos().get(0).getTittle().toString()+" - "
                                            +c.getVideos().get(0).getScore(),
                                    Uri.parse(videosURLs[0%videosURLs.length])));

                            videosRecyclerVid();



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

    private void videosRecyclerVid() {

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerVideo);
        videoAdapter = new VideoAdapter(this, videoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(videoAdapter);
    }


}
