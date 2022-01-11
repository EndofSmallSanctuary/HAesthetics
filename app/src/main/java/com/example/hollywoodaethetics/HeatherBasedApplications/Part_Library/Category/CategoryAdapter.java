package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Category.Data.Category;
import com.example.hollywoodaethetics.R;
import com.google.android.exoplayer2.C;

import java.util.List;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    CategoryFragment context;
    List<Category> categories;



    public CategoryAdapter(List<Category> categories, CategoryFragment context){
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row,parent,false);
      return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
            holder.itemView.setId(position);
            holder.name.setText(categories.get(position).name);
            holder.img.setImageResource(categories.get(position).img);
            ((GifDrawable)holder.img.getDrawable()).stop();
            holder.description.setText(categories.get(position).description);
            holder.itemView.setOnClickListener(view -> {
                if(holder.description.getVisibility()==View.VISIBLE) holder.stopAnimation();
                else {
                    context.hideRecyclerItemsAnimation();
                    holder.initializeAnimation();
                }

            });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;
        GifImageView img;
        ImageView starter;
//        View divider;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.category_header);
            description = itemView.findViewById(R.id.category_description);
            img = itemView.findViewById(R.id.category_prev);
            starter = itemView.findViewById(R.id.category_starter);
//            divider = itemView.findViewById(R.id.category_divider);
        }

        public void initializeAnimation(){
            ((GifDrawable)img.getDrawable()).start();
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) name.getLayoutParams();
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.UNSET;
            name.setLayoutParams(layoutParams);
            starter.setVisibility(View.VISIBLE);
//            divider.setVisibility(View.VISIBLE);
            description.setVisibility(View.VISIBLE);
        }

        public void stopAnimation() {
            if (description.getVisibility() == View.VISIBLE) {
                ((GifDrawable)this.img.getDrawable()).stop();
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) name.getLayoutParams();
                layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
                name.setLayoutParams(layoutParams);
                starter.setVisibility(View.GONE);
//                divider.setVisibility(View.GONE);
                description.setVisibility(View.GONE);
            }
        }
    }
}
