package com.example.fitapp.view;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.fitapp.R;
import com.example.fitapp.model.FirebaseDatabaseHelper;
import com.example.fitapp.model.User;
import com.example.fitapp.presenter.DataConfig;
import com.example.fitapp.presenter.UserInfoPresenter;
import com.google.firebase.database.DataSnapshot;

public class PlanActivity extends AppCompatActivity {

    ImageView btn_profile;
    Button btn_srt_training;
    TextView progress, bodyConstText;
    RelativeLayout bodyConst;
    TextView day1, day2, day3, day4,day5,day6, day7;

     String day = "";

    
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
        myEditor.remove("LASTDAY");
        myEditor.putString("LASTDAY", "01.05.2022");

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
        Log.i("LOG-diff", String.valueOf(new UserInfoPresenter(PlanActivity.this).readLastDateDiff()));
        Log.i("LOG-today", DataConfig.GetCurrentDay());

        data().setBackground(getDrawable(R.drawable.circle_calendar));
    }

    private TextView data(){
        String today = DataConfig.GetCurrentDay();
        day = today;
        if (day.equals("понедельник") | day.equals("Monday"))  return day1;
        else if (day.equals("вторник")| day.equals("Tuesday")) return day2;
        else if (day.equals("среда")| day.equals("Wednesday")) return day3;
        else if (day.equals("четверг")| day.equals("Thursday")) return day4;
        else if (day.equals("пятница")| day.equals("Friday")) return day5;
        else if (day.equals("суббота")| day.equals("Saturday")) return day6;
        else return day7;
    }

    private void setRestIsNeeded(){
        UserInfoPresenter userInfoPresenter = new UserInfoPresenter(PlanActivity.this);
        String d1 = userInfoPresenter.readDayOfTrainig(true);
        String d2 = userInfoPresenter.readDayOfTrainig(false);
        String d1_rus = "",d2_rus = "";
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
        }
        else if (userInfoPresenter.readLastDateDiff()==0){
            btn_srt_training.setEnabled(false);
            btn_srt_training.setText("Сегодня вы хорошо потренировались");
        }
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