package com.example.manuelsanchezferna.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Video extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    private List<VideoInfo> videoList = new ArrayList<VideoInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerVideo);
        videoAdapter = new VideoAdapter(this,videoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(videoAdapter);

    }
}
