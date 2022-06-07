package com.example.fitapp.presenter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fitapp.model.Exercise;
import com.example.fitapp.model.FirebaseDatabaseHelper;
import com.example.fitapp.model.User;

import java.util.List;


public class TrainingPresenter {
    private Context myContext;
    private RecyclerView rv;
    String[] pref = new String[]{"ArmExs","BackExs", "BackSurfaceHipExs","ChestExs", "DirectAbsExs",
            "FrontSurfaceHipExs", "GlutealMuscleExs", "InnerSurfaceHipExs", "LowerAbsExs",
            "ObliqueAbsExs"};

    public TrainingPresenter(Context context){
        myContext = context;
    }



    public  void fillExs(RecyclerView warmUpRecyclerView,RecyclerView mainRecyclerView,
                         RecyclerView stretchRecyclerView){
        int planday = new UserInfoPresenter(myContext).readPlanDay();
        new FirebaseDatabaseHelper(myContext).UserAuth(new FirebaseDatabaseHelper.UserStatus() {
            @Override
            public void DataIsLoaded(User user) {
                new RecyclerView_Config().setConfig(warmUpRecyclerView,myContext,user.getPlan().get(planday).getWarmUp(),0);
                new RecyclerView_Config().setConfig(mainRecyclerView,myContext,user.getPlan().get(planday).getMain(),0);
                new RecyclerView_Config().setConfig(stretchRecyclerView,myContext,user.getPlan().get(planday).getStretching(),0);
            }
        });
    }

    public void fillTrainig(RecyclerView recyclerView){
        new FirebaseDatabaseHelper(myContext).UserAuth(new FirebaseDatabaseHelper.UserStatus() {
            @Override
            public void DataIsLoaded(User user) {
                new RecyclerView_Config().setConfig(recyclerView,myContext,user.getPlan().get(0).getMain(),1);
            }
        });
    }

}
