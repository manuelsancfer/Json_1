package com.example.manuelsanchezferna.json;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_usuari);
        //makeJsonVideo(url);

        user = (TextView) findViewById(R.id.usernamee);
        email = (TextView) findViewById(R.id.email);
        gustos = (TextView) findViewById(R.id.gustos_musicales);

        String usuario = "cristina";
        makeJsonUser("https://unguled-flash.000webhostapp.com/Consultas/consultaperfilpropio.php?user="+usuario);


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

                    //TODO conseguir que funcione el settext de user
                    user.setText(c.getUsers().get(0).getUser());
                    email.setText(c.getUsers().get(0).getEmail());
                    gustos.setText(c.getUsers().get(0).getGustos_musicales());


                    makeJsonVideo(c.getUsers().get(0).getF1(), c.getUsers().get(0).getF2(),
                            c.getUsers().get(0).getF3(), c.getUsers().get(0).getF4());


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


    public void makeJsonVideo(int f1, int f2, int f3, int f4){
        //Toast.makeText(getApplicationContext(),
          //      f1, Toast.LENGTH_LONG).show();

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
}



