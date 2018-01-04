package com.example.manuelsanchezferna.json;

import android.app.ProgressDialog;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
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

public class TopVideosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private VideoInfoAdapter adapter1;
    private List<VideoInfo> videoList = new ArrayList<VideoInfo>();

    private GridLayoutManager layoutManager;

    private String[] videosURLs= new String[10];

    private ProgressDialog pDialogImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topvideos);

        Toast.makeText(this,"Funciona", Toast.LENGTH_LONG).show();

        videosGridVid("https://unguled-flash.000webhostapp.com/Consultas/topvideos.php");
    }

    private void videosGridVid(String url) {
        pDialogImage = new ProgressDialog(this);
        pDialogImage.setMessage("Loading...");
        pDialogImage.show();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();

                        ConsultaVideos c = gson.fromJson(response.toString(),ConsultaVideos.class);

                        if(c.getSuccess()==1) {
                            for (int i = 0; i < 10; i++) {
                                videosURLs[i] = c.getVideos().get(i).getUrl();
                                videoList.add(new VideoInfo(c.getVideos().get(i).getName().toString()
                                        + " - " + c.getVideos().get(i).getTittle().toString(),
                                        Uri.parse(videosURLs[i%videosURLs.length])));
                            }
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
                        Log.i("app","makeJsonObj: onErrorResponse List2");
                    }
                });
        Volley.newRequestQueue(this).add(jsObjRequest);


        recyclerView = (RecyclerView) findViewById(R.id.RecylerView2);
        layoutManager = new GridLayoutManager(TopVideosActivity.this,2);
        adapter1 = new VideoInfoAdapter(this,videoList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter1);

        pDialogImage.hide();
    }
}
