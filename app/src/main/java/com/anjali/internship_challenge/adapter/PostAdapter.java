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

import com.anjali.internship_challenge.CommentActivity;
import com.anjali.internship_challenge.R;
import com.anjali.internship_challenge.data.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Post> posts;

    public PostAdapter(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, final int position) {
        holder.postTitleTextView.setText(posts.get(position).getTitle());
        holder.postBodyTextView.setText(posts.get(position).getBody());
        holder.cmt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CommentActivity.class);
                intent.putExtra("post", posts.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView postTitleTextView, postBodyTextView;
        Button cmt_button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postTitleTextView = itemView.findViewById(R.id.postTitleTextView);
            postBodyTextView = itemView.findViewById(R.id.postBodyTextView);
            cmt_button=itemView.findViewById(R.id.cmt_button);

        }
    }
}
