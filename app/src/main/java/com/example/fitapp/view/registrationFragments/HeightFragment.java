package com.example.fitapp.view.registrationFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.fitapp.R;


public class HeightFragment extends Fragment {

    EditText height;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_height, container, false);
        height = (EditText) view.findViewById(R.id.height);

        return view;
    }
    public int getHeight(){
        if(TextUtils.isEmpty(height.getText().toString())) {
            //age.setError("Your message");
            return -1;
        }
        return Integer.parseInt(height.getText().toString());
    }
}