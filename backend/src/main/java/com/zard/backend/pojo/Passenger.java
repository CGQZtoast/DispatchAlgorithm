package com.zard.backend.pojo;

import lombok.Data;

@Data
public class Passenger {
    private Integer id;
    private double longitude;
    private double latitude;
    private String address;
    private String name;
    private String phone;
}
