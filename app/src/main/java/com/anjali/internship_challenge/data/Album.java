package com.anjali.internship_challenge.data;

import java.io.Serializable;

public class Album implements Serializable {

    private int userID;
    private int id;
    private String title;

    public Album(int userID, int id, String title) {
        this.userID = userID;
        this.id = id;
        this.title = title;
    }

    public int getUserID() {return userID;}
    public int getId() {return id;}
    public String getTitle() {return title;}

}
