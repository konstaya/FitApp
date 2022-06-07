package com.example.fitapp.presenter;

import static com.example.fitapp.R.drawable.bad_training;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.fitapp.R;
import com.example.fitapp.model.FirebaseDatabaseHelper;
import com.example.fitapp.model.User;
import com.example.fitapp.view.PlanActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInfoPresenter {
    Context context;


    public UserInfoPresenter(Context context){
        this.context = context;
    }

    public void setPlanActivityInfo(RelativeLayout relativeLayout, TextView textView){
        new FirebaseDatabaseHelper(context).UserAuth(new FirebaseDatabaseHelper.UserStatus() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void DataIsLoaded(User user) {
                if (user.getBodyConstitution().equals("n")) {
                    relativeLayout.setBackground(context.getDrawable(R.drawable.card_normastenik));
                    textView.setText("Нормостеник");
                }
                else if (user.getBodyConstitution().equals("a")){
                    relativeLayout.setBackground(context.getDrawable(R.drawable.card_astenik));
                    textView.setText("Астеник");
                }
                else if (user.getBodyConstitution().equals("h")){
                    relativeLayout.setBackground(context.getDrawable(R.drawable.card_hyperstenik));
                    textView.setText("Гиперстеник");
                }

            }
        });

    }
    @SuppressLint("UseCompatLoadingForDrawables")
    public void setUserProfileInfo(TextView name, TextView text, ImageView imageView){
        new FirebaseDatabaseHelper(context).UserAuth(new FirebaseDatabaseHelper.UserStatus() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void DataIsLoaded(User user) {
                name.setText(user.getName());
            }
        });
        if (readLastDateDiff()>7) {
            text.setText(R.string.bad_training);
            imageView.setBackground(context.getDrawable(R.drawable.bad_training));
        }
        else{
            text.setText(R.string.good_training);
            imageView.setBackground(context.getDrawable(R.drawable.good_training));
        }
    }

    public void readProgress(TextView textView){
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(context);
        int progress = myPreferences.getInt("PROGRESS",0);

        textView.setText(String.valueOf(progress));

    }
    public void addProgress(){
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(context);
        int progress = myPreferences.getInt("PROGRESS",0)+1;
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.remove("PROGRESS");
        myEditor.putInt("PROGRESS", progress);
        myEditor.apply();

    }

    public void updatePlanDay(){
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(context);
        int planDay = myPreferences.getInt("PLANDAY", 0);
        if (planDay == 7) planDay = 0;
        else planDay++;
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.remove("PLANDAY");
        myEditor.putInt("PLANDAY", planDay);
        myEditor.apply();
    }
    public int readPlanDay(){
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(context);
        return myPreferences.getInt("PLANDAY", 0);
    }
    public void addLastDate(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date d = new Date();
        String s = dateFormat.format(d);
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.remove("LASTDAY");
        myEditor.putString("LASTDAY", s);
        myEditor.apply();

    }
    public int readLastDateDiff() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(context);
        String s = myPreferences.getString("LASTDAY", "");
        try {
            Date today = new Date();
            Date date = dateFormat.parse(s);
            long i = (today.getTime() - date.getTime());
            int days = (int) (i / (24 * 60 * 60 * 1000));
            return days;
        }
        catch (Exception e){
            return 90;
        }
    }

    public String readDayOfTrainig(boolean isFirst){
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(context);
        if (isFirst){
            String day1 = myPreferences.getString("DayOfTraining1","");
            Log.d("DAY", day1);
            return day1;
        }
        else {
            String day2 = myPreferences.getString("DayOfTraining2","");
            Log.d("DAY", day2);
            return day2;
        }
    }


}
