package com.example.fitapp.view.registrationFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.fitapp.R;


public class WeightFragment extends Fragment {

   EditText weight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_weight, container, false);
        weight=(EditText) view.findViewById(R.id.weight);
        return view;
    }
    public float getWeight(){

        if(TextUtils.isEmpty(weight.getText().toString())) {
            //age.setError("Your message");
            return -1;
        }
        return Float.parseFloat(weight.getText().toString());
    }
}