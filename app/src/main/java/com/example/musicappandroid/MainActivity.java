package com.example.musicappandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import song.Song;
import song.SongAdapter;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPhatGanDay;
    private SongAdapter songAdapter;
    private RecyclerView recyclerViewMFU;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPhatGanDay = findViewById(R.id.rcv_phatGanDay);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerViewPhatGanDay.setLayoutManager(linearLayoutManager);
        songAdapter = new SongAdapter(this);
        songAdapter.setData(getItems());
        recyclerViewPhatGanDay.setAdapter(songAdapter);

        recyclerViewMFU = findViewById(R.id.rcv_MFU);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerViewMFU.setLayoutManager(linearLayoutManager1);
        songAdapter = new SongAdapter(this);
        songAdapter.setData(getItems1());
        recyclerViewMFU.setAdapter(songAdapter);

    }


    private List<Song> getItems(){
        List<Song> listSong = new ArrayList<>();
        listSong.add(new Song("Thích Thì Đến","Lê Bảo Bình",R.drawable.lebaobinh_img,R.raw.thichthiden));
        listSong.add(new Song("Chill!","By Nguyễn Văn Hùng",R.drawable.chillies_img,R.raw.cuchillthoi));
        listSong.add(new Song("Stay","Justin Beiber",R.drawable.beiber_img,R.raw.stay));
        return listSong;
    }

    private List<Song> getItems1(){
        List<Song> listSong = new ArrayList<>();
        listSong.add(new Song("Shape Of You","J.Fla",R.drawable.jfla_img,R.raw.shapeofyou));
        listSong.add(new Song("Stay","Justin Beiber",R.drawable.beiber_img,R.raw.stay));
        listSong.add(new Song("Hoa Nở Không Màu","Hoài Lâm",R.drawable.hoailam_img,R.raw.hoanokhongmau));
        listSong.add(new Song("Dark Horse","Katy Perry",R.drawable.katyperry_img,R.raw.darkhorse));
        listSong.add(new Song("Sài Gòn Đau Lòng Quá","Hứa Kim Tuyền",R.drawable.huakimtuyen_img,R.raw.saigondaulongqua));
        listSong.add(new Song("Where Do You Go","No Mercy",R.drawable.nomercy_img,R.raw.wheredoyougo));
        listSong.add(new Song("2000's Song Hit","Mix Song",R.drawable.song2000s_img,R.raw.song2000s));
        return listSong;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (songAdapter != null){
            songAdapter.release();
        }
    }

}