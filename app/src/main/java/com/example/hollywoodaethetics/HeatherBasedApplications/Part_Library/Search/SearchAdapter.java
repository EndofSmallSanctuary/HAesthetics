package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Search;

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
import com.example.hollywoodaethetics.HeatherBasedApplications.ExerciseDetails.ExerciseDetailsActivity;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Data.ExerciseResponce;
import com.example.hollywoodaethetics.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<ExerciseResponce> exerciseResponceList;

    public SearchAdapter(List<ExerciseResponce> exerciseResponceList , Context context){
        this.exerciseResponceList = exerciseResponceList;
        this.context = context;
    }
    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recycler_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(exerciseResponceList.get(position).img).into(holder.eximg);
        holder.extitle.setText(exerciseResponceList.get(position).title);


        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ExerciseDetailsActivity.class);
            intent.putExtra("img",exerciseResponceList.get(position).img);
            intent.putExtra("title",exerciseResponceList.get(position).title);
            intent.putExtra("desc",exerciseResponceList.get(position).description);
            intent.putExtra("category",exerciseResponceList.get(position).category);
            intent.putExtra("video",exerciseResponceList.get(position).video);
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return exerciseResponceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView eximg;
            TextView extitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eximg = itemView.findViewById(R.id.search_row_prev);
            extitle = itemView.findViewById(R.id.search_title);

        }
    }
}
