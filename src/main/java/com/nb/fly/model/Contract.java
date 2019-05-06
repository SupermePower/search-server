package com.nb.fly.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author liying.fu
 * @description 签约信息
 * @date 2019/5/6 下午11:26
 */
@Setter
@Getter
@ToString
@Document(indexName = "commerce", type = "contract")
public class Contract {
    @Id
    private String id;
    private String paymentId;
    private String merchantId;
    private String businessUserId;
    private String businessUserName;
    private String businessUserTel;
    private String projectId;
    private String projectName;
    private String stallId;
    private String stallName;
    private String contact;
    private String contactTel;
    private String contractTime;
    private String underLineUserId;
    private String underLineUserName;
    private String underLineUserTel;
    private String enterTime;
    private String leaveTime;
    private String startTime;
    private String endTime;
    private String paymentType;
    private String paymentTypeName;
    private String discountType;
    private String slottingFee;
    private String slottingDiscount;
    private String slottingPayment;
    private String rentFee;
    private String rentDiscount;
    private String rentPayment;
    private String createTime;
    private String updateTime;
    private String isDelete;
    private String status;
    private String developmentCycle;
    private String parentContract;
    private String delayReleaseTime;
    private String branchNo;
    private String replaceOperation;
    private String isRenewal;
    private String isValid;
    private String subletContractId;
    private String contractPeriod;
    private String adjustmentTime;
}
