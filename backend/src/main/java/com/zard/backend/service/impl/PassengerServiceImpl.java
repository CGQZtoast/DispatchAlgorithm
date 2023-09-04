package com.zard.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zard.backend.dao.PassengerMapper;
import com.zard.backend.pojo.Passenger;
import com.zard.backend.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger> implements PassengerService {
    @Autowired
    private PassengerMapper passengerMapper;
}
