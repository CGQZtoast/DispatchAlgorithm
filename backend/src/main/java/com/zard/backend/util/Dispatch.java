package com.zard.backend.util;

import com.zard.backend.model.DispatchModel;
import com.zard.backend.model.NavigateModel;
import com.zard.backend.pojo.Driver;
import com.zard.backend.pojo.Passenger;

import java.util.*;

/**
 * @Author 乌贼
 * @Date 2023-08-28 11:00
 * @Description 派单算法
 */

public class Dispatch {
    /**
     * @param p       乘客经纬度信息
     * @param drivers 司机信息
     * @Description Ver 2.0 利用路线规划计算距离,实现基础派单功能
     * 获取司机信息，根据路程距离排序，将订单推送给最近的司机
     */
    public static DispatchModel dispatchV2_0(Point p, List<Driver> drivers) throws Exception {
        Map<Driver, NavigateModel> map = new HashMap<>();

        // 1. 遍历司机信息获取路程距离并进行排序
        for (Driver driver : drivers) {
            Point driverPoint = new Point(driver.getLatitude(), driver.getLongitude());
            // 调用导航接口，获取距离和路径信息
            NavigateModel navigateModel = Navigate.navigation(p, driverPoint);

            map.put(driver, navigateModel);
        }
        // 使用Stream API和Comparator对map进行排序
        List<Map.Entry<Driver, NavigateModel>> sortedList = map.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().getDistance()))
                .toList();

        // 2. 返回司机信息
        return new DispatchModel(sortedList.get(0).getKey(), sortedList.get(0).getValue());
    }

    /**
     * Ver 3.0 采用区域最优的派单算法
     * 使用区域最优策略，保证区域内乘客的等待时间最优——区域内所有乘客总等待时间最少。
     */
    public static DispatchModel dispatchV3(Point p, List<Passenger> passengers, List<Driver> drivers) throws Exception {
        // 当前乘客在乘客列表中的 index
        int index = 0;

        // 获取 navigateModels 信息、 weights 数组
        List<NavigateModel> navigateModels = new ArrayList<>(drivers.size() * passengers.size());
        int[][] weights = new int[drivers.size()][passengers.size()];

        for (int i = 0; i < drivers.size(); i++) {
            for (int j = 0; j < passengers.size(); j++) {
                Point p1 = new Point(drivers.get(i).getLatitude(), drivers.get(i).getLongitude());
                Point p2 = new Point(passengers.get(j).getLatitude(), passengers.get(j).getLongitude());

                if (Objects.equals(p, p2)) {
                    index = i;
                }

                NavigateModel tempModel = Navigate.navigation(p1, p2);
                navigateModels.add(tempModel);
                weights[i][j] = -tempModel.getDistance();

                System.out.println("司机" + i + "到乘客" + j + "的距离为：" + -weights[i][j]);
            }
        }

        // 传入 weights 数组，返回 driver 和 passenger 对应关系
        int[] match = KMAlgorithm.KM(weights);

        return new DispatchModel(drivers.get(match[index]), navigateModels.get(match[index]));
    }
}
