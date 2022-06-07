package com.example.fitapp.view.registrationFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitapp.R;


public class RegisterFragment extends Fragment {

    private EditText name, email, pass, check_pass;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        name = v.findViewById(R.id.editTextName);
        email = v.findViewById(R.id.editTextEmail);
        pass = v.findViewById(R.id.editTextPassword1);
        check_pass = v.findViewById(R.id.editTextPassword2);
        textView = v.findViewById(R.id.textViewPassIsSmall);
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return v;
    }
    public boolean checkFields(){
        String n = name.getText().toString().trim();
        String e = email.getText().toString().trim();
        String p1 = pass.getText().toString().trim();
        String p2 = check_pass.getText().toString().trim();

        if (n.isEmpty()){
            name.setError("");
            name.requestFocus();
            return false;
        }
        if (e.isEmpty()){
            email.setError("");
            email.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
            email.setError("");
            email.requestFocus();
            return false;
        }
        if (p1.isEmpty()){
            pass.setError("");
            pass.requestFocus();
            return false;
        }
        if (p1.length()<6){
            pass.setError("");
            textView.setVisibility(View.VISIBLE);
            pass.requestFocus();
            return false;
        }
        if (p2.isEmpty()){
            check_pass.setError("");
            check_pass.requestFocus();
            return false;
        }
        if (!p1.equals(p2)){

            check_pass.setError("");
            check_pass.setText("");
            check_pass.requestFocus();
            return false;
        }
        return true;
    }
    public String getEmail(){
        return email.getText().toString().trim();
    }
    public String getUserName(){
        return name.getText().toString().trim();
    }
    public String getPassword(){
        return pass.getText().toString().trim();
    }

}