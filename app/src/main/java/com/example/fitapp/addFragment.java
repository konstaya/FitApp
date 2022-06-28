package com.example.fitapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitapp.model.FirebaseDatabaseHelper;

public class addFragment extends Fragment {


    Button btn_save;
    EditText chest, waist, hips, oneHip, shin, weight;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        btn_save = view.findViewById(R.id.btn_save);
        chest = view.findViewById(R.id.et_newChest);
        waist = view.findViewById(R.id.et_newWaist);
        hips = view.findViewById(R.id.et_newHips);
        oneHip = view.findViewById(R.id.et_oneHip);
        shin = view.findViewById(R.id.et_newShin);
        weight = view.findViewById(R.id.et_newWeight);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });


        return view;
    }

    private void save() {
        if (check()){
            new FirebaseDatabaseHelper(getContext()).UpdateUser(Float.parseFloat(chest.getText().toString()),
                    Float.parseFloat(waist.getText().toString()), Float.parseFloat(hips.getText().toString()));
            Toast.makeText(getContext(), "Внесены изменения", Toast.LENGTH_LONG).show();

            this.getActivity().findViewById(R.id.addFragment).setVisibility(View.GONE);
            this.getActivity().findViewById(R.id.btn_save).setVisibility(View.VISIBLE);

        }
    }
    private boolean check(){
        boolean flag = true;

        return flag;
    }
}