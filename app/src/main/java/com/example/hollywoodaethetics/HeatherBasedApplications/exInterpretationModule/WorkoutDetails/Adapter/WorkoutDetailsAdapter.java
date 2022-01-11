package com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.WorkoutDetails.Adapter;

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

public class WorkoutDetailsAdapter extends RecyclerView.Adapter<WorkoutDetailsAdapter.ViewHolder> {

    Context context;
    List<exInterpretationResponce> exInterpretationResponces = new ArrayList<>();
    String[] reps;

    public void setExInterpretationResponces(Context context, List<exInterpretationResponce> exInterpretationResponces, String reps) {
        this.exInterpretationResponces = exInterpretationResponces;
        this.reps = reps.split(",");
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_details_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(exInterpretationResponces.get(position).img).into(holder.img);
        holder.name.setText(exInterpretationResponces.get(position).title);
        String[] certainExReps = reps[position].split("x");
        holder.reps.setText(context.getString(R.string.sets)+certainExReps[0]+" • "+context.getString(R.string.reps)+certainExReps[1]);
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
        TextView name;
        TextView reps;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.workout_details_row_img);
            name = itemView.findViewById(R.id.workout_details_row_name);
            reps = itemView.findViewById(R.id.workout_details_row_reps);
        }
    }
}
