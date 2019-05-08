package com.nb.fly.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author fly
 * @description 获取档口请求对象
 * @date 2019/5/6 18:55
 */
@Setter
@Getter
@ToString
public class QueryStallRequest {

    /**
     * 项目名称
     */
    private String name;

    /**
     * 档口性质
     */
    private String stallProperty;

    /**
     * 最小价格
     */
    private BigDecimal minPrice;

    /**
     * 最大价格
     */
    private BigDecimal maxPrice;

    /**
     * 最小使用面积
     */
    private BigDecimal minArea;

    /**
     * 最大使用面积
     */
    private BigDecimal maxArea;

    /**
     * 最小基础电量
     */
    private BigDecimal minElectricity;

    /**
     * 最大基础电量
     */
    private BigDecimal maxElectricity;

    /**
     * 户型，0方正，1异形
     */
    private Integer houseType;

    /**
     * 是否遮挡，0无遮挡，1有遮挡
     */
    private Integer occlusion;

    /**
     * 燃气，0有，1无
     */
    private Integer gas;

    /**
     * 堂食，0无，1有
     */
    private Integer dine;

    /**
     * 状态，0招商，5期至，100我的预订,200我的签约
     */
    private Integer status;

    /**
     * 抢占类型，1、正常档口，2、11:00开放档口，3、15:00开放档口
     */
    private Integer grabType;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 城市ID
     */
    private Set<Integer> cityIdList;

    /**
     * 当前页
     */
    private Integer startPage;

    /**
     * 每页记录数
     */
    private Integer pageSize;
}
