package com.anjali.internship_challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.anjali.internship_challenge.data.Photo;

import java.util.concurrent.ExecutionException;

public class FullImageActivity extends AppCompatActivity {
    private ImageView fullScreenImageView;
    private Photo photos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        photos = (Photo) getIntent().getSerializableExtra("photo");
        fullScreenImageView=findViewById(R.id.fullScreenImageView);
        LoadPhoto task1=new LoadPhoto();
        Bitmap myimage=null;
        try {
            myimage=task1.execute(photos.getUrl()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fullScreenImageView.setImageBitmap(myimage);
    }
}