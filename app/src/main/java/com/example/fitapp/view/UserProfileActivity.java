package com.example.fitapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitapp.R;
import com.example.fitapp.presenter.UserInfoPresenter;

public class UserProfileActivity extends AppCompatActivity {
    TextView name, text;
    Button add;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name = findViewById(R.id.tv_user_name);
        text = findViewById(R.id.tv_training_motivation);
        image = findViewById(R.id.image_motivation);
        new UserInfoPresenter(UserProfileActivity.this).setUserProfileInfo(name, text, image);
    }
}