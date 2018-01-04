package com.example.manuelsanchezferna.json;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Perfil extends AppCompatActivity {
    public static String  KEY_NAME = "KEY_NAME";
    public static int REQUEST_NAME = 1;


    /* private String url = "https://unguled-flash.000webhostapp.com/Consultas/consultavideos.php";
    private String url2 = "https://unguled-flash.000webhostapp.com/Consultas/consultaperfilpropio.php";
    private String url3 = "https://unguled-flash.000webhostapp.com/Consultas/ConsultaSeguidoresU.php";
    private String url4 = "https://unguled-flash.000webhostapp.com/Consultas/ConsultaAmigos.php";*/
    private ImageView fotoperfil;
    private TextView user, email, gustos, amigos, siguiendo;


    private RecyclerView recyclerView;
    private VideoInfoAdapter adapter;
    private List<VideoInfo> videoList = new ArrayList<VideoInfo>();

    private String[] vidf =  new String[4];
    private String[] vidt = new String[4];
    private String[] vidv = new String[4];


    VideoView videoView;
    TextView textView;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_usuari);
        //makeJsonVideo(url);

        user = (TextView) findViewById(R.id.usernamee);
        email = (TextView) findViewById(R.id.email);
        gustos = (TextView) findViewById(R.id.gustos_musicales);
        fotoperfil = (ImageView) findViewById(R.id.imagen_perfil);


        String usuario = "cristina";
        makeJsonUser("https://unguled-flash.000webhostapp.com/Consultas/consultaperfilpropio.php?user="
                +usuario);

        //makeJsonFollowers(url3);
        //makeJsonFriends(url4);

        createSpinner();
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

                    user.setText(c.getUsers().get(0).getUser());
                    email.setText(c.getUsers().get(0).getEmail());
                    gustos.setText(c.getUsers().get(0).getGustos_musicales());


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

                    videosRecyclerVid();

                    //TODO conseguir ver la imagen

                    makeImageRequest(c.getUsers().get(0).getFoto_perfil());

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


    private void videosRecyclerVid() {

        videoList.add(new VideoInfo(vidt[0]+"-"+vidv[0], Uri.parse(vidf[0%vidf.length])));
        videoList.add(new VideoInfo(vidt[1]+"-"+vidv[1], Uri.parse(vidf[1%vidf.length])));
        videoList.add(new VideoInfo(vidt[2]+"-"+vidv[2], Uri.parse(vidf[2%vidf.length])));
        videoList.add(new VideoInfo(vidt[3]+"-"+vidv[3], Uri.parse(vidf[3%vidf.length])));


        recyclerView = (RecyclerView) findViewById(R.id.RecylerViewFavoritos);
        adapter = new VideoInfoAdapter(this,videoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

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

    private void Seleccio(int pos) {

        if (pos == 0){

            count++;
            if (count == 2) {
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                count = 0;
            }

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

    private ProgressDialog pDialogImage; // Lo tenéis que declarar como atributo de la clase


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
                        Log.i("Perfil","Error foto perfil");
                        fotoperfil.setImageResource(R.mipmap.ic_launcher);
                        pDialogImage.hide();
                    }
                }
        );
        Volley.newRequestQueue(this).add(imageReq);
        Log.i("Perfil","Error imagen imagen bien");
    }
}



