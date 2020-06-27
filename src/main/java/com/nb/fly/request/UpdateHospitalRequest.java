package com.nb.fly.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @description: update hospital request
 * @author: Zero
 * @date: 2020/6/27 上午11:27
 */
@Setter
@Getter
@ToString
public class UpdateHospitalRequest {
    @ApiModelProperty(value = "主键", required = true)
    @NotBlank(message = "请选择医院")
    private String id;
    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "请输入名称")
    private String name;
    @ApiModelProperty(value = "地址", required = true)
    @NotBlank(message = "请输入地址")
    private String address;
}
