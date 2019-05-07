package com.nb.fly.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * @author fly
 * @description 档口搜索
 * @date 2019/5/7 15:36
 */

public class StallSearchHelper {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
}
