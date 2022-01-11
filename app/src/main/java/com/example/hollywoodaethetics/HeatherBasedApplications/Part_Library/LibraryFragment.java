package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Category.CategoryFragment;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Data.ExerciseResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Search.SearchFragment;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.ViewModel.LibraryViewModel;
import com.example.hollywoodaethetics.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LibraryFragment extends Fragment {

    LibraryViewModel viewModel;
    List<ExerciseResponce> exerciseResponceList;


    @BindView(R.id.library_imageheader)
    ImageView header;
    @BindView(R.id.library_description_holder)
    LinearLayout descrholder;
    @BindView(R.id.library_option_selector)
    TextView selecttext;
    @BindView(R.id.library_selector)
    View view1;
    @BindView(R.id.library_selector_outline)
    View view2;
    @BindView(R.id.library_option_interactive)
    ImageView interactivebutton;
    @BindView(R.id.library_option_category)
    ImageView categorybutton;
    @BindView(R.id.library_option_search)
    ImageView searchbutton;
    @BindView(R.id.library_placeholder)
    ConstraintLayout constraintLayout;

    @BindView(R.id.library_description_holder_title)
    TextView descTitle;
    @BindView(R.id.library_description_holder_text)
    TextView descText;
    @BindView(R.id.library_description_holder_starter)
    ImageView starter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(LibraryViewModel.class);
        viewModel.setFragmentlinked(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_library, container, false);
        ButterKnife.bind(this,view);
        constraintLayout.setVisibility(View.GONE);
        viewModel.RequestResponceList();

        return view;
    }

    public void passLoading(List<ExerciseResponce> exerciseResponceList){
        this.exerciseResponceList = exerciseResponceList;
        sortExercises();
        constraintLayout.setVisibility(View.VISIBLE);

        categorybutton.setOnClickListener(view1 -> {
            if(descrholder.getVisibility()==(View.INVISIBLE)) {  descrholder.setVisibility(View.VISIBLE);   selecttext.setVisibility(View.INVISIBLE); }
            inflateDescription(getString(R.string.category_title),getString(R.string.category_text),'c');
        });
        interactivebutton.setOnClickListener(view1 -> {
            if(descrholder.getVisibility()==(View.INVISIBLE)) {  descrholder.setVisibility(View.VISIBLE);   selecttext.setVisibility(View.INVISIBLE); }
            inflateDescription(getString(R.string.interactive_title),getString(R.string.interactive_text),'i');
        });
        searchbutton.setOnClickListener(view1 -> {
            if(descrholder.getVisibility()==(View.INVISIBLE)) {  descrholder.setVisibility(View.VISIBLE);   selecttext.setVisibility(View.INVISIBLE); }
            inflateDescription(getString(R.string.search_title),getString(R.string.search_text),'s');
        });

    }

    private void inflateDescription(String title, String text, char action){
        descTitle.setText(title);
        descText.setText(text);
        switch (action){
            case 's':
                starter.setOnClickListener(view->{
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    selecttext.setVisibility(View.GONE);
                    descrholder.setVisibility(View.GONE);
                    searchbutton.setVisibility(View.GONE);
                    interactivebutton.setVisibility(View.GONE);
                    categorybutton.setVisibility(View.GONE);
                    SearchFragment fragment = new SearchFragment();
                    Bundle b = new Bundle();
                    b.putParcelableArrayList("list",(ArrayList)exerciseResponceList);
                    fragment.setArguments(b);
                    getChildFragmentManager().beginTransaction().replace(R.id.library_placeholder,fragment).commit();
                });
                break;
            case 'c':
                starter.setOnClickListener(view->{
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    selecttext.setVisibility(View.GONE);
                    descrholder.setVisibility(View.GONE);
                    searchbutton.setVisibility(View.GONE);
                    interactivebutton.setVisibility(View.GONE);
                    categorybutton.setVisibility(View.GONE);
                    CategoryFragment fragment = new CategoryFragment();
                    Bundle b = new Bundle();
                    b.putParcelableArrayList("list",(ArrayList)exerciseResponceList);
                    fragment.setArguments(b);
                    getChildFragmentManager().beginTransaction().replace(R.id.library_placeholder,fragment).commit();
                });
                break;
        }

    }

    private void sortExercises(){
        Collections.sort(exerciseResponceList, new Comparator<ExerciseResponce>() {
            @Override
            public int compare(ExerciseResponce exerciseResponce, ExerciseResponce t1) {
                return exerciseResponce.title.compareTo(t1.description);
            }
        });
    }
}
