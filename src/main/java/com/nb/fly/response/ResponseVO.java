package com.nb.fly.response;

import com.nb.fly.enums.ResponseEnums;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: API响应对象
 * @author: Mr.Fu
 * @date: 2020/5/28 下午4:13
 */
@Setter
@Getter
@ToString
public class ResponseVO<T> {
    /**
     * 处理码
     */
    private Integer code;
    /**
     * 处理信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    public ResponseVO() {
        this.code = ResponseEnums.SUCCESS.getCode();
        this.message = ResponseEnums.SUCCESS.getMessage();
    }

    public ResponseVO error(String message) {
        this.code = ResponseEnums.FAILED.getCode();
        this.message = message;
        return this;
    }

    public ResponseVO<T> success(T data) {
        this.code = ResponseEnums.SUCCESS.getCode();
        this.message = ResponseEnums.SUCCESS.getMessage();
        this.data = data;
        return this;
    }

    public ResponseVO success(String message) {
        this.code = ResponseEnums.SUCCESS.getCode();
        this.message = message;
        return this;
    }
}
