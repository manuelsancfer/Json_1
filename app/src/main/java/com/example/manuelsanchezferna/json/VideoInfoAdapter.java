package com.example.manuelsanchezferna.json;

import android.content.Context;
import android.widget.MediaController;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;

/**
 * Created by Maria on 27/12/2017.
 */

class VideoInfoAdapter extends RecyclerView.Adapter<VideoInfoAdapter.ViewHolder>{

    private List<VideoInfo> videoInfoList;
    private Context context;

    public VideoInfoAdapter(Context context, List<VideoInfo> videoInfoList) {
        this.context = context;
        this.videoInfoList = videoInfoList;
    }

    @Override
    public VideoInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VideoInfoAdapter.ViewHolder holder, int position) {
        holder.title.setText(videoInfoList.get(position).getTitle());
        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(context);
            mediacontroller.setAnchorView(holder.videoview);
            holder.videoview.setMediaController(mediacontroller);
            holder.videoview.setVideoURI(videoInfoList.get(position).getVideoUri());

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        holder.videoview.requestFocus();
    }

    @Override
    public int getItemCount() {
        return videoInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private VideoView videoview;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            videoview = (VideoView) itemView.findViewById(R.id.videoView);
        }
    }

}
