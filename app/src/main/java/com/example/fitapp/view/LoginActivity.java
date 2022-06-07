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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fitapp.R;
import com.example.fitapp.model.FirebaseDatabaseHelper;
import com.example.fitapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    Button signIn;
    ImageView btnExit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.editTextSignInEmail);
        password = (EditText) findViewById(R.id.editTextSignInPassword);
        signIn = (Button) findViewById(R.id.btn_signIn);
        btnExit = (ImageView) findViewById(R.id.btnSignIn_exit);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLogIn();
                addlogin(email.getText().toString().trim(), password.getText().toString().trim());
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);

        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.remove("DayOfTraining1");
        myEditor.remove("DayOfTraining2");
        myEditor.remove("PLANDAY");
        myEditor.putString("DayOfTraining1", "Tuesday");
        myEditor.putString("DayOfTraining2", "Friday");
        myEditor.apply();

    }
    private void addlogin(String email, String password){
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);

        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.remove("LOGIN");
        myEditor.remove("PASSWORD");
        myEditor.putString("LOGIN", email);
        myEditor.putString("PASSWORD", password);
        myEditor.apply();
    }
    private void UserLogIn() {
        String e = email.getText().toString().trim();
        String p = password.getText().toString().trim();

        if (e.isEmpty()){
            email.setError("");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
            email.setError("");
            email.requestFocus();
            return;
        }
        if (p.isEmpty()){
            password.setError("");
            password.requestFocus();
            return;
        }

        new FirebaseDatabaseHelper(LoginActivity.this).UserLogIn(e, p, "Авторизация", new FirebaseDatabaseHelper.UserStatus() {
            @Override
            public void DataIsLoaded(User user) {

            }

            @Override
            public void UserLogin() {
                Intent intent = new Intent(LoginActivity.this, PlanActivity.class);
                startActivity(intent);
            }
        });


    }
}