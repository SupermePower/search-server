package com.nb.fly.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @description:
 * @author: Zero
 * @date: 2020/6/27 下午12:15
 */
@Setter
@Getter
@ToString
public class HospitalListVO {
    @ApiModelProperty(value = "scroll id")
    private String scrollId;
    @ApiModelProperty(value = "hospital list")
    private List<HospitalVO> list;
}
