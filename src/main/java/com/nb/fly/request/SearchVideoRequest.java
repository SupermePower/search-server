package com.nb.fly.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: search video request
 * @author: Zero
 * @date: 2020/6/26 下午12:37
 */
@Setter
@Getter
@ToString
public class SearchVideoRequest {
    /**
     * scroll id
     */
    private String scrollId;
    /**
     * keyword
     */
    private String keyword;
}
