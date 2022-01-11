package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.Adapters.LegendaryAdapter;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.Data.AestheticsResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.ViewModel.LegendaryViewModel;
import com.example.hollywoodaethetics.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LegendaryFragment extends Fragment {


    LegendaryViewModel viewModel;
    LegendaryAdapter adapter;
    @BindView(R.id.empty_aesthetics)
    TextView ifempty;
    @BindView(R.id.legend_layout)
    ConstraintLayout container;
    @BindView(R.id.legend_loading)
    RelativeLayout loading;
    @BindView(R.id.legend_container)
    ViewPager2 viewPager2;
    @BindView(R.id.legend_avatar)
    ImageView avatar;
    @BindView(R.id.legend_username)
    TextView username;
    @BindView(R.id.legend_add)
    ImageView add;

    public LegendaryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(LegendaryViewModel.class);
        adapter = new LegendaryAdapter();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_legendary, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onStart() {

        viewPager2.setAdapter(adapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1-Math.abs(position);
                page.setScaleY(0.95f + r * 0.05f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

        add.setOnClickListener(view -> {
            startActivity(new Intent(this.getActivity(), LegendaryeditActivity.class));
        });

        viewModel.getAestheticsResponceList().observe(this, new Observer<List<AestheticsResponce>>() {
            @Override
            public void onChanged(List<AestheticsResponce> aestheticsResponces) {
                List<AestheticsResponce> list = viewModel.getAestheticsResponceList().getValue();
                loading.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
               if(list.size()==0) ifempty.setVisibility(View.VISIBLE);
               else {
                   ifempty.setVisibility(View.GONE);
                   adapter.setAestheticsResponceList(list,LegendaryFragment.this);
                   adapter.notifyDataSetChanged();
               }
            }
        });
        Glide.with(this).load(getActivity().getSharedPreferences("UserLocals", Context.MODE_PRIVATE).getString("avatar",""))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(avatar);
        username.setText(getActivity().getSharedPreferences("UserLocals", Context.MODE_PRIVATE).getString("username","") + "'s");
//        List<PhysiquePojo> physiques = new ArrayList<>();
//        PhysiquePojo SashaBanks = new PhysiquePojo("Sasha Banks","She is the boss",
//                "https://wallpapercave.com/wp/wp4550503.jpg");
//        PhysiquePojo LivMorgan = new PhysiquePojo("Liv Morgan","She's tongue is omg",
//                "https://4.bp.blogspot.com/-YUx-0Ue3cVY/WrsjLltkhRI/AAAAAAAAKho/oDFYCnl0Rxkkh2bXajeapROifEC0AseTgCK4BGAYYCw/s1600/livmorgan28947471_806611746194973_8756376189872244111_o.jpg");
//        physiques.add(SashaBanks);
//        physiques.add(LivMorgan);

        super.onStart();
    }



    @Override
    public void onResume() {
        viewModel.getAesthetics(getContext().getSharedPreferences("UserLocals",Context.MODE_PRIVATE).getString("id",""));
        super.onResume();
    }

}
