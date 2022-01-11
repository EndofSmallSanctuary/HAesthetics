package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.Data.AestheticsResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.LegendaryDetailsFragment;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.LegendaryFragment;
import com.example.hollywoodaethetics.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LegendaryAdapter extends RecyclerView.Adapter<LegendaryAdapter.LegendaryViewHolder>  {

    private LegendaryFragment context;
    private List<AestheticsResponce> aestheticsResponceList = new ArrayList<>();

    public LegendaryAdapter(){

    }

    public void setAestheticsResponceList(List<AestheticsResponce> list,LegendaryFragment context){
        this.context = context;
        aestheticsResponceList = list;
        //Collections.reverse(aestheticsResponceList);
    }

    @NonNull
    @Override
    public LegendaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LegendaryViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.legendary_physiquecontainer,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull LegendaryViewHolder holder, int position) {
        Picasso.get().load(aestheticsResponceList.get(position).imglink)
        .into(holder.kbv);
        holder.name.setText(aestheticsResponceList.get(position).name);
        holder.info.setText(aestheticsResponceList.get(position).info);
        holder.itemView.setOnClickListener(view -> {
                    Bundle b = new Bundle();
                    b.putString("img",aestheticsResponceList.get(position).imglink);
                    b.putString("name",aestheticsResponceList.get(position).name);
                    b.putString("info",aestheticsResponceList.get(position).info);
                    b.putString("title",aestheticsResponceList.get(position).tag);
                    b.putString("components",aestheticsResponceList.get(position).components);
                    b.putInt("height",aestheticsResponceList.get(position).height);
                    b.putInt("weight",aestheticsResponceList.get(position).weight);
                    LegendaryDetailsFragment fragment = new LegendaryDetailsFragment(context.getChildFragmentManager());
                    fragment.setArguments(b);
                    context.getChildFragmentManager().beginTransaction().replace(R.id.legend_details_container,fragment).commit();
                }

        );
    }

    @Override
    public int getItemCount() {
        return aestheticsResponceList.size();
    }

    static class LegendaryViewHolder extends RecyclerView.ViewHolder{
       private KenBurnsView kbv;
       private TextView name,info;
       public LegendaryViewHolder(@NonNull View itemView) {
           super(itemView);
           kbv= itemView.findViewById(R.id.legend_image);
           name = itemView.findViewById(R.id.legend_name);
           info = itemView.findViewById(R.id.legend_info);
       }

//       void setPhysiqueData(AestheticsResponce aestheticsResponce){
//           Picasso.get().load(physique.img).into(kbv);
//           name.setText(physique.title);
//           info.setText(physique.info);
//       }
   }


}
