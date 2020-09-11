package com.anjali.internship_challenge.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anjali.internship_challenge.R;
import com.anjali.internship_challenge.data.Album;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Album> albums;

    public AlbumAdapter(Context context, ArrayList<Album> albums) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.albums = albums;
    }


    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.layout_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, final int position) {
        holder.albumtitle.setText(albums.get(position).getTitle());
        holder.photo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(context,activity_photos.class);
//                intent.putExtra("album", albums.get(position));
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView albumtitle;
        Button photo_button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            albumtitle = itemView.findViewById(R.id.albumTitle);
            photo_button=itemView.findViewById(R.id.photoButton);
        }
    }
}
