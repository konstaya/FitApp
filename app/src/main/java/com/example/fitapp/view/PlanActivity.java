package com.example.fitapp.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitapp.R;
import com.example.fitapp.presenter.DataConfig;
import com.example.fitapp.presenter.UserInfoPresenter;

public class PlanActivity extends AppCompatActivity {

    ImageView btn_profile;
    Button btn_srt_training;
    TextView progress, bodyConstText;
    RelativeLayout bodyConst;
    TextView day1, day2, day3, day4,day5,day6, day7;

     String day = "";
    String d1_rus = "",d2_rus = "", d1 = "", d2 = "";
    int number_of_day = 0;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        init();
        new UserInfoPresenter(PlanActivity.this).setPlanActivityInfo(bodyConst, bodyConstText);
        //new UserInfoPresenter(PlanActivity.this).write();
        new UserInfoPresenter(PlanActivity.this).readProgress(progress);
        //new UserInfoPresenter(PlanActivity.this).addLastDate();
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(PlanActivity.this);

        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.remove("DayOfTraining1");
        myEditor.remove("DayOfTraining2");
        myEditor.remove("LASTDAY");
        myEditor.putString("DayOfTraining1", "Tuesday");
        myEditor.putString("DayOfTraining2", "Friday");
        myEditor.apply();



        btn_srt_training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTrain();
            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stsrtProfile();
            }
        });
    }

    @Override
    protected void onStart() {
        setRestIsNeeded();
        super.onStart();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {

            if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("OK-LOG", "Activity result canceled");

                new UserInfoPresenter(PlanActivity.this).readProgress(progress);
                setRestIsNeeded();
            }
        }
    } //onActivityResult

    @SuppressLint("UseCompatLoadingForDrawables")
    private void init(){
        btn_profile = findViewById(R.id.btn_progileP);
        btn_srt_training = findViewById(R.id.btn_srt_training);
        bodyConst = findViewById(R.id.RL_body_constitution);
        progress = findViewById(R.id.textView_progress);
        bodyConstText = findViewById(R.id.textView_body);

        day1 = findViewById(R.id.monDay);
        day2 = findViewById(R.id.tuesDay);
        day3 = findViewById(R.id.wednesDay);
        day4 = findViewById(R.id.thursDay);
        day5 = findViewById(R.id.friDay);
        day6 = findViewById(R.id.saturDay);
        day7 = findViewById(R.id.sunDay);
        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose(1);
            }
        });
        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose(2);
            }
        });
        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose(3);
            }
        });
        day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose(4);
            }
        });
        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose(5);
            }
        });
        day6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose(6);
            }
        });
        day7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose(7);
            }
        });
        Log.i("LOG-diff", String.valueOf(new UserInfoPresenter(PlanActivity.this).readLastDateDiff()));
        Log.i("LOG-today", DataConfig.GetCurrentDay());

        data().setBackground(getDrawable(R.drawable.circle_calendar));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void choose(int i) {

        switch (i){
            case 1:
                day1.setBackground(getDrawable(R.drawable.circle_calendar_view));
                day2.setBackgroundResource(0);day3.setBackgroundResource(0);day4.setBackgroundResource(0);
                day5.setBackgroundResource(0);day6.setBackgroundResource(0);day7.setBackgroundResource(0);
                if (data() == day1) data().setBackground(getDrawable(R.drawable.circle_calendar));
                else  data().setBackground(getDrawable(R.drawable.circle_today));
                changeDay(day1, 1, "Monday");
                break;
            case 2:
                day2.setBackground(getDrawable(R.drawable.circle_calendar_view));
                day1.setBackgroundResource(0);day3.setBackgroundResource(0);day4.setBackgroundResource(0);
                day5.setBackgroundResource(0);day6.setBackgroundResource(0);day7.setBackgroundResource(0);
                if (data() == day2) data().setBackground(getDrawable(R.drawable.circle_calendar));
                else  data().setBackground(getDrawable(R.drawable.circle_today));
                changeDay(day2, 2, "Tuesday");
                break;
            case 3:
                day3.setBackground(getDrawable(R.drawable.circle_calendar_view));
                day1.setBackgroundResource(0);day2.setBackgroundResource(0);day4.setBackgroundResource(0);
                day5.setBackgroundResource(0);day6.setBackgroundResource(0);day7.setBackgroundResource(0);
                if (data() == day3) data().setBackground(getDrawable(R.drawable.circle_calendar));
                else  data().setBackground(getDrawable(R.drawable.circle_today));
                changeDay(day3, 3, "Wednesday");
                break;
            case 4:
                day4.setBackground(getDrawable(R.drawable.circle_calendar_view));
                day1.setBackgroundResource(0);day2.setBackgroundResource(0);day3.setBackgroundResource(0);
                day5.setBackgroundResource(0);day6.setBackgroundResource(0);day7.setBackgroundResource(0);
                if (data() == day4) data().setBackground(getDrawable(R.drawable.circle_calendar));
                else  data().setBackground(getDrawable(R.drawable.circle_today));
                changeDay(day4, 4, "Thursday");
                break;
            case 5:
                day5.setBackground(getDrawable(R.drawable.circle_calendar_view));
                day1.setBackgroundResource(0);day2.setBackgroundResource(0);day3.setBackgroundResource(0);
                day4.setBackgroundResource(0);day6.setBackgroundResource(0);day7.setBackgroundResource(0);
                if (data() == day5) data().setBackground(getDrawable(R.drawable.circle_calendar));
                else  data().setBackground(getDrawable(R.drawable.circle_today));
                changeDay(day5, 5, "Friday");
                break;
            case 6:
                day6.setBackground(getDrawable(R.drawable.circle_calendar_view));
                day1.setBackgroundResource(0);day2.setBackgroundResource(0);day3.setBackgroundResource(0);
                day4.setBackgroundResource(0);day5.setBackgroundResource(0);day7.setBackgroundResource(0);
                if (data() == day6) data().setBackground(getDrawable(R.drawable.circle_calendar));
                else  data().setBackground(getDrawable(R.drawable.circle_today));
                changeDay(day6, 6, "Saturday");
                break;
            case 7:
                day7.setBackground(getDrawable(R.drawable.circle_calendar_view));
                day1.setBackgroundResource(0);day3.setBackgroundResource(0);day4.setBackgroundResource(0);
                day5.setBackgroundResource(0);day6.setBackgroundResource(0);day2.setBackgroundResource(0);
                if (data() == day7) data().setBackground(getDrawable(R.drawable.circle_calendar));
                else  data().setBackground(getDrawable(R.drawable.circle_today));
                changeDay(day7, 7, "Sunday");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }


    }

    private TextView data(){
        String today = DataConfig.GetCurrentDay();
        day = today;
        if (day.equals("понедельник") | day.equals("Monday")) { number_of_day = 1; return day1;}
        else if (day.equals("вторник")| day.equals("Tuesday")) { number_of_day = 2; return day2;}
        else if (day.equals("среда")| day.equals("Wednesday")) { number_of_day = 3; return day3;}
        else if (day.equals("четверг")| day.equals("Thursday")) { number_of_day = 4; return day4;}
        else if (day.equals("пятница")| day.equals("Friday")) { number_of_day = 5; return day5;}
        else if (day.equals("суббота")| day.equals("Saturday")) { number_of_day = 6; return day6;}
        else { number_of_day = 7; return day7;}
    }

    private void setRestIsNeeded(){
        UserInfoPresenter userInfoPresenter = new UserInfoPresenter(PlanActivity.this);
        d1 = userInfoPresenter.readDayOfTrainig(true);
        d2 = userInfoPresenter.readDayOfTrainig(false);
        if (d1.equals("Monday")) d1_rus = "понедельник";
        else if (d1.equals("Tuesday")) d1_rus = "вторник";
        else if (d1.equals("Wednesday")) d1_rus = "среда";
        else if (d1.equals("Thursday")) d1_rus = "четверг";
        else if (d1.equals("Friday")) d1_rus = "пятница";
        else if (d1.equals("Saturday")) d1_rus = "суббота";

        if (d2.equals("Monday")) d2_rus = "понедельник";
        else if (d2.equals("Tuesday")) d2_rus = "вторник";
        else if (d2.equals("Wednesday")) d2_rus = "среда";
        else if (d2.equals("Thursday")) d2_rus = "четверг";
        else if (d2.equals("Friday")) d2_rus = "пятница";
        else if (d2.equals("Saturday")) d2_rus = "суббота";

        // если сегодня уже была тренировка
        if ((!d1.equals(day))&(!d2.equals(day))&(!d1_rus.equals(day))&(!d2_rus.equals(day))){
            btn_srt_training.setEnabled(false);
            btn_srt_training.setText("ОТДЫХ!");
            btn_srt_training.setBackgroundColor(getColor(R.color.background_light_grey_purple));
        }
        else if (userInfoPresenter.readLastDateDiff()==0){
            btn_srt_training.setEnabled(false);

            btn_srt_training.setText("Вы хорошо потренировались");
            btn_srt_training.setBackgroundColor(getColor(R.color.background_light_grey_purple));
        }

    }

    private void changeDay(TextView textView, int i, String str_day){
        if ((d1.equals(str_day)) || (d2.equals(str_day))){
            if (number_of_day<i){
                btn_srt_training.setText("Назначена тренировка");
                btn_srt_training.setBackgroundColor(getColor(R.color.background_light_grey_purple));
                btn_srt_training.setEnabled(false);
            }
            else if (number_of_day>i){
                btn_srt_training.setText("Была тренировка");
                btn_srt_training.setBackgroundColor(getColor(R.color.background_light_grey_purple));
                btn_srt_training.setEnabled(false);
            }
            else {

                btn_srt_training.setText("Начать тренировку");
                btn_srt_training.setEnabled(true);
                btn_srt_training.setBackgroundColor(getColor(R.color.pink));
            }
        }
        else {
            btn_srt_training.setText("Отдых");
            btn_srt_training.setEnabled(false);
            btn_srt_training.setBackgroundColor(getColor(R.color.background_light_grey_purple));
        }
        if (textView == data()) setRestIsNeeded();
    }

    private void stsrtProfile() {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    public void startTrain(){
        Intent intent = new Intent(this, TrainingActivity.class);
        int LAUNCH_TRAIN_ACTIVITY = 1;
        startActivityForResult(intent, LAUNCH_TRAIN_ACTIVITY);
    }
    
}