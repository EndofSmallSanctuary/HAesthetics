package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hollywoodaethetics.R;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LegendaryDetailsFragment extends Fragment {
    FragmentManager fragmentManager;
    @BindView(R.id.legend_details_backbutton)
    ImageView backbutton;
    @BindView(R.id.legend_details_img)
    RoundedImageView img;
    @BindView(R.id.legend_details_name)
    TextView name;
    @BindView(R.id.legend_details_title)
    TextView title;
    @BindView(R.id.legend_details_info)
    TextView info;
    @BindView(R.id.legend_details_height)
    TextView height;
    @BindView(R.id.legend_details_weight)
    TextView weight;

    //PhysiqueBuilder
    @BindView(R.id.legend_details_physiquemodel_neck)
    ImageView neck;
    @BindView(R.id.legend_details_physiquemodel_abs)
    ImageView abs;
    @BindView(R.id.legend_details_physiquemodel_chest)
    ImageView chestS;
    @BindView(R.id.legend_details_physiquemodel_back)
    ImageView back;
    @BindView(R.id.legend_details_physiquemodel_leftarm)
    ImageView leftarm;
    @BindView(R.id.legend_details_physiquemodel_leftarmback)
    ImageView leftarmback;
    @BindView(R.id.legend_details_physiquemodel_rightarm)
    ImageView rightarm;
    @BindView(R.id.legend_details_physiquemodel_rightarmback)
    ImageView rightarmback;
    @BindView(R.id.legend_details_physiquemodel_legleft)
    ImageView legleft;
    @BindView(R.id.legend_details_physiquemodel_leftlegback)
    ImageView legleftback;
    @BindView(R.id.legend_details_physiquemodel_rightleft)
    ImageView rightleg;
    @BindView(R.id.legend_details_physiquemodel_rightlegback)
    ImageView rightlegback;

    public LegendaryDetailsFragment() {
        // Required empty public constructor
    }
    public LegendaryDetailsFragment(FragmentManager manager){
        fragmentManager = manager;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_legendary_details, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        backbutton.setOnClickListener(view -> {
            fragmentManager.beginTransaction().detach(LegendaryDetailsFragment.this).commit();
        });
        Bundle b = getArguments();
        Glide.with(this).load(b.getString("img","")).into(img);
        name.setText(b.getString("name",""));
        info.setText(b.getString("info",""));
        title.setText(b.getString("title",""));
        physiqueModelParcer(b.getString("components",""));
        height.setText(Integer.toString(b.getInt("height")));
        weight.setText(Integer.toString(b.getInt("weight")));
        if(getActivity().getSharedPreferences("UserLocals", Context.MODE_PRIVATE).getString("metric","").equals("metric")){
            height.append(" cm");
            weight.append(" kg");
        } else {
            height.setText(height.getText().charAt(0)+"'"+height.getText().charAt(1)+ " ft");
            weight.append(" lbs");
        }
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void physiqueModelParcer(String components){
        String[] arraycomponents = components.split(",");
        for (String s: arraycomponents) {
            switch (s){
                case "chest": {
                    chestS.setVisibility(View.VISIBLE);
                    break;
                }
                case "arms": {
                    leftarm.setVisibility(View.VISIBLE);
                    rightarm.setVisibility(View.VISIBLE);
                    leftarmback.setVisibility(View.VISIBLE);
                    rightarmback.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
