package com.nb.fly.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: Zero
 * @date: 2020/5/21 下午10:47
 */
@Setter
@Getter
@ToString
public class QueryHospitalRequest {
    @NotNull(message = "请获取地理位置")
    private Double longitude;
    @NotNull(message = "请获取地理位置")
    private Double latitude;
}
