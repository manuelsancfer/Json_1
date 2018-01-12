package com.example.manuelsanchezferna.json;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ConfigUsuario extends AppCompatActivity {

    private EditText email, pass, gustos, f1, f2, f3, f4;
    private ToggleButton priva;
    private ImageView photo;
    private Button image;
    private Bitmap photobmp;;

/* TODO: Arreglar al cambiar lo de aquí si justo damos atrás al perfil no se actualiza el perfil
(si volvemos a darle a perfil si se actualiza)*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_usuario);

        email = (EditText) findViewById(R.id.editmail);
        pass = (EditText) findViewById(R.id.editcontrasenya);
        gustos = (EditText) findViewById(R.id.editgustos);
        f1 = (EditText) findViewById(R.id.editf1);
        f2 = (EditText) findViewById(R.id.editf2);
        f3 = (EditText) findViewById(R.id.editf3);
        f4 = (EditText) findViewById(R.id.editf4);
        priva = (ToggleButton) findViewById(R.id.btn_public);
        image = (Button) findViewById(R.id.btn_subirfoto);
        photo = (ImageView) findViewById(R.id.photo);




        //TODO: favoritos

        String usuario = "cristina";
        makeJsonPriva("https://unguled-flash.000webhostapp.com/Consultas/consultaperfilpropio.php?user="
                +usuario);
        createSpinner();

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
                                        getResources().getString(R.string.a_contrasenya),
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Log.i("Configuracion", "makeJsonRequest: onResponse - NOT Success");
                                Toast.makeText(getApplicationContext(),
                                        getResources().getString(R.string.i_contrasenya),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("Configuracion", "makeJsonRequest: onResponse - get Success");
                            Toast.makeText(getApplicationContext(),"eo: "+
                                    getResources().getString(R.string.i_json),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

            Volley.newRequestQueue(this).add(jsObjRequest);
        }

        else {
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.e_contrasenya),
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
                                        getResources().getString(R.string.a_email),
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Log.i("Configuracion", "makeJsonRequest: onResponse - NOT Success");
                                Toast.makeText(getApplicationContext(),
                                        getResources().getString(R.string.i_email),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.i_json),
                                    Toast.LENGTH_LONG).show();
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
                                    getResources().getString(R.string.a_gustos),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Log.i("Configuracion", "makeJsonRequest: onResponse - NOT Success");
                            Toast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.i_gustos),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.i_json),
                                Toast.LENGTH_LONG).show();
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
                                    getResources().getString(R.string.a_priv),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Log.i("Configuracion", "makeJsonRequest: onResponse - NOT Success");
                            Toast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.i_priv),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Configuración", "makeJsonObj: onErrorResponse - boton privacidad");
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.i_json),
                                Toast.LENGTH_LONG).show();
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
                                    getResources().getString(R.string.i_priv),
                                    Toast.LENGTH_LONG).show();
                            Log.i("app","makeJsonRequest: onResponse - no funciona");

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("app","makeJsonObj: onErrorResponse List2");
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.i_json),
                                Toast.LENGTH_LONG).show();
                    }
                });
        Volley.newRequestQueue(this).add(jsObjRequest);


    }

    private void createSpinner() {

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterS = ArrayAdapter.createFromResource(this,
                R.array.desplegable6,android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapterS);
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
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }

        if(pos == 2){
            //Perfil propio usuario
            Intent intent = new Intent(this,Perfil.class);
            startActivity(intent);
        }

        if(pos == 3){
            //Lista rep
        }

        if (pos == 4){
            //Categorías
            Intent intent = new Intent(this,Genero.class);
            startActivity(intent);
        }

        if (pos == 5){
            //Agenda
        }
    }

    public void clickimage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Complete la acción usando..."), 1);
    }

    public void buttoni(View view) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        photobmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        //Se ejecuta en segundo plano para no colgar la aplicacion
        new MyAsyncTask(ConfigUsuario.this).execute(encodedImage);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
//            String aaa = getRealPathFromURI(selectedImageUri);
            InputStream inputStream;
            try {
                inputStream = this.getContentResolver().openInputStream(data.getData());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
            photobmp = BitmapFactory.decodeStream(inputStream);
            photo.setImageBitmap(photobmp);
        }
    }


    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = getApplicationContext().getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}