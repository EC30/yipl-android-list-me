package com.anjali.internship_challenge.data;

import java.io.Serializable;

public class Photo implements Serializable {

    private int albumID;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Photo(int albumID, int id, String title, String url, String thumbnailUrl) {
        this.albumID = albumID;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }


    public int getAlbumID() {return albumID;}
    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getUrl() {return url;}
    public String getThumbnailUrl() {return thumbnailUrl;}

    public void setAlbumID(int albumID) {this.albumID = albumID;}
    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setUrl(String url) {this.url = url;}
    public void setThumbnailUrl(String thumbnailUrl) {this.thumbnailUrl = thumbnailUrl;}

}
