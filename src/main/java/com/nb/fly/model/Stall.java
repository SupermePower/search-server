package com.nb.fly.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

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
    private Byte businessStatus;
    private String floor;
    private BigDecimal entryFee;
    private BigDecimal monthlyRent;
    private Byte gas;
    private Byte smokeExhaust;
    private Byte exhaustSmokeLevel;
    private BigDecimal constructionArea;
    private String usageRate;
    private Byte occlusion;
    private BigDecimal basicBattery;
    private Byte airConditioning;
    private Byte houseType;
    private Byte diningOutlet;
    private Byte sewageRoom;
    private Timestamp signingTime;
    private Byte stallsLevel;
    private Byte status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Byte scopeOperation;
    private String waterMeterModel;
    private BigDecimal waterMeterInitReading;
    private String gasMeterModel;
    private BigDecimal gasMeterInitReading;
    private String gasMeter;
    private BigDecimal gasFlow;
    private String ammeterModel;
    private BigDecimal ammeterInitReading;
    private String airSwitch;
    private Byte parishFood;
    private Byte increaseCapacity;
    private Byte reform;
    private Byte decantor;
    private Byte internalFan;
    private String offlineDockerTel;
    private Byte isRent;
    private Byte businessNature;
    private BigDecimal stallsGas;
    private Byte assistInvestmentPromotion;
}
