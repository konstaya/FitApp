package com.example.fitapp.model;

import android.content.Context;
import android.content.Intent;

import com.example.fitapp.view.LoginActivity;
import com.example.fitapp.view.PlanActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class UserPlanCreator {

    private Context context;

    List<Exercise> armExs = new ArrayList<>();
    List<Exercise> backExs = new ArrayList<>();
    List<Exercise> backSurfaceHipExs = new ArrayList<>();
    List<Exercise> chestExs = new ArrayList<>();
    List<Exercise> directAbsExs = new ArrayList<>();
    List<Exercise> frontSurfaceHipExs = new ArrayList<>();
    List<Exercise> glutealMuscleExs = new ArrayList<>();
    List<Exercise> innerSurfaceHipExs = new ArrayList<>();
    List<Exercise> lowerAbsExs = new ArrayList<>();
    List<Exercise> obliqueAbsExs = new ArrayList<>();
    List<Exercise> warmUp = new ArrayList<>();
    List<Exercise> stretching = new ArrayList<>();



    private String[] pref = new String[]{"ArmExs","BackExs", "BackSurfaceHipExs","ChestExs", "DirectAbsExs",
            "FrontSurfaceHipExs", "GlutealMuscleExs", "InnerSurfaceHipExs", "LowerAbsExs",
            "ObliqueAbsExs"};


    public UserPlanCreator(Context context){
        this.context = context;
    }
    public void userRegistration(User user, String pass){
        String[] warm_cool_ex = {"Warm-up","Stretching"};
        for (String type : warm_cool_ex){
            new FirebaseDatabaseHelper().readWarmCool(new FirebaseDatabaseHelper.DataStatus() {
                @Override
                public void DataIsLoaded(List<Exercise> exercises) {
                    addList(exercises,type);
                    for (String child : pref){
                        new FirebaseDatabaseHelper().readExercises(new FirebaseDatabaseHelper.DataStatus() {
                            @Override
                            public void DataIsLoaded(List<Exercise> exercises) {
                                addList(exercises,child);
                                if (child.equals(pref[pref.length-1])){
                                    //List<Workout> workouts = new ArrayList<>();
                                    //workouts.add(new Workout(armExs, backExs, backSurfaceHipExs));
                                    //user.setPlan(workouts);
                                    //new FirebaseDatabaseHelper(context).UserRegistration(user, pass);
                                    register(user, pass, armExs, backExs, backSurfaceHipExs, chestExs,directAbsExs,
                                            frontSurfaceHipExs, glutealMuscleExs, innerSurfaceHipExs, lowerAbsExs,
                                            obliqueAbsExs, warmUp, stretching);
                                }
                            }

                        }, child);
                    }
                }
            },type);
        }



    }
    private void addList(List<Exercise> exercises,String type){
        switch (type){
            case "ArmExs":armExs = exercises; break;
            case "BackExs": backExs = exercises; break;
            case "BackSurfaceHipExs": backSurfaceHipExs = exercises; break;
            case "ChestExs": chestExs = exercises; break;
            case "DirectAbsExs": directAbsExs = exercises; break;
            case "FrontSurfaceHipExs": frontSurfaceHipExs = exercises; break;
            case "GlutealMuscleExs": glutealMuscleExs = exercises; break;
            case "InnerSurfaceHipExs":innerSurfaceHipExs = exercises; break;
            case "LowerAbsExs": lowerAbsExs = exercises; break;
            case "ObliqueAbsExs":obliqueAbsExs = exercises; break;
            case "Warm-up": warmUp = exercises; break;
            case "Stretching": stretching = exercises; break;
        }

    }

    private void register(User user, String password, List<Exercise> armExs,
                          List<Exercise> backExs, List<Exercise> backSurfaceHipExs,List<Exercise> chestExs,
                          List<Exercise> dAbsExs, List<Exercise> frontSurfaceHipExs, List<Exercise> buttExs,
                          List<Exercise> innerSurfaceHipExs,List<Exercise> lAbsExs,List<Exercise> OAbsExs,
                          List<Exercise> warmUp, List<Exercise> stretch){

        float lower_estimation = 0;
        switch (user.getBodyConstitution()) {
            case "a":{
                lower_estimation = estimateAstenik(user);
                break;
            }
            case "n":
                lower_estimation = estimateNormostenik(user);
                break;
            case "h":
                lower_estimation = estimateHyperstenik(user);
                break;
        }

        List<Workout> plan = new ArrayList<>();
        for (int i = 0; i < 8; i++ ){
            plan.add(createWorkout(lower_estimation,armExs,
                    backExs, backSurfaceHipExs,chestExs,
                    dAbsExs, frontSurfaceHipExs,  buttExs,
                    innerSurfaceHipExs,lAbsExs,OAbsExs, warmUp, stretch));
        }

        user.setPlan(plan);
        new FirebaseDatabaseHelper(context).UserRegistration(user, password);
    }

    private Workout createWorkout(float lower_estimation, List<Exercise> armExs,
                                  List<Exercise> backExs, List<Exercise> backSurfaceHipExs,List<Exercise> chestExs,
                                  List<Exercise> dAbsExs, List<Exercise> frontSurfaceHipExs, List<Exercise> buttExs,
                                  List<Exercise> innerSurfaceHipExs,List<Exercise> lAbsExs,List<Exercise> OAbsExs,
                                  List<Exercise> warmUp, List<Exercise> stretch){
        List<Exercise> mainList = new ArrayList<>();
        mainList.addAll(pickNRandomElements(frontSurfaceHipExs,2));
        mainList.addAll(pickNRandomElements(backSurfaceHipExs,2));
        mainList.addAll(pickNRandomElements(innerSurfaceHipExs,2));
        mainList.addAll(pickNRandomElements(buttExs,3));

        if (lower_estimation == 1) mainList = addRepetToEx(mainList, 14);
        else if (lower_estimation == 2) mainList = addRepetToEx(mainList,18);
        else mainList = addRepetToEx(mainList,20);

        mainList.addAll(addRepetToEx(pickNRandomElements(OAbsExs, 3),25));
        mainList.addAll(addRepetToEx(pickNRandomElements(dAbsExs, 2),25));
        mainList.addAll(addRepetToEx(pickNRandomElements(lAbsExs,2),25));
        mainList.addAll(addRepetToEx(pickNRandomElements(armExs,2),16));
        mainList.addAll(addRepetToEx(pickNRandomElements(chestExs,1),16));
        mainList.addAll(addRepetToEx(pickNRandomElements(backExs,2),16));

        List<Exercise> warmList = new ArrayList<>();
        warmList.addAll(pickNRandomElements(warmUp,1));
        List<Exercise> stretchList = new ArrayList<>();
        stretchList.addAll(pickNRandomElements(stretch,1));


        return new Workout(warmList, mainList, stretchList);
    }
    private float estimateAstenik(User user){
        float lower_estimation = 0;

        if (user.getWeight() < 47) lower_estimation++;
        else if (user.getWeight() < 53.8) lower_estimation+=2;
        else lower_estimation+=3;

        if (user.getHeight() < 157.7) lower_estimation++;
        else if (user.getHeight() < 169.3) lower_estimation+=2;
        else lower_estimation+=3;

        if (user.getHips() < 88) lower_estimation++;
        else if (user.getHips() < 94.5) lower_estimation+=2;
        else lower_estimation+=3;

        if (user.getOneHip() < 48.4) lower_estimation++;
        else if (user.getOneHip() < 53.5) lower_estimation+=2;
        else lower_estimation+=3;

        if (user.getShin() < 31.2) lower_estimation++;
        else if (user.getShin() < 34.6) lower_estimation+=2;
        else lower_estimation+=3;

        return Math.round(lower_estimation/5);
    }
    private float estimateNormostenik(User user){
        float lower_estimation = 0;

        if (user.getWeight() < 51) lower_estimation++;
        else if (user.getWeight() < 58.5) lower_estimation+=2;
        else lower_estimation+=3;

        if (user.getHeight() < 158.8) lower_estimation++;
        else if (user.getHeight() < 170.8) lower_estimation+=2;
        else lower_estimation+=3;

        if (user.getHips() < 91.4) lower_estimation++;
        else if (user.getHips() < 97.1) lower_estimation+=2;
        else lower_estimation+=3;

        if (user.getOneHip() < 51.3) lower_estimation++;
        else if (user.getOneHip() < 56.3) lower_estimation+=2;
        else lower_estimation+=3;

        if (user.getShin() < 33) lower_estimation++;
        else if (user.getShin() < 35.7) lower_estimation+=2;
        else lower_estimation+=3;

        return Math.round(lower_estimation/5);
    }
    private float estimateHyperstenik(User user){
        float lower_estimation = 0;

        if (user.getWeight() < 55.3) lower_estimation++;
        else if (user.getWeight() < 70.7) lower_estimation+=2;
        else lower_estimation+=3;

        if (user.getHeight() < 160.5) lower_estimation++;
        else if (user.getHeight() < 173) lower_estimation+=2;
        else lower_estimation+=3;

        if (user.getHips() < 95.6) lower_estimation++;
        else if (user.getHips() < 105) lower_estimation+=2;
        else lower_estimation+=3;

        if (user.getOneHip() < 54.3) lower_estimation++;
        else if (user.getOneHip() < 60.5) lower_estimation+=2;
        else lower_estimation+=3;

        if (user.getShin() < 34) lower_estimation++;
        else if (user.getShin() < 37.8) lower_estimation+=2;
        else lower_estimation+=3;

        return Math.round(lower_estimation/5);
    }
    public static <E> List<E> pickNRandomElements(List<E> list, int n, Random r) {
        int length = list.size();

        if (length < n) return null;

        //We don't need to shuffle the whole list
        for (int i = length - 1; i >= length - n; --i)
        {
            Collections.swap(list, i , r.nextInt(i + 1));
        }
        return list.subList(length - n, length);
    }

    public static <E> List<E> pickNRandomElements(List<E> list, int n) {
        return pickNRandomElements(list, n, ThreadLocalRandom.current());
    }
    private List<Exercise> addRepetToEx(List<Exercise> list, int n){
        for (Exercise item: list){
            item.setCount(n);
        }
        return list;
    }
}
