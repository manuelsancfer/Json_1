package com.example.manuelsanchezferna.json;

import android.net.Uri;

/**
 * Created by Maria on 27/12/2017.
 */

class VideoInfo {

    private Uri videoUri;
    private String artista,cancion;


    public VideoInfo(String artista, String cancion, Uri videoUri) {
        this.artista = artista;
        this.cancion = cancion;
        this.videoUri = videoUri;
    }

    public String getCancion(){ return cancion; }

    public void setCancion(String cancion){ this.cancion = cancion; }


    public String getArtista() { return artista; }

    public void setArtista(String artista) { this.artista = artista; }


    public Uri getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(Uri videoUri) {
        this.videoUri = videoUri;
    }
}
