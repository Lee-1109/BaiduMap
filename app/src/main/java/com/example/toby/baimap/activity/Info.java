package com.example.toby.baimap.activity;

import java.io.Serializable;


public class Info implements Serializable {
    private double latitude;
    private double longitude;
    private int imgId;
    private String name;
    private String distance;
    private double price;
    private String umber;

    public Info(double latitude, double longitude, int imgId, String name,
                String distance, double price, String umber) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.imgId = imgId;
        this.name = name;
        this.distance = distance;
        this.price = price;
        this.umber = umber;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUmber() {
        return umber;
    }

    public void setUmber(String umber) {
        this.umber = umber;
    }

}