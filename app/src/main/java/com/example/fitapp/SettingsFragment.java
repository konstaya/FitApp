package com.example.fitapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fitapp.presenter.UserInfoPresenter;
import com.example.fitapp.view.MainActivity;
import com.google.firebase.auth.FirebaseAuth;


public class SettingsFragment extends Fragment {
    Button btn_logout;
    TextView mon, tue, wed, thur, fri, sat, sun;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        btn_logout = view.findViewById(R.id.btn_logout);
        mon = view.findViewById(R.id.monDay);
        tue = view.findViewById(R.id.tuesDay);
        wed = view.findViewById(R.id.wednesDay);
        thur = view.findViewById(R.id.thursDay);
        fri = view.findViewById(R.id.friDay);
        sat = view.findViewById(R.id.saturDay);
        sun = view.findViewById(R.id.sunDay);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        return view;
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.remove("LOGIN");
        myEditor.remove("PASSWORD");
        myEditor.apply();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }
    private void underlineDays() {


    }
}