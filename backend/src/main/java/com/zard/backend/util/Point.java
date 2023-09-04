package com.zard.backend.util;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author 乌贼
 * @Date 2023-08-15 22:56
 * @Description
 */

public class Point {
    @JsonProperty("longitude")
    private double longitude;
    @JsonProperty("latitude")
    private double latitude;

    public Point() {
    }

    public Point(double lat, double lon) {
        this.longitude = lon;
        this.latitude = lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
