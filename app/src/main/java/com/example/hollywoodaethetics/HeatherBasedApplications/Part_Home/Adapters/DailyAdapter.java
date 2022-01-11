package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data.DailyEntity;
import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.WorkoutDetails.WorkoutDetailsActivity;
import com.example.hollywoodaethetics.R;

import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {
    Context context;
    List<DailyEntity> dailyEntities;

    public DailyAdapter(List<DailyEntity> dailyEntities, Context context) {
        this.dailyEntities = dailyEntities;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_recycler_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dailyrowname.setText(dailyEntities.get(position).workoutname);
        holder.dailyrowsplit.setText(dailyEntities.get(position).splitname);
        holder.dailyrowdate.setText(dailyEntities.get(position).date);
        Glide.with(context).load(dailyEntities.get(position).imageurl).into(holder.dailyrowimg);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, WorkoutDetailsActivity.class);
            intent.putExtra("prev",dailyEntities.get(position).imageurl);
            intent.putExtra("name",dailyEntities.get(position).workoutname);
            intent.putExtra("date",dailyEntities.get(position).date);
            intent.putExtra("split",dailyEntities.get(position).splitname);
            intent.putExtra("exids",dailyEntities.get(position).exercises);
            intent.putExtra("reps",dailyEntities.get(position).reps);
            intent.putExtra("local?",true);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return dailyEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView dailyrowimg;
        public TextView dailyrowdate;
        public TextView dailyrowname;
        public TextView dailyrowsplit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dailyrowimg = itemView.findViewById(R.id.daily_row_dailyprev);
            dailyrowdate = itemView.findViewById(R.id.daily_row_date);
            dailyrowname = itemView.findViewById(R.id.daily_row_name);
            dailyrowsplit = itemView.findViewById(R.id.daily_row_split);
        }
    }
}
