package com.nb.fly.helper;

import java.text.DecimalFormat;

/**
 * @description:
 * @author: Zero
 * @date: 2020/5/22 上午12:48
 */
public class DistanceHelper {

    private static final double EARTH_RADIUS = 6371393;

    /**
     * 把经纬度转为度（°）
     *
     * @param d 纬度
     * @return rad
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位：千米
     *
     * @param currentLon 当前经度
     * @param currentLat 当前纬度
     * @param aimsLon    目标经度
     * @param aimsLat    目标纬度
     * @return 两点距离
     */
    public static double getDistance(double currentLon, double currentLat, double aimsLon, double aimsLat) {
        double radLat1 = rad(currentLat);
        double radLat2 = rad(aimsLat);
        double a = radLat1 - radLat2;
        double b = rad(currentLon) - rad(aimsLon);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = distance / 1000;
        DecimalFormat df = new DecimalFormat("#.00");
        distance = Double.parseDouble(df.format(distance));
        return distance;
    }
}
