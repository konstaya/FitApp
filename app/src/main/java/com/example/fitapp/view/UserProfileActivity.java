package com.example.fitapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fitapp.R;
import com.example.fitapp.presenter.UserInfoPresenter;

public class UserProfileActivity extends AppCompatActivity {
    TextView name, text, pageText;
    RelativeLayout rl;
    Button add;
    ImageView image, btnSettings;
    private FragmentContainerView settingsView, addView;
    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        init();

        new UserInfoPresenter(UserProfileActivity.this).setUserProfileInfo(name, text, image);
    }
    private void init(){
        name = findViewById(R.id.tv_user_name);
        text = findViewById(R.id.tv_training_motivation);
        pageText = findViewById(R.id.tv_profile);
        image = findViewById(R.id.image_motivation);
        settingsView = findViewById(R.id.settingsFrag);
        btnSettings = findViewById(R.id.btn_settings);
        rl = findViewById(R.id.rl_motivation_text);
        add = findViewById(R.id.btn_add_new);
        addView = findViewById(R.id.addFragment);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingController();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewParams();
            }
        });
    }

    private void addNewParams() {
        add.setVisibility(View.GONE);
        addView.setVisibility(View.VISIBLE);


    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void settingController() {
        if (flag){
            settingsView.setVisibility(View.GONE);
            pageText.setText("Профиль");
            btnSettings.setImageDrawable(this.getDrawable(R.drawable.setting));
            rl.setVisibility(View.VISIBLE);
            add.setVisibility(View.VISIBLE);
            flag = false;
        }
        else{
            settingsView.setVisibility(View.VISIBLE);
            pageText.setText("Настройки");
            btnSettings.setImageDrawable(this.getDrawable(R.drawable.cross));
            rl.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
            flag = true;
        }

    }

    @Override
    public void onBackPressed() {
        if (flag) settingController();
        else super.onBackPressed();
    }
}