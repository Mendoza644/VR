package com.example.littlevr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Images> imagesArrayList;
    private Context context;

    MyAdapter(ArrayList<Images> imagesArrayList, Context context) {
        this.context = context;
        this.imagesArrayList = imagesArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.rv_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Images images = imagesArrayList.get(i);
        viewHolder.imageButton.setImageResource(images.getmImage());
        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PanoramicActivity.class);
                intent.putExtra("message", images);
                new StyleableToast.Builder(context).
                        text("You chose " + images.getmImageName()).
                        backgroundColor(Color.BLUE).
                        textColor(Color.WHITE).cornerRadius(37).
                        stroke(6, Color.YELLOW).
                        show();
                //Add this fucking line if I want to make an intent with ArrayList,
                //if my Android version is below Marshmallow.
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagesArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imageButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.image_button);
        }
    }
}
