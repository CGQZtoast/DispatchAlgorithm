package com.zard.backend.controller;

import com.zard.backend.model.DriverMarkerModel;
import com.zard.backend.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 乌贼
 * @Date 2023-08-10 10:39
 * @Description
 */

@RestController
public class initController {

    @Autowired
    DriverService driverService;

    /**
     * Description：初始化 drivers markers
     * @return 司机位置信息
     */
    @GetMapping("/init")
    public List<DriverMarkerModel> init() {
        return driverService.getDriverMarkers();
    }
}
