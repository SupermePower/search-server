package com.nb.fly.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: create hospital request
 * @author: Zero
 * @date: 2020/5/21 下午10:07
 */
@Setter
@Getter
@ToString
public class SaveHospitalRequest {
    @ApiModelProperty(value = "hospital name")
    @NotBlank(message = "请填写医院名称")
    private String name;
    @ApiModelProperty(value = "hospital address")
    @NotBlank(message = "请填写医院地址")
    private String address;
    @ApiModelProperty(value = "longitude")
    @NotNull(message = "请填医院地理位置")
    private Double longitude;
    @ApiModelProperty(value = "latitude")
    @NotNull(message = "请填医院地理位置")
    private Double latitude;
}
