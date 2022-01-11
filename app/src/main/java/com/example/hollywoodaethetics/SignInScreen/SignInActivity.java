package com.example.hollywoodaethetics.SignInScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hollywoodaethetics.HeatherBasedApplications.HeatherBasedActivity;
import com.example.hollywoodaethetics.R;
import com.example.hollywoodaethetics.SignInScreen.ViewModel.SignInViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity {
    SignInViewModel viewModel;
    Toast toast;
    View toast_view;
    TextView toast_text;

    @BindView(R.id.signin_input_login)
    EditText loginfield;
    @BindView(R.id.signin_input_password)
    EditText passfield;
    @BindView(R.id.signin_entry)
    Button entrybutton;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        viewModel.setActivitylinked(this);
        setUpToast();

        entrybutton.setOnClickListener(view -> {
            if((loginfield.getText().toString().length()==0)||(passfield.getText().toString().length()==0))
                ShowError(getString(R.string.error_fillthefields));
            else viewModel.signIn(loginfield.getText().toString(),passfield.getText().toString());
        }
        );

    }

    private void setUpToast () {

        Rect rectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;


        toast = new Toast(this);
        toast_view = getLayoutInflater().inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_layout));
        toast_text = toast_view.findViewById(R.id.custom_toast_message);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, statusBarHeight);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toast_view);
        entrybutton.setPressed(false);
    }

    public void ShowSuccess(){
        startActivity(new Intent(this, HeatherBasedActivity.class));
        finish();

    }

    public void ShowError(String message){
        toast_view.setBackgroundColor(Color.RED);
        toast_text.setText(message);
        toast.show();
        entrybutton.setPressed(false);
    }
}
