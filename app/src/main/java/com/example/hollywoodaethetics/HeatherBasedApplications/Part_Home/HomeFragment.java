package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hollywoodaethetics.HeatherBasedApplications.Leaderboards.LeaderboardsActivity;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Adapters.DailyAdapter;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Adapters.YoutubeAdapter;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data.DailyEntity;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.Data.YoutubeResponce;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Home.ViewModel.HomeViewModel;
import com.example.hollywoodaethetics.HeatherBasedApplications.exInterpretationModule.WorkoutDetails.WorkoutDetailsActivity;
import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.WorkoutNotes.WorkoutNotesActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private boolean isRest = false;
    HomeViewModel viewModel;


    @BindView(R.id.home_leaderboards)
    ImageView leaderboards;
    @BindView(R.id.home_workoutnotes)
    ImageView workoutnotes;

    @BindView(R.id.home_container)
    NestedScrollView container;
    @BindView(R.id.home_loading)
    RelativeLayout loading;

    @BindView(R.id.workouts_oftheday)
    TextView dailyheader;
    @BindView(R.id.home_dailyprev)
    ImageView dailyprev;
    @BindView(R.id.workouts_name)
    TextView workoutname;
    @BindView(R.id.workouts_date)
    TextView workoutdate;
    @BindView(R.id.workouts_split)
    TextView workoutsplit;
    @BindView(R.id.home_dailyrecycler)
    RecyclerView dailyrecycler;
    @BindView(R.id.home_emptyrecycler)
    TextView emptyrecycler;
    @BindView(R.id.home_youtube_recycler)
    RecyclerView youtuberecycler;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.setLinkedFragment(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);





        viewModel.getYoutube();
        viewModel.getDailyWorkout();


        leaderboards.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), LeaderboardsActivity.class));
        });
        workoutnotes.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), WorkoutNotesActivity.class));
        });
        return view;
    }

    public void passLoading(){
        //dialog.dismiss();

        Glide.with(HomeFragment.this)
                .load(viewModel.dailyLiveData.imageurl)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(dailyprev);

        if(viewModel.dailyLiveData.splitname.equals("Rest")){
            dailyheader.setVisibility(View.INVISIBLE);
            workoutsplit.setText("Rest");
            workoutdate.setText(viewModel.dailyLiveData.date);
            workoutname.setText("No Workouts today");
            //workoutname.setVisibility(View.INVISIBLE);
        }
        else {
            workoutname.setText(viewModel.dailyLiveData.workoutname);
            workoutdate.setText(viewModel.dailyLiveData.date);
            workoutsplit.setText(viewModel.dailyLiveData.splitname);
            dailyprev.setOnClickListener(view -> {
                Intent intent = new Intent(getContext(), WorkoutDetailsActivity.class);
                intent.putExtra("prev",viewModel.dailyLiveData.imageurl);
                intent.putExtra("name",viewModel.dailyLiveData.workoutname);
                intent.putExtra("date",viewModel.dailyLiveData.date);
                intent.putExtra("split",viewModel.dailyLiveData.splitname);
                intent.putExtra("exids",viewModel.dailyLiveData.exids);
                intent.putExtra("reps",viewModel.dailyLiveData.reps);
                intent.putExtra("local?",false);
                startActivity(intent);
            });

        }
        isDailyLegit();
        isYoutubeLegit();


        loading.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);
    }

    private String formattedate(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MMMM,dd,yyyy");
        String formatteddate = df.format(c);
        return formatteddate;
    }

    private void isDailyLegit(){

       List<DailyEntity> dailyEntityList = viewModel.getLocalDailies();
        Log.d("Rec",Integer.toString(dailyEntityList.size()));
                if ((dailyEntityList.size() > 1) && (dailyEntityList.get(dailyEntityList.size() - 1).date).equals(formattedate()) && !isRest) {
                    dailyEntityList.remove(dailyEntityList.size() - 1);
                    Collections.reverse(dailyEntityList);
                    dailyrecycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
                    dailyrecycler.setAdapter(new DailyAdapter(dailyEntityList, this.getContext()));
                    return;
                } else {
                    if ((dailyEntityList.size() >= 1) && !(dailyEntityList.get(dailyEntityList.size() - 1).date).equals(formattedate()) && isRest) {
                        Collections.reverse(dailyEntityList);
                        dailyrecycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
                        dailyrecycler.setAdapter(new DailyAdapter(dailyEntityList, this.getContext()));
                        return;
                    }
                }


            dailyrecycler.setVisibility(View.GONE);
            emptyrecycler.setVisibility(View.GONE);

       }


    private void isYoutubeLegit() {
        List<YoutubeResponce> dailyYoutubeList = viewModel.youtubeResponces;
        Collections.reverse(dailyYoutubeList);
        youtuberecycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        youtuberecycler.setAdapter(new YoutubeAdapter(dailyYoutubeList, this.getContext()));
    }


       public void setRest(){
        this.isRest = true;
       }

}
