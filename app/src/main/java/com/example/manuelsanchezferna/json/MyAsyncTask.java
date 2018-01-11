package com.example.manuelsanchezferna.json;

import android.os.AsyncTask;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

/**
 * Created by DjManko on 11/1/18.
 */

public class MyAsyncTask extends AsyncTask <String,Void,Boolean> {

    private ProgressDialog progressDialog;
    AlertDialog.Builder builder;
    private Context context;

    /**Constructor de clase */
    public MyAsyncTask(Context context) {
        this.context = context;
        builder = new AlertDialog.Builder(context);
    }
    /**
     * Antes de comenzar la tarea muestra el progressDialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Por favor espere", "Subiendo...");
    }

    /**
     * @param
     * */
    @Override
    protected Boolean doInBackground(String... params) {
        Boolean r = false;
        UploadImage apiRest = new UploadImage();
        try {
            r = apiRest.uploadPhoto(params[0]);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r;
    }

    /**
     * Cuando se termina de ejecutar, cierra el progressDialog y avisa
     * **/
    @Override
    protected void onPostExecute(Boolean resul) {
        progressDialog.dismiss();
        if( resul )
        {
            builder.setMessage("Imagen subida al servidor")
                    .setTitle("JC le informa")
                    .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            dialog.cancel();
                        }
                    }).create().show();
        }
        else
        {
            builder.setMessage("No se pudo subir la imagen")
                    .setTitle("JC le informa")
                    .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            dialog.cancel();
                        }
                    }).create().show();
        }
    }
}
