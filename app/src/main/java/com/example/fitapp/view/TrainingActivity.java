package com.example.fitapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fitapp.R;
import com.example.fitapp.presenter.DataConfig;
import com.example.fitapp.presenter.TrainingPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TrainingActivity extends AppCompatActivity {
    private RecyclerView recyclerViewMain, recyclerViewWarm, recyclerViewStretch;
    private TextView day;
    private FloatingActionButton btn_startTrain;
    private FragmentContainerView fragmentViewTraining;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        recyclerViewMain = (RecyclerView) findViewById(R.id.recyclerViewMainExercises);
        recyclerViewWarm = (RecyclerView) findViewById(R.id.recyclerViewWarm);
        recyclerViewStretch = (RecyclerView) findViewById(R.id.recyclerViewStretching);
        day = (TextView) findViewById(R.id.dayOfTraining);
        btn_startTrain =  findViewById(R.id.startTrainActionButton);
        fragmentViewTraining = findViewById(R.id.fragmentViewTraining);
        mainLayout = (LinearLayout) findViewById(R.id.layout_all_exs);
        fragmentViewTraining.setVisibility(View.GONE);


        new TrainingPresenter(TrainingActivity.this).fillExs(recyclerViewWarm, recyclerViewMain,
                recyclerViewStretch);
        day.setText(DataConfig.GetCurrentDay());
        btn_startTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentViewTraining.setVisibility(View.VISIBLE);
                mainLayout.setVisibility(View.GONE);
                NestedScrollView scrollView = findViewById(R.id.nestedScrollView);

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (fragmentViewTraining.getVisibility()==View.VISIBLE){
            fragmentViewTraining.setVisibility(View.GONE);
            mainLayout.setVisibility(View.VISIBLE);
        }
        else super.onBackPressed();
    }
}