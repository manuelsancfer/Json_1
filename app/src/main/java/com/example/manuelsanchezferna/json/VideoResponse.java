package com.example.manuelsanchezferna.json;

/**
 * Created by manuel.sanchez.ferna on 01/12/2017.
 */

public class VideoResponse {

    private String tittle;
    private String name;
    private String genre;
    private String tags;
    private float score;
    private String url;
    private String calidad;
    private String videos;
    private int success;

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "TopVideoResponse{" +
                "videos='" + videos + '\'' +
                ", success=" + success +
                '}';
    }
}
