package com.example.fitapp.presenter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.fitapp.R;
import com.example.fitapp.model.Exercise;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private Context context;

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_list_item, parent, false);
        ExerciseViewHolder pvh = new ExerciseViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int i) {
            holder.mName.setText(exerciseList.get(i).getName());
                FirebaseStorage.getInstance().getReferenceFromUrl(exerciseList.get(i).getPicture()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(holder.itemView).load(uri)
                                .thumbnail(0.5f)
                                .override(200, 200)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .priority(Priority.IMMEDIATE)
                                .into(holder.mPicture);

                    }
                });

    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private ImageView mPicture;
        ExerciseViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.textViewEx);
            mPicture = (ImageView) itemView.findViewById(R.id.imageViewEx);

        }
    }

    List<Exercise> exerciseList;

    public ExerciseAdapter(List<Exercise> exerciseList, Context context) {
        this.exerciseList = exerciseList;
        this.context = context;
    }
}
