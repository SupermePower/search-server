package com.nb.fly.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author liying.fu
 * @description 项目
 * @date 2019/5/6 下午11:10
 */
@Setter
@Getter
@ToString
@Document(indexName = "commerce", type = "project")
public class Project {
    @Id
    private Long projectId;
    private String projectName;
    private String stallsNum;
    private String status;
    private String openningHours;
    private String areaId;
    private String signingTime;
    private String expireTime;
    private String leasePeriod;
    private String projectNature;
    private String basicBattery;
    private String smokeExhaust;
    private String smokeExhaustPipe;
    private String gasMeter;
    private String dine;
    private String constructionArea;
    private String operatingTime;
    private String engineeringStage;
    private String createTime;
    private String updateTime;
    private String isDelete;
    private String earlyWarning;
    private String gasStallsNum;
    private String parishFoodStallsNum;
    private String ratedElectricQuantit;
    private String increaseElectricity;
    private String airConditioner;
    private String heating;
    private String tableNumbers;
    private String chairNumbers;
    private String usageRate;
    private String businessStatus;
    private String floor;
    private String oldNew;
    private String diameterWaterSupply;
    private String useArea;
    private String cableSize;
    private String electricBoxOpen;
    private String waterSeparationMeter;
    private String gasSeparationMeter;
    private String wattHourMeter;
    private String generalGasMeter;
    private String gasFlow;
    private String sewerCount;
    private String areaName;
    private String deliverStatus;
    private String investmentTime;
    private String sewageRoom;
    private String internalBlower;
}
