package com.zard.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zard.backend.dao.DriverMapper;
import com.zard.backend.model.DriverMarkerModel;
import com.zard.backend.pojo.Driver;
import com.zard.backend.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver> implements DriverService {
    @Autowired
    private DriverMapper driverMapper;
    @Override
    public List<DriverMarkerModel> getDriverMarkers() {
        return driverMapper.getDriverMarkers();
    }
}
