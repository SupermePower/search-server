package com.nb.fly.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nb.fly.idworker.IdWorker;
import com.nb.fly.model.Hospital;
import com.nb.fly.model.Position;
import com.nb.fly.repository.HospitalRepository;
import com.nb.fly.request.QueryHospitalRequest;
import com.nb.fly.request.SaveHospitalRequest;
import com.nb.fly.request.UpdateHospitalRequest;
import com.nb.fly.response.HospitalListVO;
import com.nb.fly.response.HospitalVO;
import com.nb.fly.service.HospitalService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Zero
 * @date: 2020/5/21 下午10:09
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private final String INDEX_NAME = "hospital";

    private final String TYPE_NAME = "hospital";

    private final String SCROLL_ALIVE_TIME = "1d";


    private Hospital getHospital(SaveHospitalRequest request) {
        Hospital hospital = new Hospital();
        hospital.setId(idWorker.nextId());
        BeanUtils.copyProperties(request, hospital);
        Position position = new Position();
        position.setLat(request.getLatitude());
        position.setLon(request.getLongitude());
        hospital.setPosition(position);
        return hospital;
    }

    @Override
    public void create(SaveHospitalRequest request) throws Exception {
        Hospital hospital = getHospital(request);
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index(INDEX_NAME);
        indexRequest.type(TYPE_NAME);
        indexRequest.id(hospital.getId().toString());

        Map<String, Object> source = new HashMap<>();
        source.put("id", hospital.getId());
        source.put("name", hospital.getName());
        source.put("address", hospital.getAddress());
        source.put("img", hospital.getImg());
        Map<String, Double> position = new HashMap<>();
        position.put("lat", hospital.getPosition().getLat());
        position.put("lon", hospital.getPosition().getLon());
        source.put("position", position);
        indexRequest.source(source);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void update(UpdateHospitalRequest request) throws Exception {
        UpdateRequest updateRequest = getUpdateRequest(request);
        hospitalRepository.update(updateRequest);
    }

    private UpdateRequest getUpdateRequest(UpdateHospitalRequest request) {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(INDEX_NAME);
        updateRequest.type(TYPE_NAME);
        updateRequest.timeout("10s");
        updateRequest.id(request.getId());
        updateRequest.doc(JSONObject.parseObject(JSON.toJSONString(request), Map.class));
        return updateRequest;
    }

    @Override
    public HospitalListVO searchHospital(QueryHospitalRequest request) throws Exception {
        HospitalListVO response = new HospitalListVO();
        List<HospitalVO> result = new ArrayList<>();
        if (StringUtils.isEmpty(request.getScrollId())) {
            SearchRequest searchRequest = getHospitalListSearchRequest(request);
            Map<String, Object> stringObjectMap = hospitalRepository.hospitalList(searchRequest);
            ((List)stringObjectMap.get("list")).forEach(hospital -> {
                result.add(JSONObject.parseObject(JSON.toJSONString(hospital), HospitalVO.class));
            });
            response.setList(result);
            response.setScrollId(stringObjectMap.get("scrollId").toString());
        } else {
            SearchScrollRequest searchScrollRequest = getHospitalListSearchScrollRequest(request);
            Map<String, Object> stringObjectMap = hospitalRepository.hospitalList(searchScrollRequest);
            ((List)stringObjectMap.get("list")).forEach(hospital -> {
                result.add(JSONObject.parseObject(JSON.toJSONString(hospital), HospitalVO.class));
            });
            response.setList(result);
            response.setScrollId(stringObjectMap.get("scrollId").toString());
        }
        return response;
    }

    private SearchRequest getHospitalListSearchRequest(QueryHospitalRequest request) {
        QueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        GeoDistanceSortBuilder geoDistanceSort = SortBuilders.geoDistanceSort("position", request.getLatitude(), request.getLongitude());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchAllQueryBuilder);
        searchSourceBuilder.sort(geoDistanceSort);
        searchSourceBuilder.size(1);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX_NAME);
        searchRequest.types(TYPE_NAME);
        searchRequest.scroll(SCROLL_ALIVE_TIME);
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }

    private SearchScrollRequest getHospitalListSearchScrollRequest(QueryHospitalRequest request) {
        SearchScrollRequest searchScrollRequest = new SearchScrollRequest();
        searchScrollRequest.scrollId(request.getScrollId());
        searchScrollRequest.scroll(SCROLL_ALIVE_TIME);
        return searchScrollRequest;
    }
}
