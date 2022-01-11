package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;


import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Legendary.ViewModel.LegendaryViewModel;
import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.Utilities.Oreo_FilePath;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LegendaryeditActivity extends AppCompatActivity {

    private static int STORAGE_PERMISSION_CODE = 4655;
    private int PICK_IMAGE_RESULT = 1;
    private Uri filepath;
    private Bitmap imgbitmap;
    private LegendaryViewModel viewModel;
    private ArrayList<String> components = new ArrayList<>();
    private int chosenposition;
    private String tag;

    View toast_view;
    Toast toast;
    TextView toast_text;

    @BindView(R.id.legend_toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_legend_height)
    NumberPicker height;
    @BindView(R.id.edit_legend_weight)
    NumberPicker weight;
    @BindView(R.id.edit_legend_name)
    EditText name;
    @BindView(R.id.edit_legend_info)
    EditText info;
    @BindView(R.id.edit_legend_save)
    TextView savebutton;
    @BindView(R.id.edit_legend_prev)
    ImageView prev;



    @BindView(R.id.edit_legend_Actor)
   ConstraintLayout actor;
    @BindView(R.id.edit_legend_Wrestler)
   ConstraintLayout wrestler;
    @BindView(R.id.edit_legend_Athlete)
   ConstraintLayout athelete;
    @BindView(R.id.edit_legend_Other)
   ConstraintLayout other;

    ConstraintLayout[] activities;

    //PhysiqueBuilder
    @BindView(R.id.legend_physiquemodel_builderfield)
    EditText descript;
    @BindView(R.id.legend_physiquemodel_neck)
    ImageView neck;
    @BindView(R.id.legend_physiquemodel_abs)
    ImageView abs;
    @BindView(R.id.legend_physiquemodel_chest)
    ImageView chestS;
    @BindView(R.id.legend_physiquemodel_back)
    ImageView back;
    @BindView(R.id.legend_physiquemodel_leftarm)
    ImageView leftarm;
    @BindView(R.id.legend_physiquemodel_leftarmback)
    ImageView leftarmback;
    @BindView(R.id.legend_physiquemodel_rightarm)
    ImageView rightarm;
    @BindView(R.id.legend_physiquemodel_rightarmback)
    ImageView rightarmback;
    @BindView(R.id.legend_physiquemodel_legleft)
    ImageView legleft;
    @BindView(R.id.legend_physiquemodel_leftlegback)
    ImageView legleftback;
    @BindView(R.id.legend_physiquemodel_rightleft)
    ImageView rightleg;
    @BindView(R.id.legend_physiquemodel_rightlegback)
    ImageView rightlegback;

    @Override
    protected void onStart() {
        viewModel = new ViewModelProvider(this).get(LegendaryViewModel.class);
        viewModel.setActivityLinked(this);
        super.onStart();
    }

    @Override
    protected void onResume() {

        prev.setOnClickListener(view -> {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                showFileChooser();
            } else storagePermission();
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                                                 @Override public void onClick(View view) {
                                                     onBackPressed(); }
                                             }
        );

        savebutton.setOnClickListener(view -> {
            savebutton.setPressed(true);
            if ((name.getText().length()>2) && (info.getText().length()>2) && (filepath!=null) && (tag!=null)){
                viewModel.setAesthericsResponceToSend(
                        getSharedPreferences("UserLocals",Context.MODE_PRIVATE).getString("id",""),
                        name.getText().toString().trim(),
                        info.getText().toString().trim(),"",
                        componentsConverter(components),
                        height.getValue(),
                        weight.getValue(),
                        tag,absolutePath());
            } else ShowError(getString(R.string.error_fillthefields));
        });

        if(getSharedPreferences("UserLocals", Context.MODE_PRIVATE).getString("metric","").equals("metric")){
            height.setMinValue(140);
            height.setMaxValue(220);
            height.setValue(175);
            height.computeScroll();

            weight.setMinValue(50);
            weight.setMaxValue(140);
            weight.setValue(70);
            weight.computeScroll();
        } else {
            height.setMinValue(49);
            height.setMaxValue(67);
            height.setValue(59);
            height.computeScroll();

            weight.setMinValue(140);
            weight.setMaxValue(308);
            weight.setValue(163);
            weight.computeScroll();
        }

        descript.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String substring = charSequence.toString().toLowerCase();
                if(substring.contains("chest") || substring.contains("shoulde") ) {
                    if (chestS.getVisibility() == View.GONE) {
                        chestS.setVisibility(View.VISIBLE);
                        components.add("chest");
                    }
                } else if(chestS.getVisibility() == View.VISIBLE) {
                    chestS.setVisibility(View.GONE);
                    components.remove(components.indexOf("chest"));
                }
                if(substring.contains("arms")) {
                    if (leftarm.getVisibility() == View.GONE) {
                        leftarm.setVisibility(View.VISIBLE);
                        leftarmback.setVisibility(View.VISIBLE);
                        rightarm.setVisibility(View.VISIBLE);
                        rightarmback.setVisibility(View.VISIBLE);
                        components.add("arms");
                    }
                } else if(leftarmback.getVisibility() == View.VISIBLE) {
                    leftarm.setVisibility(View.GONE);
                    leftarmback.setVisibility(View.GONE);
                    rightarm.setVisibility(View.GONE);
                    rightarmback.setVisibility(View.GONE);
                    components.remove(components.indexOf("arms"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legendary_edit);
        ButterKnife.bind(this);
        setUpToast();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        activities =  new ConstraintLayout[]{actor,wrestler,athelete,other};
        for (int i=0; i<activities.length; i++) {
            SetTagListener(activities[i],i);
        }


    }

    private String componentsConverter(ArrayList<String> components){
        String result = "";
        if(components.size()==0) return result;
        for (String s:
                components ) {
            result+=s+',';

        }

        if(result!="") result = result.substring(0,result.length()-1);
        return result;

    };

    private void HideAllBut(){
        for(int i=0; i<activities.length;i++){
            if (i!=chosenposition){
                activities[i].setBackgroundResource(R.drawable.offer_unselected);
            }
        }
    }

    private void SetTagListener(ConstraintLayout layout, int position){
        layout.setOnClickListener(view -> {
                    layout.setBackgroundResource(R.drawable.offer_selected);
                    String[] dividedlayoutname = getResources().getResourceName(layout.getId()).split("_");
                    tag = dividedlayoutname[dividedlayoutname.length-1];
                    chosenposition = position;
                    HideAllBut();
                }
        );
    }

    private void storagePermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.d("DOGS", "granted");
            return;
        }

        Log.d("DOGS", "not granted");
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==STORAGE_PERMISSION_CODE){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            } else {
            }
        }
    }

    private void showFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Complete action using"),PICK_IMAGE_RESULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_RESULT && data!=null && data.getData()!=null){
            filepath=data.getData();
            try {
                    imgbitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filepath);
                    prev.setImageBitmap(imgbitmap);

            } catch (Exception e){ }

        }
    }

    private String absolutePath(){
        return Oreo_FilePath.getRealPath(this,filepath);
    }

    private void setUpToast(){
        toast = new Toast(this);
        toast_view = getLayoutInflater().inflate(R.layout.custom_toast,
                (ViewGroup)findViewById(R.id.custom_toast_layout));
        toast_text = toast_view.findViewById(R.id.custom_toast_message);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP,0,this.getResources().getDimensionPixelSize(R.dimen.toolbar_size));
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toast_view);
        savebutton.setPressed(false);
    }

    public void ShowSuccess(String message){
//        Log.d("Dogs","It was a success");
//        toast_view.setBackgroundColor(getColor(R.color.main));
//        toast_text.setText(message);
//        toast.show();
        savebutton.setPressed(false);
      onBackPressed();
    }

    public void ShowError(String message){
        toast_view.setBackgroundColor(Color.RED);
        toast_text.setText(message);
        savebutton.setPressed(false);
        toast.show();
    }



}
