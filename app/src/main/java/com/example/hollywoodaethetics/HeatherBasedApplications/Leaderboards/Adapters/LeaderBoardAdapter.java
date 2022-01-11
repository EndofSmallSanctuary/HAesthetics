package com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.Data.LeaderResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.ProfileActivity;
import com.example.hollywoodaethetics.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder> {
    List<LeaderResponce> leaderslist = new ArrayList<>();
    Boolean metric;
    Context context;
    //METRIC, COLORS, CONTEXT

    public LeaderBoardAdapter(Context context, Boolean metric){
        this.metric = metric;
        this.context = context;
    }

    public void setLeaderslist(List<LeaderResponce> leaderslist) {
        this.leaderslist = leaderslist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leader_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(leaderslist.get(position).avatar).into(holder.img);
        holder.rank.setText("#"+Integer.toString(position+1));
        holder.username.setText(leaderslist.get(position).username);
        switch (leaderslist.get(position).physique){
            case "Olympus":
                holder.physique.setTextColor(context.getColor(R.color.olympus));
                holder.result.setTextColor(context.getColor(R.color.olympus));
                break;
            case "Wonder":
                holder.physique.setTextColor(context.getColor(R.color.wonder));
                holder.result.setTextColor(context.getColor(R.color.wonder));
                break;
            case "Avengers":
                holder.physique.setTextColor(context.getColor(R.color.avengers));
                holder.result.setTextColor(context.getColor(R.color.avengers));
                break;
        }

        holder.physique.setText(" " +leaderslist.get(position).physique);
        if(metric) holder.result.setText(String.format("%.1f", leaderslist.get(position).result) + " Kg");
        else holder.result.setText(String.format("%.1f",leaderslist.get(position).result*2.2f) + " lbs");

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context,ProfileActivity.class);
            intent.putExtra("id",leaderslist.get(position).id);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return leaderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rank;
        RoundedImageView img;
        TextView username;
        TextView physique;
        TextView result;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.leader_row_rank);
            img = itemView.findViewById(R.id.leader_row_img);
            username = itemView.findViewById(R.id.leader_row_name);
            physique = itemView.findViewById(R.id.leader_row_physique);
            result = itemView.findViewById(R.id.leader_row_result);
        }
    }
}
