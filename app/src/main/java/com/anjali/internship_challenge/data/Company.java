package com.anjali.internship_challenge.data;

import java.io.Serializable;

public class Company implements Serializable {

    private String name;
    private String catchphrase;
    private String bs;

    public Company(String name, String catchphrase, String bs) {
        this.name = name;
        this.catchphrase = catchphrase;
        this.bs = bs;
    }

    public String getName() {return name;}
    public String getCatchphrase() {return catchphrase; }
    public String getBs() {return bs;}

    public void setName(String name) {this.name = name;}
    public void setCatchphrase(String catchphrase) {this.name = catchphrase;}
    public void setBs(String bs) {this.name = bs;}

}
