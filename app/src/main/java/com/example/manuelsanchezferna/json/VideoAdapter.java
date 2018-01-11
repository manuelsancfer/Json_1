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

        holder.artista2.setText(videoList.get(position).getArtista());
        holder.artista2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context2,PerfilArtista.class);
                context2.startActivity(intent);
                // @Jordi: suposo que també abans d'engegar l'activitat li hauràs de passar l'artista, no?
            }
        });

        holder.titulo.setText(videoList.get(position).getCancion());

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
        private TextView titulo;
        private VideoView videoview;
        private Button artista2;

        public ViewHolder(View itemView2) {
            super(itemView2);

            artista2 = (Button) itemView2.findViewById(R.id.btn_artista2);
            titulo = (TextView) itemView2.findViewById(R.id.texto_cancion);
            videoview = (VideoView) itemView2.findViewById(R.id.video);
        }
    }

}
