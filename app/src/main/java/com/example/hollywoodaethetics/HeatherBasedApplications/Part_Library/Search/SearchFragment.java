package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Adapters.DailyAdapter;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Library.Data.ExerciseResponce;
import com.example.hollywoodaethetics.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    List<ExerciseResponce> fulllist;
    @BindView(R.id.search_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.search_field)
    EditText searchfield;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,view);

        fulllist = (List)getArguments().getParcelableArrayList("list");

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new SearchAdapter(fulllist,this.getContext()));

        searchfield.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final String query = charSequence.toString().toLowerCase().trim();
                final List<ExerciseResponce> filteredlist = new ArrayList<>();
                for(int s=0; s<fulllist.size(); s++) {
                    final String text = fulllist.get(s).title.toLowerCase();
                    if (text.contains(query)) {
                        filteredlist.add(fulllist.get(s));
                    }
                }
                SearchAdapter adapter = new SearchAdapter(filteredlist,SearchFragment.this.getContext());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }
}
