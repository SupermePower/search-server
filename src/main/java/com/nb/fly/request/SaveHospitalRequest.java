package com.nb.fly.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description:
 * @author: Zero
 * @date: 2020/5/21 下午10:07
 */
@Setter
@Getter
@ToString
public class SaveHospitalRequest {
    @ApiModelProperty(value = "hospital name")
    private String name;
    @ApiModelProperty(value = "hospital address")
    private String address;
    @ApiModelProperty(value = "longitude")
    private Double longitude;
    @ApiModelProperty(value = "latitude")
    private Double latitude;
}
