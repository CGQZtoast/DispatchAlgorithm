package com.zard.backend.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zard.backend.util.Point;

/**
 * @Author 乌贼
 * @Date 2023-08-15 23:01
 * @Description
 */

public class Points {
    @JsonProperty("origin")
    private Point origin;
    @JsonProperty("destination")
    private Point destination;

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }
}
