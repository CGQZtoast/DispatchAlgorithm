package com.zard.backend.model;

/**
 * @Author 乌贼
 * @Date 2023-08-28 8:27
 * @Description
 */

public class DriverMarkerModel {
    private Integer id;
    // 经纬度
    private double longitude;
    private double latitude;

    private int width = 40;
    private int height = 40;
    private String iconPath = "../../resources/taxi.png";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
