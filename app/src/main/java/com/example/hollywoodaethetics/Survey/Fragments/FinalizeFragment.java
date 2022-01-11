package com.example.hollywoodaethetics.Survey.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hollywoodaethetics.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinalizeFragment extends Fragment {

    @BindView(R.id.finalize_split)
    TextView split;


   private static ImageView transferimg;
   private static FrameLayout placeholder;
   private static Bundle b;

    public FinalizeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = this.getArguments();
        String[] namesplit = b.getStringArray("namesplit");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_finalize, container, false);
        ButterKnife.bind(this,view);

        transferimg = view.findViewById(R.id.finalize_pic);
        placeholder = view.findViewById(R.id.finalize_placeholder);
        split.setText(namesplit[1]);
        switch(namesplit[0]){
            case "Spartan":{
                transferimg.setImageDrawable(getResources().getDrawable(R.drawable.physique_prev_spartan,null));
                break;
            }
            case "Olympus":{
                transferimg.setImageDrawable(getResources().getDrawable(R.drawable.physique_prev_olympus,null));
                break;
            }
            case "Avengers":{
                transferimg.setImageDrawable(getResources().getDrawable(R.drawable.physique_prev_avengers,null));
                break;
            }
            case "Wonder":{
                transferimg.setImageDrawable(getResources().getDrawable(R.drawable.physique_prev_wonder,null));
                break;
            }

        }

        transferimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transferimg.setVisibility(View.GONE);
                placeholder.setVisibility(View.VISIBLE);
                Fragment fragment = new FinalizeDescriptionFragment(getChildFragmentManager());
                fragment.setArguments(b);
                getChildFragmentManager().beginTransaction().replace(R.id.finalize_placeholder,
                       fragment)
                        .commit();
            }
        });

        return view;
    }


    public static void RestoreState(){
        transferimg.setVisibility(View.VISIBLE);
        placeholder.setVisibility(View.GONE);
    }

}
