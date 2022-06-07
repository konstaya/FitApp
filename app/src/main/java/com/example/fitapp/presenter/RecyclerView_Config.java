package com.example.fitapp.presenter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.fitapp.R;
import com.example.fitapp.model.Exercise;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private  ExerciseAdapter mExerciseAdapter;


    public void setConfig(RecyclerView recyclerView, Context context,
                          List<Exercise> exerciseList, int adapterNumber){

        mContext = context;
        if (adapterNumber == 0){
            mExerciseAdapter = new ExerciseAdapter(exerciseList,mContext);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(mExerciseAdapter);
        }
        else if (adapterNumber == 1){
            TrainingAdapter mTrainAdapter = new TrainingAdapter(exerciseList,mContext);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL, false);

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(context) {

                @Override
                public boolean canScrollHorizontally() {
                    return false;
                }

                @Override
                public void setOrientation(int orientation) {
                    super.setOrientation(LinearLayoutManager.HORIZONTAL);
                }

                @Override
                public void setReverseLayout(boolean reverseLayout) {
                    super.setReverseLayout(false);
                }
            };
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mTrainAdapter);
        }


    }

    /*class ExerciseItemView extends RecyclerView.ViewHolder {
        private TextView mName;
        private ImageView mPicture;

        private String key;

        private FirebaseStorage storage = FirebaseStorage.getInstance();
        private StorageReference storageRef;

        public ExerciseItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.exercise_list_item, parent, false));
            mName = (TextView) itemView.findViewById(R.id.textViewEx);
            mPicture = (ImageView) itemView.findViewById(R.id.imageViewEx);

        }
        public void bind(Exercise exercise){
            mName.setText(exercise.getName());
            downloadPicture(exercise, mPicture);
            this.key = key;

        }
        public void downloadPicture(Exercise exercise, ImageView ePic){
            storageRef = storage.getReferenceFromUrl(exercise.getPicture());
            storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(mContext).load(uri)
                            .thumbnail(0.5f)
                            .override(200, 200)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .fitCenter()
                            .priority(Priority.IMMEDIATE)
                            .into(ePic);

                }
            });

        }

    }

    class ExerciseAdapter extends RecyclerView.Adapter<ExerciseItemView>{
        private List<Exercise> mExerciseList;
        private List<String> mKeys;

        public ExerciseAdapter(List<Exercise> mExerciseList) {
            this.mExerciseList = mExerciseList;
        }

        @NonNull
        @Override
        public ExerciseItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ExerciseItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ExerciseItemView holder, int position) {
            holder.mName.setText(mExerciseList.get(position).getName());
            FirebaseStorage.getInstance().getReferenceFromUrl(mExerciseList.get(position).getPicture()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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
            return mExerciseList.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }
    }

     */



}
