package com.example.hollywoodaethetics.Survey.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hollywoodaethetics.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class FinalizeDescriptionFragment extends Fragment {

    @BindView(R.id.finalize_description_pic)
    ImageView finalizepic;
    @BindView(R.id.finalize_description_header)
    TextView finalizehead;
    @BindView(R.id.finalize_description_text)
    TextView finalizetext;

   FragmentManager manager;
   Bundle b;



    public FinalizeDescriptionFragment() {
        // Required empty public constructor
    }

    public FinalizeDescriptionFragment(FragmentManager manager){
        this.manager = manager;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        b = this.getArguments();
        String[] namesplit = b.getStringArray("namesplit");

        View view = inflater.inflate(R.layout.fragment_finalize_description, container, false);
        ButterKnife.bind(this,view);

        switch (namesplit[0]){
            case "Spartan":
            {
                setUpPhysique(getResources().getDrawable(R.drawable.physique_prev_spartan_description),getResources().getText(R.string.spartan_title)
                        ,getString(R.string.longteststring),getResources().getColor(R.color.main,null));
                break;
            }
            case "Olympus":
            {
                setUpPhysique(getResources().getDrawable(R.drawable.physique_prev_olympus_description),getResources().getText(R.string.olympus_title)
                        ,getString(R.string.longteststring),getResources().getColor(R.color.olympus,null));
                break;
            }
            case "Avengers":
            {
                setUpPhysique(getResources().getDrawable(R.drawable.physique_prev_avengers_description),getResources().getText(R.string.avengers_title)
                        ,getString(R.string.avengers_text),getResources().getColor(R.color.avengers,null));
                break;
            }
            case "Wonder":
            {
                setUpPhysique(getResources().getDrawable(R.drawable.physique_prev_wonder_description),getResources().getText(R.string.wonder_title)
                        ,getString(R.string.longteststring),getResources().getColor(R.color.wonder,null));
                break;
            }

        }


        finalizepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalizeFragment.RestoreState();
                manager.beginTransaction().detach(FinalizeDescriptionFragment.this).commit();

            }
        });

        return view;
    }


    private void setUpPhysique(Drawable pic, CharSequence title, CharSequence descriprion, int textcolor){
        finalizepic.setImageDrawable(pic);
        finalizehead.setTextColor(textcolor);
        finalizehead.setText(title);
        finalizetext.setText(descriprion);

    }


}
