package com.example.fitapp.view.registrationFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.fitapp.R;


public class HipsFragment extends Fragment {

    EditText hips;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hips, container, false);
        hips = v.findViewById(R.id.hips);
        return v;
    }
    public float getHips(){
        if(TextUtils.isEmpty(hips.getText().toString())) {
            //age.setError("Your message");
            return -1;
        }
        return Float.parseFloat(hips.getText().toString());
    }
}