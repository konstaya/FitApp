package com.example.fitapp.view.registrationFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.fitapp.R;
import com.example.fitapp.view.Registration;


public class AgeFragment extends Fragment {

    EditText age;
    Registration activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_age, container, false);
        age=(EditText) view.findViewById(R.id.age);
        return view;
    }
    public int getAge(){

        if(TextUtils.isEmpty(age.getText().toString())) {
            //age.setError("Your message");
            return -1;
        }

        return Integer.parseInt(age.getText().toString());
    }

}