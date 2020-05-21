package com.nb.fly.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author liying.fu
 * @description 项目
 * @date 2019/5/6 下午11:10
 */
@Setter
@Getter
@ToString
@Document(indexName = "project", type = "project")
public class Project {
    @Id
    private Long projectId;
    private String projectName;
    private Integer stallsNum;
    private Byte status;
    private Timestamp openningHours;
    private Long areaId;
    private Timestamp signingTime;
    private Timestamp expireTime;
    private String leasePeriod;
    private Byte projectNature;
    private String basicBattery;
    private String smokeExhaust;
    private String smokeExhaustPipe;
    private String gasMeter;
    private Byte dine;
    private BigDecimal constructionArea;
    private String operatingTime;
    private Byte engineeringStage;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Byte earlyWarning;
    private Integer gasStallsNum;
    private Integer parishFoodStallsNum;
    private BigDecimal ratedElectricQuantit;
    private BigDecimal increaseElectricity;
    private Byte airConditioner;
    private Byte heating;
    private Integer tableNumbers;
    private Integer chairNumbers;
    private BigDecimal usageRate;
    private String businessStatus;
    private String floor;
    private Byte oldNew;
    private String diameterWaterSupply;
    private BigDecimal useArea;
    private BigDecimal cableSize;
    private BigDecimal electricBoxOpen;
    private BigDecimal waterSeparationMeter;
    private BigDecimal gasSeparationMeter;
    private BigDecimal wattHourMeter;
    private BigDecimal generalGasMeter;
    private String gasFlow;
    private Integer sewerCount;
    private String areaName;
    private Byte deliverStatus;
    private Timestamp investmentTime;
    private String sewageRoom;
    private String internalBlower;
}
