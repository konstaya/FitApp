package com.example.fitapp.model;

import java.util.ArrayList;
import java.util.List;

public class BodyContitution {
    public static String Calculate(Float weight, int height, Float chest, Float arm){
        int  constitution = 0;
        float indexKetle = weight*1000/height;
        float indexSolovieva = arm;
        float indexPinie = height-(weight+chest);
        if (indexKetle<325) constitution++;
        else if (indexKetle<355) constitution+=2;
        else if (indexKetle>=355) constitution+=3;

        if (indexSolovieva<15) constitution++;
        else if (indexSolovieva<18) constitution+=2;
        else if (indexSolovieva>=18) constitution+=3;

        if (indexPinie>30) constitution++;
        else if (indexPinie>=10) constitution+=2;
        else if (indexPinie<10) constitution+=3;

        if (constitution <= 4) return "a";
        else if (constitution <= 7) return "n";
        else return "h";

    }
    public static List<Boolean> ProblemPlaces(User user){
        List<Boolean> list = new ArrayList<>();

        return list;
    }
}
