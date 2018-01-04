package com.example.manuelsanchezferna.json;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

public class ConfigUsuario extends AppCompatActivity {

    private EditText user, email, pass, gustos, f1, f2, f3, f4;
    private ToggleButton priva;

/* TODO: Arreglar al cambiar lo de aquí si justo damos atrás al perfil no se actualiza el perfil
(si volvemos a darle a perfil si se actualiza)*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_usuario);

        user = (EditText) findViewById(R.id.edituser);
        email = (EditText) findViewById(R.id.editmail);
        pass = (EditText) findViewById(R.id.editcontrasenya);
        gustos = (EditText) findViewById(R.id.editgustos);
        f1 = (EditText) findViewById(R.id.editf1);
        f2 = (EditText) findViewById(R.id.editf2);
        f3 = (EditText) findViewById(R.id.editf3);
        f4 = (EditText) findViewById(R.id.editf4);
        priva = (ToggleButton) findViewById(R.id.btn_public);

        //TODO: favoritos

        String usuario = "cristina";
        makeJsonPriva("https://unguled-flash.000webhostapp.com/Consultas/consultaperfilpropio.php?user="
                +usuario);

    }

    public void cpass(View view) {


        String user = "cristina";


        if(pass.length()>7 && pass.length()<15) {
            String url =
                    "https://unguled-flash.000webhostapp.com/Consultas/updateconfig_password.php?pass=" +
                            pass.getText() + "&user=" + user;

            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("Configuración", "onResponse");

                            Gson gson = new Gson();

                            ConsultaResponse c = gson.fromJson(response.toString(), ConsultaUserPropi.class);


                            if (c.getSuccess() == 1) {
                                Log.i("Configuracion", "makeJsonRequest: onResponse - get Success");
                                Toast.makeText(getApplicationContext(),
                                        "Contraseña cambiada con éxito", Toast.LENGTH_LONG).show();
                            } else {
                                Log.i("Configuracion", "makeJsonRequest: onResponse - NOT Success");
                                Toast.makeText(getApplicationContext(),
                                        "No ha podido cambiarse la contraseña",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("Configuración", "makeJsonObj: onErrorResponse - no funciona volley");
                        }
                    });

            Volley.newRequestQueue(this).add(jsObjRequest);
            Log.i("Configuración", "volley");
        }

        else {
            Toast.makeText(getApplicationContext(),
                    "No ha podido cambirse la contraseña, debe contener entre 8 y 14 carácteres",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void cemail(View view) {

        String user = "cristina";

            String url =
                    "https://unguled-flash.000webhostapp.com/Consultas/updateconfig_email.php?email="
                            +email.getText()+"&user="+user;

            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("Configuración", "onResponseBotonEmail");

                            Gson gson = new Gson();

                            ConsultaResponse c = gson.fromJson(response.toString(), ConsultaUserPropi.class);


                            if (c.getSuccess() == 1) {
                                Log.i("Configuracion", "makeJsonRequest: onResponse - get Success");
                                Toast.makeText(getApplicationContext(),
                                        "Email cambiado con éxito", Toast.LENGTH_LONG).show();
                            } else {
                                Log.i("Configuracion", "makeJsonRequest: onResponse - NOT Success");
                                Toast.makeText(getApplicationContext(),
                                        "No ha podido cambiarse el email",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("Configuración", "makeJsonObj: onErrorResponse - boton email");
                        }
                    });

            Volley.newRequestQueue(this).add(jsObjRequest);
            Log.i("Configuración", "volley");

    }

    public void cgustos(View view) {

        String user = "cristina";

        String url =
                "https://unguled-flash.000webhostapp.com/Consultas/updateconfig_gustosmusic.php?gustos="
                        +gustos.getText()+"&user="+user;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Configuración", "onResponseBotonGustos");

                        Gson gson = new Gson();

                        ConsultaResponse c = gson.fromJson(response.toString(), ConsultaUserPropi.class);


                        if (c.getSuccess() == 1) {
                            Log.i("Configuracion", "makeJsonRequest: onResponse - get Success");
                            Toast.makeText(getApplicationContext(),
                                    "Gustos musicales cambiados con éxito", Toast.LENGTH_LONG).show();
                        } else {
                            Log.i("Configuracion", "makeJsonRequest: onResponse - NOT Success");
                            Toast.makeText(getApplicationContext(),
                                    "No han podido cambiarse los gustos musicales",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Configuración", "makeJsonObj: onErrorResponse - boton gustos");
                    }
                });

        Volley.newRequestQueue(this).add(jsObjRequest);
        Log.i("Configuración", "volley");
    }

    public void cpriv(View view) {

        String user = "cristina";
        int estado;

        if(priva.isChecked()){
            estado=1;
        }

        else{ estado = 0;}

        String url =
                "https://unguled-flash.000webhostapp.com/Consultas/updateconfig_privacidad.php?priva="
                       +estado+"&user="+user;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Configuración", "onResponseBotonGustos");

                        Gson gson = new Gson();

                        ConsultaResponse c = gson.fromJson(response.toString(), ConsultaUserPropi.class);


                        if (c.getSuccess() == 1) {
                            Log.i("Configuracion", "makeJsonRequest: onResponse - get Success");
                            Toast.makeText(getApplicationContext(),
                                    "Privacidad cambiada con éxito", Toast.LENGTH_LONG).show();
                        } else {
                            Log.i("Configuracion", "makeJsonRequest: onResponse - NOT Success");
                            Toast.makeText(getApplicationContext(),
                                    "No ha podido cambiarse la privacidad",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Configuración", "makeJsonObj: onErrorResponse - boton privacidad");
                    }
                });

        Volley.newRequestQueue(this).add(jsObjRequest);
        Log.i("Configuración", "volley");
    }

    private void makeJsonPriva(String url) {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();

                        ConsultaUserPropi c = gson.fromJson(response.toString(),ConsultaUserPropi.class);
                        boolean estado = c.getUsers().get(0).isPublico();

                        if(c.getSuccess()==1) {
                            if(estado == true ){
                                priva.setChecked(false);
                            }
                            else{
                                priva.setChecked(true);
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    response.toString(), Toast.LENGTH_LONG).show();
                            Log.i("app","makeJsonRequest: onResponse - no funciona");

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("app","makeJsonObj: onErrorResponse List2");
                    }
                });
        Volley.newRequestQueue(this).add(jsObjRequest);


    }
}
