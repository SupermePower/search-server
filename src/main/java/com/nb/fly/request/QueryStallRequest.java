package com.nb.fly.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author fly
 * @description 获取档口请求对象
 * @date 2019/5/6 18:55
 */
@Setter
@Getter
@ToString
public class QueryStallRequest {
    private String stallName;
}
