package com.nb.fly.enums;

/**
 * @description: 响应信息枚举
 * @author: Zero
 * @date: 2020/5/28 下午4:07
 */
public enum  ResponseEnums {

    /**
     * <p>
     *     code-0
     * </p>
     *
     * <p>
     *     message-SUCCESS
     * </p>
     */
    SUCCESS(0, "SUCCESS"),
    /**
     * <p>
     *     code-1
     * </p>
     *
     * <p>
     *     message-FAILED
     * </p>
     */
    FAILED(1, "FAILED");

    private Integer code;
    private String message;

    ResponseEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
