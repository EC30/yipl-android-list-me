package com.anjali.internship_challenge.data;

public class Comment {

    private int postID;
    private int id;
    private String name;
    private String email;
    private String body;

    public Comment(int postID, int id, String name, String email, String body) {
        this.postID = postID;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public int getPostID() {return postID;}
    public int getId() {return id;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getBody() {return body;}

    public void setPostID(int postID) {this.postID = postID;}
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setBody(String body) {this.body = body;}

}
