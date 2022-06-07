package com.example.fitapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitapp.R;
import com.example.fitapp.presenter.TrainingPresenter;
import com.example.fitapp.presenter.UserInfoPresenter;


public class ExersiseFragment extends Fragment {
    RecyclerView recyclerView;
    Button btn_next;
    TextView tv;
    int currentPosition;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.exercise_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewTraining);
        btn_next = view.findViewById(R.id.btn_next_exs);
        tv = view.findViewById(R.id.textViewEndTraining);
        new TrainingPresenter(getContext()).fillTrainig(recyclerView);

        currentPosition = 1;

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int totalItemCount = recyclerView.getAdapter().getItemCount();

                if (btn_next.getText().equals("Завершить")){

                    UserInfoPresenter userInfoPresenter = new UserInfoPresenter(getContext());
                    userInfoPresenter.addLastDate();
                    userInfoPresenter.addProgress();
                    userInfoPresenter.updatePlanDay();

                    Log.i("LOG", "попытка закрыть фрагмент") ;
                    Intent returnIntent = new Intent();
                    getActivity().setResult(Activity.RESULT_CANCELED, returnIntent);
                    getActivity().finish();
                }
                Log.i("LOG", String.valueOf(currentPosition));

                if (totalItemCount-1 == currentPosition){
                    recyclerView.setVisibility(View.GONE);
                    tv.setVisibility(View.VISIBLE);
                    btn_next.setText("Завершить");

                }
                else{
                    recyclerView.getLayoutManager().scrollToPosition(((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition() + 1);
                    currentPosition++;
                }


            }
        });
        return view;
    }
}