package com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.hollywoodaethetics.HeatherBasedApplications.Part_Profile.ViewModel.ProfileViewModel;
import com.example.hollywoodaethetics.LogInScreen.LogInActivity;
import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.Utilities.Oreo_FilePath;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileEditActivity extends AppCompatActivity {

    Intent intent;

    SharedPreferences preferences;
    String metric;
    String prevusername;
    String prevemail;
    View toast_view;
    Toast toast;
    TextView toast_text;

    @BindView(R.id.edit_profile_action_logout)
    TextView logout;
    @BindView(R.id.edit_profile_save)
    TextView savebutton;
    @BindView(R.id.edit_profile_toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_profile_image)
    ImageView avatar;
    @BindView(R.id.edit_profile_height)
    NumberPicker heightpicker;
    @BindView(R.id.edit_profile_weight)
    NumberPicker weightpicker;
    @BindView(R.id.edit_profile_image_icon)
    ImageView avatareditbutton;
    @BindView(R.id.edit_profile_background)
    ImageView backgroundeditbutton;
    @BindView(R.id.edit_profile_backgroundholder)
    ImageView background;
    @BindView(R.id.edit_profile_name)
    EditText profilename;
    @BindView(R.id.edit_profile_email)
    EditText email;
    @BindView(R.id.edit_profile_fullname)
    EditText fullname;
    @BindView(R.id.edit_profile_physique)
    EditText physique;
    @BindView(R.id.edit_profile_split)
    EditText split;
    @BindView(R.id.edit_profile_incline)
    EditText incline;
    @BindView(R.id.edit_profile_chinups)
    EditText chinups;
    @BindView(R.id.edit_profile_deadlift)
    EditText deadlift;
    @BindView(R.id.edit_profile_press)
    EditText press;

    private static int STORAGE_PERMISSION_CODE = 4655;
    private int PICK_IMAGE_RESULT = 1;
    private int AVATAR_SWITCH;
    private Uri filepath;
    private Bitmap imgbitmap;
    private ProfileViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        //ПОРАБОТАТЬ НАД МЕТОДАМИ, В ТОМ ЧИСЛЕ ЧЕК И ТД. ВЫНЕСТИ СМЕНУ ЮЗЕРНЕЙМА И ЭМЕЙЛА В ОТДЕЛЬНЫЙ СПИСОК
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        viewModel.setActivitylinked(this,intent.getStringExtra("id"));
        metric = intent.getStringExtra("metric");
        prevusername = intent.getStringExtra("username");
        prevemail = intent.getStringExtra("email");
        //prevemail = "Test";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setUpToast();

        Glide.with(this).load(intent.getStringExtra("avatar"))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(avatar);

        if(!intent.getStringExtra("background").equals("")) {
            Glide.with(this).load(intent.getStringExtra("background"))
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(background);
//            Glide.with(this).load(intent.getStringExtra("background"))
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
//                    .into(new CustomTarget<Drawable>() {
//                        @Override
//                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                            background.setBackground(resource);
//                        }
//
//                        @Override
//                        public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                        }
//                    });
        }


        avatareditbutton.setOnClickListener(view1 -> {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                AVATAR_SWITCH = 1;
                showFileChooser();
            } else storagePermission();
        });
        backgroundeditbutton.setOnClickListener(view1 -> {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                AVATAR_SWITCH = 0;
                showFileChooser();
            } else storagePermission();
        });
        savebutton.setOnClickListener(view -> {
            savebutton.setPressed(true);
            if(prevemail.equals(email.getText().toString()) && (prevusername.equals(profilename.getText().toString())))
            {
                viewModel.updateprofile(profilename.getText().toString(), fullname.getText().toString()
                        , heightpicker.getValue(), weightpicker.getValue(), email.getText().toString()
                        , Float.parseFloat(incline.getText().toString()), Float.parseFloat(chinups.getText().toString())
                        , Float.parseFloat(deadlift.getText().toString()), Float.parseFloat(press.getText().toString()), false);
            } else {
                if(intent.getStringExtra("metric").equals("metric")) {
                    viewModel.updateprofile(profilename.getText().toString(), fullname.getText().toString()
                            , heightpicker.getValue(), weightpicker.getValue(), email.getText().toString()
                            , Float.parseFloat(incline.getText().toString()), Float.parseFloat(chinups.getText().toString())
                            , Float.parseFloat(deadlift.getText().toString()), Float.parseFloat(press.getText().toString()), true);
                } else {
                    viewModel.updateprofile(profilename.getText().toString(), fullname.getText().toString()
                            , heightpicker.getValue(), weightpicker.getValue(), email.getText().toString()
                            , Float.parseFloat(incline.getText().toString()) / 2.2f, Float.parseFloat(chinups.getText().toString()) / 2.2f
                            , Float.parseFloat(deadlift.getText().toString()) / 2.2f, Float.parseFloat(press.getText().toString()) / 2.2f, true);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        setUpPickers();
        profilename.setText(prevusername);
        fullname.setText(intent.getStringExtra("fullname"));
        physique.setText(intent.getStringExtra("physique"));
        split.setText(intent.getStringExtra("split"));
        email.setText(prevemail);

        incline.setText(Float.toString(intent.getFloatExtra("incline",0f)));
        chinups.setText(Float.toString(intent.getFloatExtra("chinups",0f)));
        deadlift.setText(Float.toString(intent.getFloatExtra("deadlift",0f)));
        press.setText(Float.toString(intent.getFloatExtra("press",0f)));
        logout.setOnClickListener(view -> {
            getSharedPreferences("UserLocals",Context.MODE_PRIVATE).edit().putBoolean("loginstate",false).apply();
            startActivity(new Intent(this, LogInActivity.class));
            finish();
        });

        super.onResume();
    }

    private void setUpPickers() {
        if(metric.equals("metric")) {

            heightpicker.setMinValue(140);
            heightpicker.setMaxValue(220);
            heightpicker.setValue(intent.getIntExtra("height",0));
            heightpicker.computeScroll();

            weightpicker.setMinValue(50);
            weightpicker.setMaxValue(140);
            weightpicker.setValue(intent.getIntExtra("weight",0));
            weightpicker.computeScroll();

        } else {

            heightpicker.setMinValue(49);
            heightpicker.setMaxValue(67);
            heightpicker.setValue(intent.getIntExtra("height",0));
            heightpicker.computeScroll();

            weightpicker.setMinValue(140);
            weightpicker.setMaxValue(308);
            weightpicker.setValue(intent.getIntExtra("weight",0));
            weightpicker.computeScroll();
        }
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
        toast_view.setBackgroundColor(getColor(R.color.main));
        toast_text.setText(message);

//        TSnackbar snack = TSnackbar.make(findViewById(android.R.id.content),"TEST",TSnackbar.LENGTH_SHORT);
//        View view = snack.getView();
//        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)view.getLayoutParams();
//        params.gravity = Gravity.CENTER_HORIZONTAL|Gravity.TOP;
//
//        TypedValue typedValue  = new TypedValue();
//        int abrh= 0;
//        if (getTheme().resolveAttribute(R.attr.actionBarSize,typedValue,true)){
//                abrh = TypedValue.complexToDimensionPixelSize(typedValue.data,getResources().getDisplayMetrics());
//        }
//        params.setMargins(0,abrh,0,0);
//        view.setLayoutParams(params);
//        snack.show();
        toast.show();
        savebutton.setPressed(false);
    }

    public void ShowError(String message){
        toast_view.setBackgroundColor(Color.RED);
        toast_text.setText(message);
        toast.show();
    }

    //-Avatar send section
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
            Log.d("DOGS",filepath.toString());
            try {
                if(AVATAR_SWITCH==1) {
                    imgbitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filepath);
                    avatar.setImageBitmap(imgbitmap);
                    SharedPreferences preferences = this.getSharedPreferences("UserLocals", Context.MODE_PRIVATE);
                    viewModel.updateavatar(
                            intent.getStringExtra("username"),
                            intent.getStringExtra("id"),
                            absolutePath());
                } else if(AVATAR_SWITCH==0){
                    imgbitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filepath);
                    background.setImageBitmap(imgbitmap);
                    getSharedPreferences("UserLocals",Context.MODE_PRIVATE).edit().putBoolean("backgroundcache",false).apply();
                    viewModel.updatebackground(
                            intent.getStringExtra("username"),
                            intent.getStringExtra("id"),
                            absolutePath());

                }



            } catch (Exception e){ }

        }
    }

    private String absolutePath(){
        return Oreo_FilePath.getRealPath(this,filepath);

    }

    @Override
    protected void onPause() {
        if (toast!=null) toast.cancel();
        super.onPause();
    }
}
