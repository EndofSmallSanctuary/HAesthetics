package com.example.hollywoodaethetics.Survey;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.hollywoodaethetics.HeatherBasedApplications.HeatherBasedActivity;
import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.Survey.Fragments.FinalizeFragment;
import com.example.hollywoodaethetics.Survey.Fragments.FitnessLevelFragment;
import com.example.hollywoodaethetics.Survey.Fragments.GenderPickerFragment;
import com.example.hollywoodaethetics.Survey.Fragments.GoalsFragment;
import com.example.hollywoodaethetics.Survey.Fragments.HeightPickerFragment;
import com.example.hollywoodaethetics.Survey.Fragments.PerformanceFragment;
import com.example.hollywoodaethetics.Survey.Fragments.ProfilePictureFragment.ProfilePictureFragment;
import com.example.hollywoodaethetics.Survey.Fragments.RegistrationFragment;
import com.example.hollywoodaethetics.Survey.Fragments.WeightPickerFragment;
import com.example.hollywoodaethetics.Survey.ViewModel.SurveyViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class SurveyActivity extends AppCompatActivity {

    Toast toast;
    View toast_view;
    TextView toast_text;

    @BindView(R.id.survey_next)
    Button nextButton;
    @BindView(R.id.survey_progress)
    ProgressBar progressBar;
    @BindView(R.id.survey_terms)
    TextView conditions;


    private SurveyViewModel viewModel = new SurveyViewModel(this);
    private Bundle b = new Bundle();
    int currentfragment = 0;
    private Fragment[] fragments;
    private AlertDialog dialog;

    //Static private to ViewModel Fields
    public static String gender;
    public static String metric;
    public static int weight;
    public static int height;
    public static int fitnesslevel;
    public static int[] goals = new int[3];
    public static float[] performance = new float[]{0f,0f,0f,0f};
    public static String avatarpath;

    //Static private userinfo Fields;
    public static String[] userinfo = new String[]{"","","",""};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        getWindow().setStatusBarColor(Color.TRANSPARENT );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        ButterKnife.bind(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(SurveyActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.hp_alert_dialogue);
        dialog = builder.create();
        progressBar.setMax(8);
        progressBar.setProgress(1);
        getSupportFragmentManager().beginTransaction().replace(R.id.survey_items, new GenderPickerFragment()).commit();

        //Survey-process Fragments
      fragments = new Fragment[]{
                new HeightPickerFragment(),
                new WeightPickerFragment(),
                new FitnessLevelFragment(),
                new GoalsFragment(),
                new PerformanceFragment(),
                new FinalizeFragment(),
                new ProfilePictureFragment(),
                new RegistrationFragment()
        };


        nextButton = findViewById(R.id.survey_next);
        nextButton.setOnClickListener(view -> {

            if (currentfragment == fragments.length-3){
                //dialog.show();
                viewModel.setUpBio(loadBio(b));
                b.clear();
                return;
            }

            if (currentfragment < fragments.length) {

                getSupportFragmentManager().beginTransaction().replace(R.id.survey_items, fragments[currentfragment]).commit();
                currentfragment++;
                progressBar.setProgress(progressBar.getProgress() + 1);
            }

            if (currentfragment == fragments.length) {
                conditions.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                nextButton.setText(getResources().getText(R.string.create_profile));

                nextButton.setOnClickListener(view1 -> {
                   // dialog.show();
                  if(isBioLegit()) ShowDecline(getString(R.string.error_fillthefields));
                  else {
                      viewModel.SetUpAvatar(avatarpath);
                      viewModel.setUpData(loadData(b));
                      viewModel.sendUserinfo();
                  }
                });
                }
        });
    }

    @Override
    protected void onResume() {
        setUpToast();
        super.onResume();
    }

    //-------------------------------------------------ActivityStatusMethods------------------------------------------------

    public void ShowDecline(String message){
      //  this.dialog.dismiss();
        toast_view.setBackgroundColor(Color.RED);
        toast_text.setText(message);
        toast.show();

    }

    public void PassDecline(){
       // this.dialog.dismiss()
        startActivity(new Intent(this, HeatherBasedActivity.class));
        finish();

    }

    public void PostCallupdate(Bundle bundle){
     //   this.dialog.dismiss();
         fragments[currentfragment].setArguments(bundle);
         getSupportFragmentManager().beginTransaction().replace(R.id.survey_items,fragments[currentfragment]).commit();
         currentfragment++;
         progressBar.setProgress(progressBar.getProgress() + 1);
    }

    //-------------------------------------------------/ActivityStatusMethods------------------------------------------------


    //-------------------------------------------------PrevDataSetUps------------------------------------------------

    private Bundle loadBio(Bundle b){
        b.putString("gender",SurveyActivity.gender);
        b.putString("metric",SurveyActivity.metric);
        b.putInt("weight",SurveyActivity.weight);
        b.putInt("height",SurveyActivity.height);
        b.putInt("fitnesslevel",SurveyActivity.fitnesslevel);
        b.putIntArray("goals",SurveyActivity.goals);
        b.putFloatArray("performance",SurveyActivity.performance);

        return b;
    }

    private Bundle loadData(Bundle b){
        b.clear();
        b.putString("username",userinfo[0]);
        b.putString("fullname",userinfo[1]);
        b.putString("email",userinfo[2]);
        b.putString("password",userinfo[3]);
        return b;
    }

    //-------------------------------------------------/PrevDataSetUps------------------------------------------------

    private void setUpToast () {

        Rect rectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;


        toast = new Toast(this);
        toast_view = getLayoutInflater().inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_layout));
        toast_text = toast_view.findViewById(R.id.custom_toast_message);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toast_view);
    }

    private boolean isBioLegit(){
        for (String s:
                userinfo) {
            if(s.equals("")) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        progressBar.setProgress(1);
        super.onDestroy();
    }
}
