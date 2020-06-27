package com.nb.fly.service;

import com.nb.fly.request.SearchVideoRequest;
import com.nb.fly.response.ResponseVO;

import java.util.Map;

/**
 * @description: video service
 * @author: Mr.Fu
 * @date: 2020/6/25 下午8:37
 */
public interface VideoService {

    /**
     * query video
     *
     * @param scrollId scroll id
     * @param keyword  keyword
     * @return video list
     */
    ResponseVO<Map<String, Object>> queryVideo(String scrollId, String keyword);

    /**
     * search video
     *
     * @param request scrollId, keyword
     * @return video collection
     */
    ResponseVO<Map<String, Object>> searchVideo(SearchVideoRequest request) throws Exception;
}
