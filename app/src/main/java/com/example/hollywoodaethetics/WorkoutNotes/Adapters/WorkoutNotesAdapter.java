package com.example.hollywoodaethetics.WorkoutNotes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.WorkoutNotes.Data.WorkoutNote;
import com.example.hollywoodaethetics.WorkoutNotes.WorkoutNoteEditActivity;

import java.util.ArrayList;
import java.util.List;

public class WorkoutNotesAdapter extends RecyclerView.Adapter<WorkoutNotesAdapter.ViewHolder> {
    List<WorkoutNote> workoutNotes = new ArrayList<>();
    Context context;

    public WorkoutNotesAdapter(Context c,List<WorkoutNote> l){
        context = c;
        workoutNotes = l;
    }

    public void setWorkoutNotes(List<WorkoutNote> workoutNotes) {
        this.workoutNotes = workoutNotes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workoutnotes_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(workoutNotes.get(position).date);
        holder.name.setText(workoutNotes.get(position).name);
        holder.split.setText(workoutNotes.get(position).split);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, WorkoutNoteEditActivity.class);
            intent.putExtra("date",workoutNotes.get(position).date);
            intent.putExtra("name",workoutNotes.get(position).name);
            intent.putExtra("split",workoutNotes.get(position).split);
           intent.putExtra("content",workoutNotes.get(position).content);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return workoutNotes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView name;
        TextView split;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.note_row_date);
            name = itemView.findViewById(R.id.note_row_name);
            split = itemView.findViewById(R.id.note_row_split);
        }
    }

}
