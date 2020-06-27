package com.nb.fly.service.impl;

import com.nb.fly.response.AssociativeWordsVO;
import com.nb.fly.service.AssociativeWordsService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 联想词
 * @author: Mr.Fu
 * @date: 2020/5/28 下午4:29
 */
@Service
public class AssociativeWordsServiceImpl implements AssociativeWordsService {

    @Autowired
    private TransportClient transportClient;

    /**
     * 联想词
     *
     * @param keyWord 联想词
     * @return 联想词
     */
    @Override
    public List<AssociativeWordsVO> associativeWordsService(String keyWord) {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", keyWord);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<font color = \"red\">");
        highlightBuilder.postTags("</font>");
        SearchResponse searchResponse = transportClient.prepareSearch("hospital")
                .setQuery(matchQueryBuilder)
                .highlighter(highlightBuilder)
                .execute()
                .actionGet();
        SearchHits hits = searchResponse.getHits();
        List<AssociativeWordsVO> responseList = new ArrayList<>();
        for (SearchHit searchHit : hits) {
            Map<String, Object> sourceMap = searchHit.getSourceAsMap();

            Text[] name = searchHit.getHighlightFields().get("name").getFragments();
            StringBuilder hight = new StringBuilder();
            if (name != null) {
                for (Text str : name) {
                    hight.append(str);
                }
            }
            sourceMap.put("name", hight.toString());

            AssociativeWordsVO response = new AssociativeWordsVO();
            response.setName(searchHit.getSourceAsMap());
            responseList.add(response);
        }
        return responseList;
    }
}
