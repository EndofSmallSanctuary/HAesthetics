package com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.Adapters.LeaderBoardAdapter;
import com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.Data.LeaderResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.ViewModel.LeaderboardsViewModel;
import com.example.hollywoodaethetics.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LeaderContainerFragment extends Fragment {

    LeaderboardsViewModel viewModel;
    LeaderBoardAdapter adapter;
    @BindView(R.id.leader_container_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.leader_container_loading)
    RelativeLayout loading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(LeaderboardsViewModel.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leader_container,container,false);
        ButterKnife.bind(this,view);
        adapter = new LeaderBoardAdapter(this.getContext(),this.getArguments().getBoolean("metric"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        viewModel.getLeaderslist().observe(getViewLifecycleOwner(), new Observer<List<LeaderResponce>>() {
            @Override
            public void onChanged(List<LeaderResponce> leaderResponces) {
                Log.d("Dogs",Integer.toString(leaderResponces.size()));
                adapter.setLeaderslist(leaderResponces);
                adapter.notifyDataSetChanged();
                loading.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        viewModel.getLeadersByCategory(
                getArguments().getString("category")
        );
        super.onResume();
    }
}
