package org.dsumps200.gcu.myapplication;

public class Earthquake {

    private String location;
    private String dateTime;
    private float magnitude;
    private int depth;
    private float latitude;
    private float longitude;

    public Earthquake(String location, String dateTime, float magnitude, int depth, float latitude, float longitude) {
        this.location = location;
        this.dateTime = dateTime;
        this.magnitude = magnitude;
        this.depth = depth;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
