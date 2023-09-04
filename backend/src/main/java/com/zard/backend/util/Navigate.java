package com.zard.backend.util;

import com.google.gson.Gson;
import com.zard.backend.model.NavigateModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 乌贼
 * @Date 2023-08-15 23:07
 * @Description
 */

public class Navigate {
    /**
     * 获取两点间的距离和路径
     *
     * @param p1 起点
     * @param p2 终点
     */
    public static NavigateModel navigation(Point p1, Point p2) throws Exception {

        // 构建请求 URL
        URL url = createUrl(p1, p2);

        // 获取响应
        String jsonResponse = getResponse(url);

        // 解析响应
        MapResponse response = parseResponse(jsonResponse);

        // 提取、计算所需信息
        return calculate(response);
    }

    /**
     * 构建请求 URL
     *
     * @param p1 起点
     * @param p2 终点
     * @return 构建请求 URL
     * @throws MalformedURLException
     */
    private static URL createUrl(Point p1, Point p2) throws MalformedURLException {
        // 构建请求 URL
        String key = "E35BZ-EH6KA-GFCKL-CC4B6-XLIAQ-WBBES";

        String url = "https://apis.map.qq.com/ws/direction/v1/driving/?key=" + key +
                "&from=" + p1.getLatitude() + "," + p1.getLongitude() +
                "&to=" + p2.getLatitude() + "," + p2.getLongitude();

        // 创建、返回 URL 对象
        return new URL(url);
    }

    /**
     * 获取响应
     *
     * @param url URL 对象
     * @return 响应
     * @throws IOException
     */
    private static String getResponse(URL url) throws IOException {
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置请求方法
        connection.setRequestMethod("GET");

        // 获取响应
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder res = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            res.append(line);
        }
        reader.close();

        // 返回响应
        return res.toString();
    }

    /**
     * 解析响应
     *
     * @param jsonResponse 响应
     * @return 对应结构数据
     */
    private static MapResponse parseResponse(String jsonResponse) {
        // 在这里解析 jsonResponse，提取路线信息，并进行绘制
        // 创建 Gson 实例
        Gson gson = new Gson();

        // 将json格式数据转化为对应结构
        return gson.fromJson(jsonResponse, MapResponse.class);
    }

    /**
     * @param response 对应结构数据
     * @return 两点间的距离以及路径
     */
    private static NavigateModel calculate(MapResponse response) {
        NavigateModel navigateModel = new NavigateModel();

        if (response != null && response.getResult() != null && response.getResult().getRoutes() != null) {
            // 获取距离信息
            int distance = response.getResult().getRoutes().get(0).getDistance();

            List<Double> coors = response.getResult().getRoutes().get(0).getPolyline();

            // 坐标解压
            double kr = 1000000;

            for (int i = 2; i < coors.size(); i += 1) {
                double newValue = coors.get(i - 2) + coors.get(i) / kr;
                coors.set(i, newValue);
            }

            List<Point> pl = new ArrayList<>();
            for (int i = 0; i < coors.size(); i += 2) {
                pl.add(new Point(coors.get(i), coors.get(i + 1)));
            }

            navigateModel.setDistance(distance);
            navigateModel.setPolyline(pl);
        }

        return navigateModel;
    }
}

class MapResponse {
    private MapResult result;

    public MapResult getResult() {
        return result;
    }

    public void setResult(MapResult result) {
        this.result = result;
    }
}

class MapResult {
    private List<Route> routes;

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}

class Route {
    private int distance;
    private List<Double> polyline;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<Double> getPolyline() {
        return polyline;
    }

    public void setPolyline(List<Double> polyline) {
        this.polyline = polyline;
    }
}
