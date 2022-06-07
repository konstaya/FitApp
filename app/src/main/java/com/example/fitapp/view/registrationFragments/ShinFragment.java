package com.example.fitapp.view.registrationFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.fitapp.R;


public class ShinFragment extends Fragment {

   EditText shin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_shin, container, false);
        shin = (EditText) v.findViewById(R.id.shin);
        return v;
    }
    public float getShin(){
        if(TextUtils.isEmpty(shin.getText().toString())) {
            //age.setError("Your message");
            return -1;
        }
        return Float.parseFloat(shin.getText().toString());
    }
}