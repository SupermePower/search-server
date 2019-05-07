package com.nb.fly.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

/**
 * 档口列表VO类
 * @author fly
 * @date 2019-05-07 10:17
 */
@Getter
@Setter
@ToString
@Document(indexName = "commerce", type = "stall_list")
public class StallListVO {

    @Id
    private Long stallId;

    private Long projectId;

    private String projectName;

    private String stallName;

    private BigDecimal area;

    private BigDecimal electricity;

    private Integer gas;

    private BigDecimal slotting;

    private BigDecimal rent;

    private String projectStatus;

    private String projectProperty;

    private String stallProperty;

    private Byte status;

    private Integer promotions;

    private Integer hasLock;

    private Integer myStall;

    private Integer assistInvestmentPromotion;

    private Long merchantId;

    private String secondClassificationName;
}
