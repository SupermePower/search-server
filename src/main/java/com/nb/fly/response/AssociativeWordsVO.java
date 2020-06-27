package com.nb.fly.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @description: 关键词搜索结果
 * @author: Mr.Fu
 * @date: 2020/5/28 下午4:23
 */
@Setter
@Getter
@ToString
public class AssociativeWordsVO {
    private Map<String, Object> name;
}
