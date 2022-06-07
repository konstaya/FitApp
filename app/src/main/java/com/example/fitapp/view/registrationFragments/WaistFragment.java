package com.example.fitapp.view.registrationFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.fitapp.R;


public class WaistFragment extends Fragment {

    EditText waist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_waist, container, false);
        waist = (EditText) v.findViewById(R.id.waist);
        return v;
    }
    public float getWaist(){
        if(TextUtils.isEmpty(waist.getText().toString())) {
            //age.setError("Your message");
            return -1;
        }
        return Float.parseFloat(waist.getText().toString());
    }
}