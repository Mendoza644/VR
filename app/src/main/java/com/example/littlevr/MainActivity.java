package com.example.littlevr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Images> imagesArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imagesArrayList.add(new Images(1, "Image 1", R.drawable.image_one));
        imagesArrayList.add(new Images(2, "Image 2", R.drawable.image_two));

        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new MyAdapter(imagesArrayList, getApplicationContext());
        mRecyclerView.setAdapter(adapter);

    }
}


