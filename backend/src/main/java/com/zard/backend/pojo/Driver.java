package com.zard.backend.pojo;

import lombok.Data;

@Data
public class Driver{
    private Integer id;
    // 经纬度
    private double longitude;
    private double latitude;
    // 状态（是否可载客）
    private boolean state;
    private String name;
    private String phone;
    private String numberPlate;
}
