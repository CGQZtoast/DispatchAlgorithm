package com.zard.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zard.backend.pojo.Passenger;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PassengerMapper extends BaseMapper<Passenger> {

}
