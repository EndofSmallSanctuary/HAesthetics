package com.example.hollywoodaethetics.Survey.Fragments.ProfilePictureFragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.Survey.SurveyActivity;
import com.example.hollywoodaethetics.Utilities.Oreo_FilePath;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePictureFragment extends Fragment {

    private static int STORAGE_PERMISSION_CODE = 4655;
    private int PICK_IMAGE_RESULT = 1;
    private Uri filepath;
    private Bitmap imgbitmap;
    private Boolean def=true;
    InputStream inputStream;

    @BindView(R.id.provile_avatar_prev)
    ImageView profileprev;
    @BindView(R.id.provile_avatar_edit)
    FloatingActionButton button;

    public ProfilePictureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_profile_picture, container, false);
        ButterKnife.bind(this,view);
        storagePermission();
        if(SurveyActivity.gender=="Male"){
            profileprev.setImageDrawable(getResources().getDrawable(R.drawable.no_avatar_male,null));

        } else profileprev.setImageDrawable(getResources().getDrawable(R.drawable.no_avatar_female,null));
       // storagePermission();
        button.setOnClickListener(view1 -> {
            if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                showFileChooser();
            } else storagePermission();
        });


       return view;
    }

    private void storagePermission(){
        Log.d("DOGS",getActivity().toString());
        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
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
               Toasty.success(getActivity(),"Permission Granted",Toasty.LENGTH_SHORT).show();
           } else {
               Toasty.error(getActivity(),"Permission Denied",Toasty.LENGTH_SHORT).show();
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
                imgbitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),filepath);
                this.def = false;
                profileprev.setImageBitmap(imgbitmap);
                SurveyActivity.avatarpath=absolutePath();

            } catch (Exception e){ }

        }
    }

    private String absolutePath(){
        Log.d("Avatar", Oreo_FilePath.getRealPath(getActivity(),filepath));
      return Oreo_FilePath.getRealPath(getActivity(),filepath);

    }

    @Override
    public void onDetach() {
        Log.d("Def",def.toString());
        if(def) SurveyActivity.avatarpath = "No";
        super.onDetach();
    }
}
