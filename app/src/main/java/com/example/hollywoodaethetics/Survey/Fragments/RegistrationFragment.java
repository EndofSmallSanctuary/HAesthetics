package com.example.hollywoodaethetics.Survey.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.Survey.SurveyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    @BindView(R.id.register_eye)
    ImageView eyeicon;
    @BindView(R.id.register_password)
    EditText password;
    @BindView(R.id.register_username)
    EditText username;
    @BindView(R.id.register_email)
    EditText email;
    @BindView(R.id.register_fullname)
    EditText fullname;

    EditText[] inputs;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


       View view = inflater.inflate(R.layout.fragment_registration, container, false);
        ButterKnife.bind(this,view);

        inputs =  new EditText[]{username,fullname,email,password};
        for (int i =0; i<inputs.length; i++){
            setUpOnEdit(inputs[i],i);
        }

        return  view;

    }

    private void setUpOnEdit(EditText e, int position){
        if(position==3) eyeCick();
        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                SurveyActivity.userinfo[position] = e.getText().toString().trim();
            }
        });
    }





    private void eyeCick(){
        eyeicon.setOnClickListener(view -> {
            if (this.password.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                Log.d("Dogs",password.getTransformationMethod().toString());
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
