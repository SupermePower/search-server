package com.nb.fly.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author fly
 * @description 档口数据
 * @date 2019/5/6 18:14
 */
@Setter
@Getter
@ToString
@Document(indexName = "commerce", type = "stall")
public class Stall implements Serializable {
    @Id
    private Long stallsId;
    private String stallsName;
    private Long projectId;
    private String offlineDocker;
    private String businessStatus;
    private String floor;
    private String entryFee;
    private String monthlyRent;
    private String gas;
    private String smokeExhaust;
    private Byte exhaustSmokeLevel;
    private String constructionArea;
    private BigDecimal usageRate;
    private String occlusion;
    private String basicBattery;
    private String airConditioning;
    private Byte houseType;
    private String diningOutlet;
    private String sewageRoom;
    private Date signingTime;
    private Byte stallsLevel;
    private Byte status;
    private String createTime;
    private String updateTime;
    private String scopeOperation;
    private String waterMeterModel;
    private String waterMeterInitReading;
    private String gasMeterModel;
    private String gasMeterInitReading;
    private String gasMeter;
    private String gasFlow;
    private String ammeterModel;
    private String ammeterInitReading;
    private String airSwitch;
    private String parishFood;
    private String increaseCapacity;
    private String reform;
    private String decantor;
    private String internalFan;
    private String offlineDockerTel;
    private String isRent;
    private String businessNature;
    private String stallsGas;
    private String assistInvestmentPromotion;
}
