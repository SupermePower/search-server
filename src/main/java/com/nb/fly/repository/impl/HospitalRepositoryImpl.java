package com.nb.fly.repository.impl;

import com.nb.fly.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Mr.Fu
 * @date: 2020/5/21 下午10:09
 */
@Repository
@Slf4j
public class HospitalRepositoryImpl implements HospitalRepository{

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * search hospital list
     *
     * @param request search request
     * @return hospital list
     * @throws Exception e
     */
    @Override
    public Map<String, Object> hospitalList(SearchRequest request) throws Exception {
        log.info("search hospital list dsl -> {}", request.source().toString());
        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        List<Map<String, Object>> hospitalList = new ArrayList<>();
        for (SearchHit hit : hits) {
            hospitalList.add(hit.getSourceAsMap());
        }
        Map<String, Object> result = new HashMap<>();
        result.put("list", hospitalList);
        result.put("scrollId", searchResponse.getScrollId());
        log.info("search hospital list total -> {}", hits.totalHits);
        return result;
    }

    @Override
    public Map<String, Object> hospitalList(SearchScrollRequest request) throws Exception {
        log.info("search hospital scroll list dsl -> {}", request.toString());
        SearchResponse searchResponse = restHighLevelClient.scroll(request, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        List<Map<String, Object>> hospitalList = new ArrayList<>();
        for (SearchHit hit : hits) {
            hospitalList.add(hit.getSourceAsMap());
        }
        Map<String, Object> result = new HashMap<>();
        result.put("list", hospitalList);
        result.put("scrollId", searchResponse.getScrollId());
        log.info("search hospital scroll list total -> {}", hits.totalHits);
        return result;
    }

    @Override
    public void update(UpdateRequest request) throws Exception {
        log.info("update hospital request -> {}", request.toString());
        UpdateResponse update = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        log.info("update hospital status -> {}", update.status().getStatus());
    }
}
