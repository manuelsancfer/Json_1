package com.example.manuelsanchezferna.json;

import android.net.Uri;

/**
 * Created by Maria on 27/12/2017.
 */

class VideoInfo {

    private Uri videoUri;
    private String artista,cancion;
    private float puntuacion;

    public VideoInfo(String artista, String cancion, float puntuacion, Uri videoUri) {
        this.artista = artista;
        this.cancion = cancion;
        this.puntuacion = puntuacion;
        this.videoUri = videoUri;
    }

    public String getCancion(){ return cancion; }

    public void setCancion(String cancion){ this.cancion = cancion; }

    public float getPuntuacion(){return puntuacion;}

    public void setPuntuacion(float puntuacion) { this.puntuacion = puntuacion; }

    public String getArtista() { return artista; }

    public void setArtista(String artista) { this.artista = artista; }


    public Uri getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(Uri videoUri) {
        this.videoUri = videoUri;
    }
}
