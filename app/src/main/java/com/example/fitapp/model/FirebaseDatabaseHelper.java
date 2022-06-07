package com.example.fitapp.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.fitapp.model.Exercise;
import com.example.fitapp.presenter.TrainingPresenter;
import com.example.fitapp.view.LoginActivity;
import com.example.fitapp.view.MainActivity;
import com.example.fitapp.view.PlanActivity;
import com.example.fitapp.view.Registration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceExercise;
    private DatabaseReference mUserReference;
    private FirebaseAuth firebaseAuth;


    private List<Exercise> exs = new ArrayList<>();
    private Context context;

    public interface DataStatus{
        void DataIsLoaded(List<Exercise> exercises );
    }
    public interface UserStatus{
        void DataIsLoaded(User user);
        void UserLogin();
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance("https://fitness-app-taya-default-rtdb.asia-southeast1.firebasedatabase.app/");
        mReferenceExercise = mDatabase.getReference("Exercises");
        mUserReference = mDatabase.getReference("Users");
    }

    public FirebaseDatabaseHelper(Context context) {
        mDatabase = FirebaseDatabase.getInstance("https://fitness-app-taya-default-rtdb.asia-southeast1.firebasedatabase.app/");
        mReferenceExercise = mDatabase.getReference("Exercises");
        mUserReference = mDatabase.getReference("Users");

        //initialize the FirebaseAuth instance.
        firebaseAuth = FirebaseAuth.getInstance();
        this.context = context;
    }

    public void UserRegistration(User newUser, String password){
        ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("Регистрация");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(newUser.getEmail(),password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseDatabase.getInstance("https://fitness-app-taya-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){

                                        Toast.makeText(context, "Вы успешно зарегистрировались!", Toast.LENGTH_LONG).show();

                                    }
                                    else Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        else Toast.makeText(context, "Ой, что-то пошло не так", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.remove("DayOfTraining1");
        myEditor.remove("DayOfTraining2");
        myEditor.putString("DayOfTraining1", "Wednesday");
        myEditor.putString("DayOfTraining2", "Friday");
        myEditor.apply();
    }

    public void UserLogIn(String email, String password, String loadText, UserStatus datastatus){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setTitle(loadText);
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Toast.makeText(context, "Добро пожаловать", Toast.LENGTH_SHORT).show();
                    datastatus.UserLogin();
                }
                else{
                    Toast.makeText(context, "Что-то пошло не так!", Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();
            }

        });
    }

    public void UserDatabase(UserStatus dataStatus){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String userId = user.getUid();

        mUserReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userProfile = dataSnapshot.getValue(User.class);
                if (userProfile != null){
                    dataStatus.DataIsLoaded(userProfile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void read(DataStatus dataStatus){
        ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("Загружаем упражнения");
        progressDialog.show();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                exs.clear();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()){
                    if(keyNode!=null){
                        for(DataSnapshot keyChildNode:keyNode.getChildren()){
                            if(keyChildNode!=null){
                                Exercise exercise=keyChildNode.getValue(Exercise.class);
                                exs.add(exercise);
                            }
                        }
                    }

                }
                dataStatus.DataIsLoaded(exs);
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mReferenceExercise.addValueEventListener(valueEventListener);

    }
    public void readExercises(DataStatus dataStatus, String type) {

        mReferenceExercise.child(type).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                exs.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Exercise exercise = keyNode.getValue(Exercise.class);
                    exs.add(exercise);

                }
                dataStatus.DataIsLoaded(exs);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void readWarmCool(DataStatus dataStatus, String type){
        List<Exercise> e = new ArrayList<>();
        mDatabase.getReference(type).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                e.clear();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    Exercise exercise = keyNode.getValue(Exercise.class);
                    e.add(exercise);
                }
                dataStatus.DataIsLoaded(e);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
