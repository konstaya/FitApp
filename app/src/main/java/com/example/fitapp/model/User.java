package com.example.fitapp.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class User {
    private String name, email;
    private int age, height ;
    private float weight ;
    private float arm , chest , waist , hips , oneHip, shin;
    private String bodyConstitution;
    private List<Workout> plan;
    public User(){}
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public User(String name, String email,
                int age, int height, float weight,
                float arm, float chest, float waist, float hips,
                float oneHip, float shin){
        this.name = name;
        this.email = email;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.arm = arm;
        this.chest = chest;
        this.waist = waist;
        this.hips = hips;
        this.oneHip = oneHip;
        this.shin = shin;
        this.bodyConstitution = BodyContitution.Calculate(weight, height, chest, arm);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getArm() {
        return arm;
    }

    public void setArm(float arm) {
        this.arm = arm;
    }

    public float getChest() {
        return chest;
    }

    public void setChest(float chest) {
        this.chest = chest;
    }

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public float getHips() {
        return hips;
    }

    public void setHips(float hips) {
        this.hips = hips;
    }

    public float getOneHip() {
        return oneHip;
    }

    public void setOneHip(float oneHip) {
        this.oneHip = oneHip;
    }

    public float getShin() {
        return shin;
    }

    public void setShin(float shin) {
        this.shin = shin;
    }
    public String getBodyConstitution() {
        return bodyConstitution;
    }

    public void setBodyConstitution(String bodyConstitution) {
        this.bodyConstitution = bodyConstitution;
    }

    public List<Workout> getPlan() {
        return plan;
    }

    public void setPlan(List<Workout> plan) {
        this.plan = plan;
    }
}
