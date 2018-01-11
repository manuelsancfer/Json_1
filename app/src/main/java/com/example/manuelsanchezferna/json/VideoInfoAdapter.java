package com.example.manuelsanchezferna.json;

import android.content.Context;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
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
 * Adaptador línia de vídeos a la pantalla principal
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
    public void onBindViewHolder(VideoInfoAdapter.ViewHolder holder, final int position) {
        holder.cancion.setText(videoInfoList.get(position).getCancion());
        holder.cancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(context,Video.class);
                intent2.putExtra("KEY_CANCION_NAME", videoInfoList.get(position).getCancion());
                context.startActivity(intent2);
            }
        });

        holder.artista.setText(videoInfoList.get(position).getArtista());
        holder.artista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,PerfilArtista.class);
                intent.putExtra("KEY_ARTISTA_NAME",videoInfoList.get(position).getArtista());
                context.startActivity(intent);
            }
        });

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
        private TextView puntuacion;
        private VideoView videoview;
        private Button artista, cancion;

        public ViewHolder(View itemView) {
            super(itemView);
            artista = (Button) itemView.findViewById(R.id.btn_artista);
            cancion = (Button) itemView.findViewById(R.id.btn_cancion);
            //puntuacion = (TextView) itemView.findViewById(R.id.puntuacion);
            //puntuacion.setMovementMethod(new ScrollingMovementMethod());
            videoview = (VideoView) itemView.findViewById(R.id.videoView);
        }
    }



}
