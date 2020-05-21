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
 * @description 签约信息
 * @date 2019/5/6 下午11:26
 */
@Setter
@Getter
@ToString
@Document(indexName = "contract", type = "contract")
public class Contract {
    @Id
    private Long id;
    private Long paymentId;
    private Long merchantId;
    private String businessUserId;
    private String businessUserName;
    private String businessUserTel;
    private Long projectId;
    private String projectName;
    private Long stallId;
    private String stallName;
    private String contact;
    private String contactTel;
    private Timestamp contractTime;
    private String underLineUserId;
    private String underLineUserName;
    private String underLineUserTel;
    private Timestamp enterTime;
    private Timestamp leaveTime;
    private Timestamp startTime;
    private Timestamp endTime;
    private Long paymentType;
    private String paymentTypeName;
    private Long discountType;
    private BigDecimal slottingFee;
    private BigDecimal slottingDiscount;
    private BigDecimal slottingPayment;
    private BigDecimal rentFee;
    private BigDecimal rentDiscount;
    private BigDecimal rentPayment;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer status;
    private Integer developmentCycle;
    private Long parentContract;
    private Timestamp delayReleaseTime;
    private Integer branchNo;
    private Byte replaceOperation;
    private Integer isRenewal;
    private Long subletContractId;
    private Integer contractPeriod;
    private Timestamp adjustmentTime;
}
