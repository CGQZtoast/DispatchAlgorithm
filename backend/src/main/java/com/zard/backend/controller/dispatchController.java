package com.zard.backend.controller;

import com.zard.backend.common.ApiResult;
import com.zard.backend.model.DispatchModel;
import com.zard.backend.param.Points;
import com.zard.backend.pojo.Driver;
import com.zard.backend.pojo.Passenger;
import com.zard.backend.service.DriverService;
import com.zard.backend.service.PassengerService;
import com.zard.backend.util.Dispatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 乌贼
 * @Date 2023-08-28 11:14
 * @Description
 */

@RestController
public class dispatchController {
    @Autowired
    DriverService driverService;
    @Autowired
    PassengerService passengerService;

    @PostMapping("/dispatch3.0")
    public ApiResult<DispatchModel> dispatch3_0(@RequestBody Points points) throws Exception {
        List<Driver> drivers = driverService.list();
        List<Passenger> passengers = passengerService.list();

        return ApiResult.success(Dispatch.dispatchV3(points.getOrigin(), passengers, drivers));
    }
}
