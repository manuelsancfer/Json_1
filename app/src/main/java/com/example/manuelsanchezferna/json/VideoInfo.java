package com.example.manuelsanchezferna.json;

import android.net.Uri;

/**
 * Created by Maria on 27/12/2017.
 */

class VideoInfo {

    private String title;
    private Uri videoUri;
    private String artista;

    public VideoInfo(String artista, String title, Uri videoUri) {
        this.artista = artista;
        this.title = title;
        this.videoUri = videoUri;
    }

    public String getArtista() { return artista; }

    public void setArtista(String artista) { this.artista = artista; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(Uri videoUri) {
        this.videoUri = videoUri;
    }
}
