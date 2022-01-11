package com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.YoutubeDetails.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hollywoodaethetics.HeatherBasedApplications.ExerciseDetails.ExerciseDetailsActivity;
import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.Data.exInterpretationResponce;
import com.example.hollywoodaethetics.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class YoutubeDetailsAdapter extends RecyclerView.Adapter<YoutubeDetailsAdapter.ViewHolder> {

    Context context;
    List<exInterpretationResponce> exInterpretationResponces = new ArrayList<>();

    public YoutubeDetailsAdapter(Context context){
        this.context = context;
    }

    public void setExInterpretationResponces(List<exInterpretationResponce> exInterpretationResponces){
        this.exInterpretationResponces = exInterpretationResponces;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_exercise_row,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(exInterpretationResponces.get(position).img).into(holder.img);
        holder.exname.setText(exInterpretationResponces.get(position).title);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ExerciseDetailsActivity.class);
            intent.putExtra("img",exInterpretationResponces.get(position).img);
            intent.putExtra("title",exInterpretationResponces.get(position).title);
            intent.putExtra("desc",exInterpretationResponces.get(position).description);
            intent.putExtra("category",exInterpretationResponces.get(position).category);
            intent.putExtra("video",exInterpretationResponces.get(position).video);
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return exInterpretationResponces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView img;
        TextView exname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.video_exrow_img);
            exname = itemView.findViewById(R.id.video_exrow_name);
        }
    }
}
