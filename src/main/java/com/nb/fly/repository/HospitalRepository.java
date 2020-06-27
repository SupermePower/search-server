package com.nb.fly.repository;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @description:
 * @author: Zero
 * @date: 2020/5/21 下午10:09
 */
@Repository
public interface HospitalRepository {

    /**
     * search hospital list
     *
     * @param request search request
     * @return hospital list
     * @throws Exception e
     */
    Map<String, Object> hospitalList(SearchRequest request) throws Exception;

    Map<String, Object> hospitalList(SearchScrollRequest request) throws Exception;

    void update(UpdateRequest request) throws Exception;
}
