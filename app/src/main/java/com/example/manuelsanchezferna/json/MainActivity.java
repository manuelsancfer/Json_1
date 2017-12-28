package com.example.manuelsanchezferna.json;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;

    private RecyclerView recyclerView;
    private VideoInfoAdapter adapter;
    private List<VideoInfo> videoList; //lo que li volem passar al RecyclerView
    private String[] videosURLs = {"http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_20mb.mp4","http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_30mb.mp4"};

    //int videosid[] = {R.id.vid0, R.id.vid1, R.id.vid2, R.id.vid3, R.id.vid4, R.id.vid5, R.id.vid6,R.id.vid7, R.id.vid8, R.id.vid9}; //rellenar
    int scoreid[] = {};
    int tituloid[] = {};
    int artistaid[] = {};

    VideoView[] videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button top = (Button) findViewById(R.id.top_videos);
        Button vid = (Button) findViewById(R.id.videos);
        Button button = (Button) findViewById(R.id.button);


        textView = (TextView)findViewById(R.id.editText);

        //makeJsonVideo("https://unguled-flash.000webhostapp.com/Consultas/consultavideos.php");

        videosRecycler();

        createSpinner();
    }

    private void createSpinner() {

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.desplegable,android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Seleccio(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.i("app","onNothing");
            }
        });
    }

    private void videosRecycler() {
        videoList = new ArrayList<VideoInfo>();
        for (int i = 0; i < 10; i++) {
            videoList.add(new VideoInfo("Video " + i + ": " + videosURLs[i%videosURLs.length],Uri.parse(videosURLs[i%videosURLs.length])));
        }
        Toast.makeText(getApplicationContext(), "Video " + 1 + ": " + videosURLs[1%videosURLs.length].toString(), Toast.LENGTH_LONG).show();

 
        recyclerView = (RecyclerView) findViewById(R.id.RecylerView);
        adapter = new VideoInfoAdapter(this,videoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void Seleccio(int pos) {
        if (pos == 1){
            //Perfil propio usuario
            Intent intent = new Intent(this,Perfil.class);
            startActivity(intent);
        }

        if(pos == 2){
            //Lista rep
        }

        if(pos == 3){
            //Categorías
        }

        if (pos == 4){
            //Agenda
        }

        if (pos == 5){
            //Configuración
            Intent intent = new Intent(this,ConfigUsuario.class);
            startActivity(intent);
        }
    }

    public void consulta(View view) {
        //makeJsonRequest("https://unguled-flash.000webhostapp.com/Consultas/consulta.php");
    }

    public void c_topvideo(View view){ //Consulta top videos
        //makeJsonRequest("https://unguled-flash.000webhostapp.com/Consultas/topvideos.php");
    }

    public void c_canciones(View view){ //Consulta canciones
        //makeJsonRequest("https://unguled-flash.000webhostapp.com/Consultas/canciones.php");
    }

//    private void makeJsonRequest(String url) {
//        Log.i("app","makeJsonRequest");
//
//
//        JsonObjectRequest jsObjRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.i("app","makeJsonRequest: onResponse main");
//
//                        Gson gson = new Gson();
//                        Log.i("app","makeJsonRequest: onResponse - newGson");
//
//                        ConsultaUsers c = gson.fromJson(response.toString(),ConsultaUsers.class);
//
//                        if(c.getSuccess()==1) {
//                            textView.setText(c.getUsers().get(0).getUser());
//                            Toast.makeText(getApplicationContext(),
//                                    "funciona", Toast.LENGTH_LONG).show();
//                        }
//                        else {
//                            /*Toast.makeText(getApplicationContext(),
//                                    response.toString(), Toast.LENGTH_LONG).show();*/
//
//                        }
//                   }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        textView.setText("Error: " + error.toString());
//                        Log.i("app","makeJsonObj: onErrorResponse List");
//                    }
//                });
//
//
//        Volley.newRequestQueue(this).add(jsObjRequest);
//    }
/*
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
                        videoView = new VideoView[10];

                        for (int i=0; i<videosid.length;i++){

                            videoView[i] = (VideoView) findViewById(videosid[i]);  //Falta Videoview1,2...
                            Uri v = Uri.parse(c.getVideos().get(i).getUrl());
                            Log.i("app","makeJsonRequest: onResponse - queme");



//                                TextView score = (TextView) findViewById(scoreid[i]);
//                                String scoret = Float.toString(
//                                        c.getVideos().get(i).getScore());
//                                score.setText(scoret);   //Falta R.id...

                            //TextView vid0_name = (TextView) findViewById(tituloid[i]);Faltan R.id..
                            //TextView vid0_art = (TextView) findViewById(artistaid[i]);Faltan R.id..

                            //vid0_name.setText(c.getVideos().get(0).getTittle()); idem
                            //vid0_art.setText(c.getVideos().get(0).getName());

                            //videoView.setVideoURI(v);
                        }

                        //for (int i=0; i<videosid.length;i++){
                        videoView[9].setOnTouchListener(new View.OnTouchListener()
                        {
                            @Override
                            public boolean onTouch(View v, MotionEvent motionEvent)
                            {
                                if (videoView[9].isPlaying())
                                {
                                    Toast.makeText(getApplicationContext(),
                                            "touch", Toast.LENGTH_LONG).show();
                                    videoView[9].pause();
//                    if (!getActivity().getActionBar().isShowing())
//                    {
//                        getActivity().getActionBar().show();
//                        mMediaController.show(0);
//                    }
//                    position = mVideoView.getCurrentPosition();
                                    return false;
                                }
                                else
                                { Toast.makeText(getApplicationContext(),
                                        "touchi", Toast.LENGTH_LONG).show();
//                    if (getActivity().getActionBar().isShowing())
//                    {
//                        getActivity().getActionBar().hide();
//                        mMediaController.hide();
//                    }
//                    videoView.seekTo(position);
                                    videoView[9].setVideoURI(va);  //prueba
                                    return false;

                                }

                            }});// }



                        //TODO conseguir que funcionen estas 4 lineas:
//                            MediaController mediaController = new MediaController(this);
//                            VideoView simpleVideoView = (VideoView) findViewById(R.id.vid9);
//                            simpleVideoView.setMediaController(mediaController);
//                            videoView.setVideoURI(va);


                        videoView[9].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                videoView[9].start();
                                //videoView.stopPlayback();

                            }

                        });





                    }
                    else {
                            */
/*Toast.makeText(getApplicationContext(),
                                    response.toString(), Toast.LENGTH_LONG).show();*//*

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
*/







}
