package com.zard.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zard.backend.model.DriverMarkerModel;
import com.zard.backend.pojo.Driver;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DriverMapper extends BaseMapper<Driver> {
    List<DriverMarkerModel> getDriverMarkers();
}
