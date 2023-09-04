package com.zard.backend.model;

import com.zard.backend.util.Point;

import java.util.List;

/**
 * @Author 乌贼
 * @Date 2023-08-16 10:47
 * @Description
 */

public class NavigateModel {
    private int distance;
    private Polyline polyline = new Polyline();

    public NavigateModel() {
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(List<Point> polyline) {
        this.polyline.setPoints(polyline);
    }
}

class Polyline {
    private List<Point> points;
    private final String color = "#d0ebff";
    private final int width = 6;
    private final String borderColor = "#2576a8";
    private final int borderWidth = 1;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public String getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public int getBorderWidth() {
        return borderWidth;
    }
}

