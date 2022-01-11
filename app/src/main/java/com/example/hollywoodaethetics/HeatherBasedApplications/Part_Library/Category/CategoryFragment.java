package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Category;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Category.Data.Category;
import com.example.hollywoodaethetics.R;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

import static java.lang.System.in;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

//    @BindView(R.id.category_prev)
//    GifImageView keyliftsimg;
//    @BindView(R.id.category_header)
//    TextView header;
    @BindView(R.id.category_recycler)
    RecyclerView recyclerView;
//    @BindView(R.id.category_starter)
//    ImageView starter;
//    @BindView(R.id.category_description)
//    TextView description;
//    @BindView(R.id.category_divider)
//    View divider;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this,view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(new CategoryAdapter(Category.categoryList(),this));
//        ((GifDrawable)keyliftsimg.getDrawable()).stop();
//        keyliftsimg.setOnClickListener(view1 -> {
//            ((GifDrawable)keyliftsimg.getDrawable()).start();
//            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) header.getLayoutParams();
//            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.UNSET;
//            header.setLayoutParams(layoutParams);
//            starter.setVisibility(View.VISIBLE);
//            divider.setVisibility(View.VISIBLE);
//            description.setVisibility(View.VISIBLE);
//        });
        return view;
    }

    public void hideRecyclerItemsAnimation(){
        for(int i=0; i<recyclerView.getChildCount();i++){
            View view =recyclerView.getChildAt(i);
            CategoryAdapter.ViewHolder holder = (CategoryAdapter.ViewHolder)recyclerView.findContainingViewHolder(view);
            holder.stopAnimation();
        }
//        for(int i =0; i<Category.categoryList().size();i++){
//            CategoryAdapter.ViewHolder holder =  (CategoryAdapter.ViewHolder)recyclerView.findViewHolderForItemId(i);
//            Log.d("rectest",holder.name.getText().toString());
//
//        }
    }
}
