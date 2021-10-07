package com.example.musicappandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import service.MyService;
import song.Song;

public class PlayMusic extends AppCompatActivity {

    private ImageView imgBia,imgClear,imgPlayOrPause,imgBack;
    private TextView tvNameNgheSi,tvNameSong;

    private Song mSong;
    private boolean isPlaying;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if( bundle == null){
                return;
            }

            mSong = (Song) bundle.get("object_song");
            isPlaying = bundle.getBoolean("status_player");
            int actionMusic = bundle.getInt("action_music");

            handleLayoutMusic(actionMusic);
        }
    };

    private void handleLayoutMusic(int action) {

        switch (action){
            case MyService.ACTION_START:
                showInforSong();
                setStatusButtonPlayOrPause();
                break;

            case MyService.ACTION_PAUSE:
                setStatusButtonPlayOrPause();
                break;

            case MyService.ACTION_RESUME:
                setStatusButtonPlayOrPause();
                break;

            case MyService.ACTION_CLEAR:
                break;
        }
    }

    private void setStatusButtonPlayOrPause() {
        if (isPlaying){
            imgPlayOrPause.setImageResource(R.drawable.outline_pause_circle_black_20);
        } else{
            imgPlayOrPause.setImageResource(R.drawable.outline_play_circle_black_20);
        }
    }

    private void showInforSong() {
        if (mSong == null){
            return;
        }

        imgPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    sendActionToService(MyService.ACTION_PAUSE);
                } else{
                    sendActionToService(MyService.ACTION_RESUME);
                }
            }
        });

        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendActionToService(MyService.ACTION_CLEAR);
            }
        });
    }

    private void sendActionToService(int action) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("action_music_service" , action);

        startService(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("send_data_to_activity"));

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        Song song = (Song) bundle.get("object_song");
        Intent intent = new Intent(this, MyService.class);
        intent.putExtras(bundle);

        imgBia = findViewById(R.id.imageSong);
        tvNameSong = findViewById(R.id.nameSong);
        tvNameNgheSi =  findViewById(R.id.nameNgheSi);
        imgBia.setImageResource(song.getResourceId());
        tvNameSong.setText(song.getNameSong());
        tvNameNgheSi.setText(song.getNameArtist());
        imgClear = findViewById(R.id.img_clear);
        imgPlayOrPause = findViewById(R.id.img_play_or_pause);
        if(isPlaying){
            sendActionToService(MyService.ACTION_CLEAR);
        } else{
            startService(intent);
        }
        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendActionToService(MyService.ACTION_CLEAR);
                finish();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sendActionToService(MyService.ACTION_CLEAR);
        finish();
    }
}