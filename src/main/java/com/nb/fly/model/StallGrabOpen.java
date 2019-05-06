package com.nb.fly.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * @description 档口释放信息
 * @author liying.fu
 * @date 2019/5/6 下午11:47
 */
@Setter
@Getter
@ToString
@Document(indexName = "commerce", type = "stall_grab_open")
public class StallGrabOpen {
    @Id
    private String id;
    private Long projectId;
    private String projectName;
    private Long stallId;
    private String stallName;
    private Byte openType;
    private Date releaseTime;
    private Byte isGrabbed;
    private Byte isDelete;
    private Date createTime;
    private Date updateTime;
}
