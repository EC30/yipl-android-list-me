package com.anjali.internship_challenge.data;

import java.io.Serializable;

public class Address implements Serializable {

    private String street;
    private String suite;
    private String city;
    private String zipCode;
    private Geo geo;

    public Address(String street, String suite, String city, String zipCode, Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipCode = zipCode;
        this.geo = geo;
    }

    public String getStreet() {return street;}
    public String getSuite() {return suite;}
    public String getCity() {return city;}
    public String getZipCode() {return zipCode;}
    public Geo getGeo() {return geo;}

    public void setStreet(String street) {this.street = street;}
    public void setSuite(String suite) {this.suite = suite;}
    public void setCity(String city) {this.city = city;}
    public void setZipCode(String zipCode) {this.zipCode = zipCode;}
    public void setGeo(Geo geo) {this.geo = geo;}

}
