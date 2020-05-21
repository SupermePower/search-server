package com.nb.fly.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author fly
 * @description 项目预警
 * @date 2019-05-08 13:11
 */
@Setter
@Getter
@ToString
@Document(indexName = "early_warning", type = "early_warning")
public class ProjectEarlyWarningModel {
    @Id
    private Long id;

    private Long projectId;

    private String schemaName;

    private Integer stallsNum;

    private BigDecimal rentDiscount;

    private BigDecimal slottingDiscount;

    private Byte valid;

    private Date activeTime;

    private Integer execute;
}
