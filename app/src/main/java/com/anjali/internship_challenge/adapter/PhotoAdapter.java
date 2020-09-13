package com.anjali.internship_challenge.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anjali.internship_challenge.FullImageActivity;
import com.anjali.internship_challenge.LoadPhoto;
import com.anjali.internship_challenge.R;
import com.anjali.internship_challenge.data.Photo;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Photo> photos;
    ImageView imageView;

    public PhotoAdapter(Context context, ArrayList<Photo> photos) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.photos = photos;
    }
    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.layout_photo,parent,false);
        return new PhotoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ViewHolder holder, final int position) {
        holder.photoTitle.setText(photos.get(position).getTitle());
//        Picasso.with(context).load(photos.get(position).getUrl())
//                .into(holder.imageView);
        LoadPhoto task1=new LoadPhoto();
        Bitmap myimage=null;
        try {
            myimage=task1.execute(photos.get(position).getThumbnailUrl()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        holder.imageView.setImageBitmap(myimage);

        holder.fullImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, FullImageActivity.class);
                intent.putExtra("photo", photos.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView photoTitle,fullImageView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            photoTitle = itemView.findViewById(R.id.photoTitle);
            fullImageView = itemView.findViewById(R.id.fullImageView);
            imageView =itemView.findViewById(R.id.imageView);

        }
    }
}
