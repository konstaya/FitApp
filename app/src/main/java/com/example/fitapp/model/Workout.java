package com.example.fitapp.model;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private List<Exercise> warmUp = new ArrayList<>();
    private List<Exercise> main = new ArrayList<>();
    private List<Exercise> stretching = new ArrayList<>();
    public Workout(){}
    public Workout(List<Exercise> warmUp, List<Exercise> main, List<Exercise> stretching){
        this.warmUp = warmUp;
        this.main = main;
        this.stretching = stretching;
    }

    public List<Exercise> getWarmUp() {
        return warmUp;
    }

    public void setWarmUp(List<Exercise> warmUp) {
        this.warmUp = warmUp;
    }

    public List<Exercise> getMain() {
        return main;
    }

    public void setMain(List<Exercise> main) {
        this.main = main;
    }

    public List<Exercise> getStretching() {
        return stretching;
    }

    public void setStretching(List<Exercise> stretching) {
        this.stretching = stretching;
    }
}
