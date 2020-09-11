package com.anjali.internship_challenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.anjali.internship_challenge.adapter.CommentAdapter;
import com.anjali.internship_challenge.data.Comment;
import com.anjali.internship_challenge.data.Post;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {
    TextView comment;
    RecyclerView commentRecyclerView;
    public Post post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        ArrayList<Comment> comments=new ArrayList<>();

        post = (Post) getIntent().getSerializableExtra("post");
        DownloadTask task = new DownloadTask();
        try {
            String commentLink = Api.commentsAPI.replace(Api.KEY_POST_ID, Integer.toString(post.getId()));
            Log.i("Link", commentLink);
            JSONArray commentArray = new JSONArray(task.execute(commentLink).get());
            for (int i = 0; i < commentArray.length(); i++) {
                JSONObject commentObject = commentArray.getJSONObject(i);

                int PostID = commentObject.getInt(Api.POST_ID);
                int commentID = commentObject.getInt(Api.ID);
                String CName = commentObject.getString(Api.NAME);
                String CEmail = commentObject.getString(Api.EMAIL);
                String Ccomment=commentObject.getString(Api.BODY);

                Comment comment = new Comment(PostID, commentID, CName, CEmail,Ccomment);
                comments.add(comment);

                Log.i("Comment", comment.getPostID()+comment.getId()+comment.getName()+comment.getEmail()+comment.getBody());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        CommentAdapter adapter = new CommentAdapter(this,comments);
        commentRecyclerView = findViewById(R.id.commentRecyclerView);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentRecyclerView.setAdapter(adapter);

    }
}