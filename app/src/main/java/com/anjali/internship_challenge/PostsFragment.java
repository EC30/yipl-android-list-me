package com.anjali.internship_challenge;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anjali.internship_challenge.adapter.PostAdapter;
import com.anjali.internship_challenge.data.Post;
import com.anjali.internship_challenge.data.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_posts, container, false);


        User user = (User) getArguments().getSerializable("user");

        ArrayList<Post> posts = new ArrayList<>();

        DownloadTask task = new DownloadTask();
        try {
            String postLink = Api.postsAPI.replace(Api.KEY_USER_ID, Integer.toString(user.getId()));
            Log.i("Link", postLink);
            JSONArray postArray = new JSONArray(task.execute(postLink).get());
            for (int i = 0; i < postArray.length(); i++) {
                JSONObject postObject = postArray.getJSONObject(i);

                int userID = postObject.getInt(Api.USER_ID);
                int postID = postObject.getInt(Api.ID);
                String postTitle = postObject.getString(Api.TITLE);
                String postBody = postObject.getString(Api.BODY);

                Post post = new Post(userID, postID, postTitle, postBody);
                posts.add(post);

                Log.i("Post", post.getUserID() + post.getId() + post.getTitle() + post.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        PostAdapter postAdapter = new PostAdapter(getContext(), posts);
        RecyclerView postsRecyclerView = view.findViewById(R.id.postsRecyclerView);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        postsRecyclerView.setAdapter(postAdapter);

        return view;
    }
}