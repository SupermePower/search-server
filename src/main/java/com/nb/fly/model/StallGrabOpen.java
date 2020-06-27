package com.nb.fly.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.sql.Timestamp;

/**
 * @author Mr.Fu
 * @description 档口释放信息
 * @date 2019/5/6 下午11:47
 */
@Setter
@Getter
@ToString
@Document(indexName = "stall_grab_open", type = "stall_grab_open")
public class StallGrabOpen {
    @Id
    private Long id;
    private Long projectId;
    private String projectName;
    private Long stallId;
    private String stallName;
    private Byte openType;
    private Timestamp releaseTime;
    private Byte isGrabbed;
    private Timestamp createTime;
    private Timestamp updateTime;
}
