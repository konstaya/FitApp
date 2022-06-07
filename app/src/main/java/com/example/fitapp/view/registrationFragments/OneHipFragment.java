package com.example.fitapp.view.registrationFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.fitapp.R;


public class OneHipFragment extends Fragment {

    EditText oneHip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one_hip, container, false);
        oneHip = v.findViewById(R.id.one_hip);
        return v;
    }
    public float getOneHip(){
        if(TextUtils.isEmpty(oneHip.getText().toString())) {
            //age.setError("Your message");
            return -1;
        }
        return Float.parseFloat(oneHip.getText().toString());
    }
}