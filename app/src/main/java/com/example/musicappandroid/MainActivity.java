package com.example.musicappandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import item.Item;
import item.ItemAdapter;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPhatGanDay;
    private ItemAdapter itemAdapter;

    private RecyclerView recyclerViewMFU;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPhatGanDay = findViewById(R.id.rcv_phatGanDay);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerViewPhatGanDay.setLayoutManager(linearLayoutManager);
        itemAdapter = new ItemAdapter(this);
        itemAdapter.setData(getItems());
        recyclerViewPhatGanDay.setAdapter(itemAdapter);


        recyclerViewMFU = findViewById(R.id.rcv_MFU);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerViewMFU.setLayoutManager(linearLayoutManager1);
        itemAdapter = new ItemAdapter(this);
        itemAdapter.setData(getItems1());
        recyclerViewMFU.setAdapter(itemAdapter);


    }


    private List<Item> getItems(){
        List<Item> listItem = new ArrayList<>();
        listItem.add(new Item(R.drawable.lebaobinh_img,"Thích Thì Đến","Lê Bảo Bình"));
        listItem.add(new Item(R.drawable.chillies_img,"Chill","By Nguyen Van Hung"));
        listItem.add(new Item(R.drawable.beiber_img,"STAY","Justin BeiBer"));
        return listItem;
    }

    private List<Item> getItems1(){
        List<Item> listItem = new ArrayList<>();
        listItem.add(new Item(R.drawable.jfla_img,"Shape Of You","J.Fla Cover"));
        listItem.add(new Item(R.drawable.beiber_img,"STAY","Justin BeiBer"));
        listItem.add(new Item(R.drawable.hoailam_img,"Hoa Nở Không Màu","Hoài Lâm"));
        return listItem;
    }

}