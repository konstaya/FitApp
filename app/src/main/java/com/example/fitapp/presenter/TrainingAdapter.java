package com.example.fitapp.presenter;

import android.content.Context;
import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>{

    private List<Exercise> exerciseList = new ArrayList<>();
    private Context mContext;

    public TrainingAdapter(List<Exercise> exerciseList, Context mContext) {
        this.exerciseList = exerciseList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exersise_train_list_item, parent, false);
        return new TrainingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, int position) {
        FirebaseStorage.getInstance().getReferenceFromUrl(exerciseList.get(position).getPicture()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(holder.itemView).load(uri)
                        .thumbnail(0.5f)
                        .override(400, 400)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .priority(Priority.IMMEDIATE)
                        .into(holder.image);

            }
        });
        holder.name.setText(exerciseList.get(position).getName());
        holder.decription.setText(exerciseList.get(position).getDescription());
        String count = "Количество повторений: " + exerciseList.get(position).getCount();
        holder.count.setText(count);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public class TrainingViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name, decription, count;
        public TrainingViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image_item);
            name = (TextView) itemView.findViewById(R.id.tv_name_item);
            decription = itemView.findViewById(R.id.tv_description_item);
            count = (TextView) itemView.findViewById(R.id.tv_count_item);
        }
    }
}
