package com.example.fitapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.fitapp.R;
import com.example.fitapp.model.FirebaseDatabaseHelper;
import com.example.fitapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String email = myPreferences.getString("LOGIN", "");
        String password = myPreferences.getString("PASSWORD", "");
        if (!email.equals("")&!password.equals("")){

            UserLogIn(email, password);
        }
    }

    public void startRegistration(View v){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
    public void tr(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private void UserLogIn(String e, String p) {
        new FirebaseDatabaseHelper(MainActivity.this).UserLogIn(e, p, "Вход", new FirebaseDatabaseHelper.UserStatus() {
            @Override
            public void DataIsLoaded(User user) {

            }

            @Override
            public void UserLogin() {
                Intent intent = new Intent(MainActivity.this, PlanActivity.class);
                startActivity(intent);
            }
        });


    }
}