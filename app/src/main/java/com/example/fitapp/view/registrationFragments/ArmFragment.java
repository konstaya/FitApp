package com.example.fitapp.view.registrationFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.fitapp.R;


public class ArmFragment extends Fragment {

    EditText wrist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_arm, container, false);
        wrist=(EditText) view.findViewById(R.id.arm);
        return view;
    }
    public float getWrist()
    {
        if(TextUtils.isEmpty(wrist.getText().toString())) {
            //age.setError("Your message");
            return -1;
        }
        return Float.parseFloat(wrist.getText().toString());
    }
}