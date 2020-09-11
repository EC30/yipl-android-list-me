package com.anjali.internship_challenge;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anjali.internship_challenge.adapter.AlbumAdapter;
import com.anjali.internship_challenge.data.Album;
import com.anjali.internship_challenge.data.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlbumsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_albums, container, false);

        User user = (User) getArguments().getSerializable("user");

        ArrayList<Album> albums = new ArrayList<>();

        DownloadTask task = new DownloadTask();
        try {
            String albumLink = Api.albumsAPI.replace(Api.KEY_USER_ID, Integer.toString(user.getId()));
            Log.i("Link", albumLink);
            JSONArray albumArray = new JSONArray(task.execute(albumLink).get());
            for (int i = 0; i < albumArray.length(); i++) {
                JSONObject albumObject = albumArray.getJSONObject(i);

                int userID = albumObject.getInt(Api.USER_ID);
                int albumID = albumObject.getInt(Api.ID);
                String albumTitle = albumObject.getString(Api.TITLE);

                Album album = new Album(userID, albumID, albumTitle);
                albums.add(album);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        RecyclerView albumRecyclerView = view.findViewById(R.id.albumsRecyclerView);
        AlbumAdapter albumAdapter= new AlbumAdapter(getContext(),albums);
        albumRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        albumRecyclerView.setAdapter(albumAdapter);

        return view;
    }
}