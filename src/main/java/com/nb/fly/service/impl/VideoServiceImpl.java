package com.nb.fly.service.impl;

import com.nb.fly.request.SearchVideoRequest;
import com.nb.fly.response.ResponseVO;
import com.nb.fly.response.VideoVO;
import com.nb.fly.service.VideoService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: video service implement
 * @author: Mr.Fu
 * @date: 2020/6/25 下午8:37
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private TransportClient transportClient;

    @Autowired
    private RestHighLevelClient highLevelClient;

    /**
     * scroll id alive time
     */
    private final String SCROLL_ALIVE_TIME = "10m";

    @Override
    public ResponseVO<Map<String, Object>> queryVideo(String scrollId, String keyword) {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("videoName", keyword);
        SearchResponse searchResponse;
        if (StringUtils.isEmpty(scrollId)) {
            searchResponse = transportClient.prepareSearch("video_item")
                    .setQuery(matchQueryBuilder)
                    .setSize(5)
                    .setScroll(SCROLL_ALIVE_TIME)
                    .execute()
                    .actionGet();
        } else {
            searchResponse = transportClient.prepareSearchScroll(scrollId)
                    .setScroll(SCROLL_ALIVE_TIME)
                    .execute()
                    .actionGet();
        }
        List<VideoVO> videoList = new ArrayList<>();
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            VideoVO video = new VideoVO();
            video.setVideoId(Long.parseLong(sourceAsMap.get("id").toString()));
            video.setVideoName(sourceAsMap.get("videoName").toString());
            videoList.add(video);
        }

        Map<String, Object> result = new HashMap<>(4);
        result.put("list", videoList);
        result.put("scrollId", searchResponse.getScrollId());
        return new ResponseVO<Map<String, Object>>().success(result);
    }

    /**
     * search video
     *
     * @param request scrollId, keyword
     * @return video collection
     */
    @Override
    public ResponseVO<Map<String, Object>> searchVideo(SearchVideoRequest request) throws Exception {
        SearchSourceBuilder searchSourceBuilder = getSearchSourceBuilder(request);

        SearchResponse searchResponse = getSearchResponse(request, searchSourceBuilder);

        SearchHits hits = searchResponse.getHits();

        Map<String, Object> result = getResult(searchResponse, hits);
        return new ResponseVO<Map<String, Object>>().success(result);
    }

    /**
     * get result
     *
     * @param searchResponse search response
     * @param hits           hits
     * @return result
     */
    private Map<String, Object> getResult(SearchResponse searchResponse, SearchHits hits) {
        List<VideoVO> videoList = new ArrayList<>();
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            VideoVO video = new VideoVO();
            video.setVideoId(Long.parseLong(sourceAsMap.get("id").toString()));
            video.setVideoName(sourceAsMap.get("videoName").toString());
            videoList.add(video);
        }
        Map<String, Object> result = new HashMap<>(4);
        result.put("list", videoList);
        result.put("scrollId", searchResponse.getScrollId());
        return result;
    }

    /**
     * get search source builder
     *
     * @param request request
     * @return search source builder
     */
    private SearchSourceBuilder getSearchSourceBuilder(SearchVideoRequest request) {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("videoName", request.getKeyword());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchQueryBuilder);
        searchSourceBuilder.size(5);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(30));
        return searchSourceBuilder;
    }

    /**
     * Get search response
     *
     * @param request             request
     * @param searchSourceBuilder search source builder
     * @return search response
     * @throws IOException io exception
     */
    private SearchResponse getSearchResponse(SearchVideoRequest request, SearchSourceBuilder searchSourceBuilder) throws IOException {
        SearchResponse searchResponse;
        if (!StringUtils.isEmpty(request.getScrollId())) {
            SearchScrollRequest searchScrollRequest = new SearchScrollRequest();
            searchScrollRequest.scroll(SCROLL_ALIVE_TIME);
            searchScrollRequest.scrollId(request.getScrollId());
            searchResponse = highLevelClient.scroll(searchScrollRequest, RequestOptions.DEFAULT);
        } else {
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.searchType(SearchType.QUERY_THEN_FETCH);
            searchRequest.source(searchSourceBuilder);
            searchRequest.scroll(SCROLL_ALIVE_TIME);
            searchRequest.indices("video_item");
            searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        }
        return searchResponse;
    }
}
