package com.example.manuelsanchezferna.json;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;

/**
 * Created by Maria on 11/01/2018.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<VideoInfo> videoList;
    private Context context2;

    public VideoAdapter(Context context2, List<VideoInfo> videoList) {
        this.context2 = context2;
        this.videoList = videoList;
    }

    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView2 = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video, parent, false);
        return new VideoAdapter.ViewHolder(itemView2);
    }

    @Override
    public void onBindViewHolder(VideoAdapter.ViewHolder holder, int position) {

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(context2);
            mediacontroller.setAnchorView(holder.videoview);
            holder.videoview.setMediaController(mediacontroller);
            holder.videoview.setVideoURI(videoList.get(position).getVideoUri());

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        holder.videoview.requestFocus();
    }

    @Override
    public int getItemCount() { return videoList.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private VideoView videoview;

        public ViewHolder(View itemView2) {
            super(itemView2);

            videoview = (VideoView) itemView2.findViewById(R.id.video);
        }
    }

}
