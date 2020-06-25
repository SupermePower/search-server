package com.nb.fly.service.impl;

import com.nb.fly.response.ResponseVO;
import com.nb.fly.response.VideoVO;
import com.nb.fly.service.VideoService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: video service implement
 * @author: Zero
 * @date: 2020/6/25 下午8:37
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private TransportClient transportClient;

    @Override
    public ResponseVO<Map<String, Object>> queryVideo(String scrollId, String keyword) {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("videoName", keyword);
        SearchResponse searchResponse;
        if (StringUtils.isEmpty(scrollId)) {
            searchResponse = transportClient.prepareSearch("video_item")
                    .setQuery(matchQueryBuilder)
                    .setSize(5)
                    .setScroll("10m")
                    .execute()
                    .actionGet();
        } else {
            searchResponse = transportClient.prepareSearchScroll(scrollId)
                    .setScroll("10m")
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
}
