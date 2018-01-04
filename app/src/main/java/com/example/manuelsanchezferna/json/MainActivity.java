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

    private RecyclerView recyclerViewVid, recyclerViewTop;
    private VideoInfoAdapter adapter1, adapter2;
    private List<VideoInfo> videoList = new ArrayList<VideoInfo>();; //lo que li volem passar al RecyclerView
    private List<VideoInfo> videoList2 = new ArrayList<VideoInfo>();; //lo que li volem passar al RecyclerView



    private String[] videosURLs= new String[10];
    private String[] videosURLsTop= new String[10];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button top = (Button) findViewById(R.id.top_videos);
        Button vid = (Button) findViewById(R.id.videos);
        Button button = (Button) findViewById(R.id.button);


        textView = (TextView)findViewById(R.id.editText);

        videosRecyclerVid("https://unguled-flash.000webhostapp.com/Consultas/consultavideos.php");
        videosRecyclerTop("https://unguled-flash.000webhostapp.com/Consultas/topvideos.php");
        //Toast.makeText(this,"HEEEELLOOOOOOOO", Toast.LENGTH_LONG).show();


        createSpinner();
    }

    private void videosRecyclerVid(String url) {

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
                                //videoList.add(new VideoInfo("Video " + i + ": " + videosURLs[i%videosURLs.length],Uri.parse(videosURLs[i%videosURLs.length])));
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
                        textView.setText("Error: " + error.toString());
                        Log.i("app","makeJsonObj: onErrorResponse List2");
                    }
                });
        Volley.newRequestQueue(this).add(jsObjRequest);


        recyclerViewVid = (RecyclerView) findViewById(R.id.RecylerViewVid);
        adapter1 = new VideoInfoAdapter(this,videoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewVid.setLayoutManager(mLayoutManager);
        recyclerViewVid.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVid.setAdapter(adapter1);

    }

    private void videosRecyclerTop(String url) {


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();

                        ConsultaVideos c = gson.fromJson(response.toString(),ConsultaVideos.class);

                        if(c.getSuccess()==1) {
                            for (int i = 0; i < 3; i++) {
                                videosURLsTop[i] = c.getVideos().get(i).getUrl();
                                videoList2.add(new VideoInfo(c.getVideos().get(i).getName().toString()
                                        + " - " + c.getVideos().get(i).getTittle().toString(),
                                        Uri.parse(videosURLsTop[i%videosURLsTop.length])));
                                //videoList2.add(new VideoInfo("Video " + i + ": " + videosURLsTop[i%videosURLsTop.length],Uri.parse(videosURLsTop[i%videosURLsTop.length]))); //he
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
                        textView.setText("Error: " + error.toString());
                        Log.i("app","makeJsonObj: onErrorResponse List2");
                    }
                });
        Volley.newRequestQueue(this).add(jsObjRequest);


        recyclerViewTop = (RecyclerView) findViewById(R.id.RecylerViewTop);
        adapter2 = new VideoInfoAdapter(this,videoList2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewTop.setLayoutManager(mLayoutManager);
        recyclerViewTop.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTop.setAdapter(adapter2);

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
            public void onNothingSelected(AdapterView<?> adapterView) {Log.i("app","onNothing");}
        });
    }

    private void Seleccio(int pos) {

        if (pos == 0){

        }

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


    private void makeJsonRequest(String url) {
        Log.i("app","makeJsonRequest");


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("app","makeJsonRequest: onResponse main");

                        Gson gson = new Gson();
                        Log.i("app","makeJsonRequest: onResponse - newGson");

                        ConsultaUsers c = gson.fromJson(response.toString(),ConsultaUsers.class);

                        if(c.getSuccess()==1) {
                            textView.setText(c.getUsers().get(0).getUser());
                            Toast.makeText(getApplicationContext(),
                                    "funciona", Toast.LENGTH_LONG).show();
                        }
                        else {
                            /*Toast.makeText(getApplicationContext(),
                                    response.toString(), Toast.LENGTH_LONG).show();*/

                        }
                   }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Error: " + error.toString());
                        Log.i("app","makeJsonObj: onErrorResponse List");
                    }
                });


        Volley.newRequestQueue(this).add(jsObjRequest);
    }



}
