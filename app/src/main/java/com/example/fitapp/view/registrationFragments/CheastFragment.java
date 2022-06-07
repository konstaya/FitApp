package com.example.fitapp.view.registrationFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.fitapp.R;


public class CheastFragment extends Fragment {

    EditText cheast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cheast, container, false);
        cheast=(EditText) view.findViewById(R.id.cheast);
        return view;
    }
    public float getCheast()
    {
        if(TextUtils.isEmpty(cheast.getText().toString())) {
            //age.setError("Your message");
            return -1;
        }
        return Float.parseFloat(cheast.getText().toString());
    }
}