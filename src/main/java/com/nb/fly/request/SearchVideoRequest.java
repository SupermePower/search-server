package com.nb.fly.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @description: search video request
 * @author: Mr.Fu
 * @date: 2020/6/26 下午12:37
 */
@Setter
@Getter
@ToString
public class SearchVideoRequest {
    /**
     * scroll id
     */
    @ApiModelProperty(value = "scroll id")
    private String scrollId;
    /**
     * keyword
     */
    @ApiModelProperty(value = "keyword")
    @NotBlank(message = "请输入搜索内容")
    private String keyword;
}
