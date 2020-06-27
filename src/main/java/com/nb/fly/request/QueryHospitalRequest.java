package com.nb.fly.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @description: query hospital request
 * @author: Mr.Fu
 * @date: 2020/5/21 下午10:47
 */
@Setter
@Getter
@ToString
public class QueryHospitalRequest {
    @ApiModelProperty(value = "经度")
    @NotNull(message = "请获取地理位置")
    private Double longitude;
    @ApiModelProperty(value = "纬度")
    @NotNull(message = "请获取地理位置")
    private Double latitude;
    @ApiModelProperty(value = "scroll id")
    private String scrollId;
}
