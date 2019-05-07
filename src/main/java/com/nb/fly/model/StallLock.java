package com.nb.fly.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.sql.Timestamp;

/**
 * @author liying.fu
 * @description 档口锁定信息
 * @date 2019/5/6 下午11:39
 */
@Setter
@Getter
@ToString
@Document(indexName = "commerce", type = "stall_lock")
public class StallLock {
    @Id
    private Long id;
    private Long projectId;
    private String projectName;
    private Long stallId;
    private String stallName;
    private String merchantName;
    private Long merchantId;
    private String userId;
    private String userName;
    private Timestamp lockStartTime;
    private Timestamp lockEndTime;
    private Byte delay;
    private Timestamp delayEndTime;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer status;
    private Integer stage;
}
