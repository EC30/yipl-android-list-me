package com.anjali.internship_challenge.data;

import java.io.Serializable;

public class Post implements Serializable {

    private int userID;
    private int id;
    private String title;
    private String body;

    public Post(int userID, int id, String title, String body) {
        this.userID = userID;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserID() {return userID;}
    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getBody() {return body;}

    public void setUserID(int userID) {this.userID = userID;}
    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setBody(String body) {this.body = body;}

}
