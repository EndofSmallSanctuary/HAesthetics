package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data.YoutubeResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.YoutubeDetails.YoutubeDetailsActivity;
import com.example.hollywoodaethetics.R;


import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.ViewHolder> {
    Context context;
    List<YoutubeResponce> youtubeResponces;

    public YoutubeAdapter(List<YoutubeResponce> youtubeResponces,Context context){
        this.context = context;
        this.youtubeResponces = youtubeResponces;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_recycler_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(youtubeResponces.get(position).channel_image).into(holder.c_image);
        Glide.with(context).load(youtubeResponces.get(position).thub).into(holder.v_image);

        holder.date.setText(youtubeResponces.get(position).date);
        holder.v_name.setText(youtubeResponces.get(position).video_name);
        holder.c_name.setText(youtubeResponces.get(position).channel_name);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, YoutubeDetailsActivity.class);
            intent.putExtra("videoname",youtubeResponces.get(position).video_name);
            intent.putExtra("channelimg",youtubeResponces.get(position).channel_image);
            intent.putExtra("channelname",youtubeResponces.get(position).channel_name);
            intent.putExtra("video_id",youtubeResponces.get(position).video_id);
            intent.putExtra("date",youtubeResponces.get(position).date);
            intent.putExtra("exids",youtubeResponces.get(position).exids);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return youtubeResponces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView v_image;
        ImageView c_image;
        TextView c_name;
        TextView v_name;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v_image = itemView.findViewById(R.id.youtube_row_dailyprev);
            c_image = itemView.findViewById(R.id.youtube_row_channelimg);
            c_name = itemView.findViewById(R.id.youtube_row_channelname);
            v_name = itemView.findViewById(R.id.youtube_row_name);
            date = itemView.findViewById(R.id.youtube_row_date);
        }
    }
}
