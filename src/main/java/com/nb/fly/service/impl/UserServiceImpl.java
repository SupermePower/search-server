package com.nb.fly.service.impl;

import com.nb.fly.response.ResponseVO;
import com.nb.fly.service.UserService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户业务
 * @author: Zero
 * @date: 2020/5/30 下午10:56
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TransportClient transportClient;

    /**
     * 获取用户信息
     *
     * @param keyword 关键词
     * @return 用户信息
     */
    @Override
    public ResponseVO queryUser(String keyword) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        MatchQueryBuilder nameQuery = QueryBuilders.matchQuery("name", keyword);
        queryBuilder.should(nameQuery);
        MatchQueryBuilder namePinYinQuery = QueryBuilders.matchQuery("name.pinyin", keyword);
        queryBuilder.should(namePinYinQuery);
        SearchResponse userResponse = transportClient.prepareSearch("user")
                .setQuery(queryBuilder)
                .execute()
                .actionGet();
        SearchHits hits = userResponse.getHits();
        List<Map<String, Object>> result = new ArrayList<>();
        for (SearchHit hit: hits) {
            Map<String, Object> source = hit.getSourceAsMap();
            result.add(source);
        }
        return new ResponseVO<>().success(result);
    }
}
