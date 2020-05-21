package com.nb.fly.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;


/**
 * 商户
 * @author fly
 * @date 2019-05-08 14:56
 */
@Getter
@Setter
@ToString
@Document(indexName = "merchant", type = "merchant")
public class MerchantListVO {

    @Id
    private Long id;

    private String merchantName;

    private String brand;

    private String contract;

    private String executorId;

    private String executor;

    private String firstClassification;

    private Integer cityId;

    private String city;

    private Date claimTime;

    private String sClaimTime;

    private Integer cycle;

    private Date recentCommunicationTime;

    private String sRecentCommunicationTime;

    private Date createTime;

    private String sCreateTime;

    private Integer merchantType;

    private Integer numberStore;

    private String phoneNo;

    private String phoneNo2;

    private String secondClassification;

    private String merchantLevel;

    private Date endTime;

    private String sEndTime;

    private Integer delay;

    private Integer followNumber;

    private Integer contractNumber;

}
