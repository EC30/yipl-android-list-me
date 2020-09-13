package com.anjali.internship_challenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.anjali.internship_challenge.adapter.PhotoAdapter;
import com.anjali.internship_challenge.data.Album;
import com.anjali.internship_challenge.data.Photo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity {
    RecyclerView photosRecyclerView;
    private Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        ArrayList<Photo> photos=new ArrayList<>();

        album = (Album) getIntent().getSerializableExtra("album");
        DownloadTask task = new DownloadTask();
        try {
            String photoLink = Api.photosAPI.replace(Api.KEY_ALBUM_ID, Integer.toString(album.getId()));
            Log.i("Link", photoLink);
            JSONArray photoArray = new JSONArray(task.execute(photoLink).get());
            for (int i = 0; i < photoArray.length(); i++) {
                JSONObject photoObject = photoArray.getJSONObject(i);

                int albumID = photoObject.getInt(Api.ALBUM_ID);
                int photoID =photoObject.getInt(Api.ID);
                String title = photoObject.getString(Api.TITLE);
                String thumbnail = photoObject.getString(Api.THUMBNAIL_URL);
                String url = photoObject.getString(Api.URL);
                ;

                Photo  photo=new Photo(albumID,photoID,title,thumbnail,url);
                photos.add(photo);

                Log.i("photo", photo.getAlbumID()+photo.getId()+photo.getTitle()+photo.getThumbnailUrl()+photo.getUrl());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }




        PhotoAdapter adapter = new PhotoAdapter(this,photos);
        photosRecyclerView = findViewById(R.id.photosRecyclerView);
        photosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        photosRecyclerView.setAdapter(adapter);
    }
}