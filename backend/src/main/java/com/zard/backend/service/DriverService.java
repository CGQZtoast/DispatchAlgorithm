package com.zard.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zard.backend.model.DriverMarkerModel;
import com.zard.backend.pojo.Driver;

import java.util.List;

public interface DriverService extends IService<Driver> {
    List<DriverMarkerModel> getDriverMarkers();
}
