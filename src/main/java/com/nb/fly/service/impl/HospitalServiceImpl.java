package com.nb.fly.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.nb.fly.helper.DistanceHelper;
import com.nb.fly.idworker.IdWorker;
import com.nb.fly.model.Hospital;
import com.nb.fly.model.Position;
import com.nb.fly.repository.HospitalRepository;
import com.nb.fly.request.QueryHospitalRequest;
import com.nb.fly.request.SaveHospitalRequest;
import com.nb.fly.response.HospitalVO;
import com.nb.fly.service.HospitalService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

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

    @Override
    public void save(SaveHospitalRequest request) {
        Hospital hospital = getHospital(request);
        hospitalRepository.save(hospital);
    }

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
    public List<HospitalVO> getHospital(QueryHospitalRequest request) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        GeoDistanceSortBuilder distanceSortBuilder = new GeoDistanceSortBuilder("position", request.getLatitude(), request.getLongitude());
        distanceSortBuilder.unit(DistanceUnit.KILOMETERS);
        distanceSortBuilder.order(SortOrder.ASC);
        nativeSearchQueryBuilder.withSort(distanceSortBuilder);
        Page<Hospital> search = hospitalRepository.search(nativeSearchQueryBuilder.build());
        List<Hospital> content = search.getContent();
        List<HospitalVO> hospitalVOS = new ArrayList<>();
        for (Hospital hospital : content) {
            HospitalVO hospitalVO = new HospitalVO();
            BeanUtils.copyProperties(hospital, hospitalVO);
            hospitalVO.setDistance(DistanceHelper.getDistance(request.getLongitude(), request.getLatitude(), hospital.getPosition().getLon().doubleValue(), hospital.getPosition().getLat().doubleValue()));
            hospitalVOS.add(hospitalVO);
        }
        return hospitalVOS;
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
    public List<HospitalVO> searchHospital(QueryHospitalRequest request) throws Exception {
        QueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        GeoDistanceSortBuilder geoDistanceSort = SortBuilders.geoDistanceSort("position", request.getLatitude(), request.getLongitude());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchAllQueryBuilder);
        searchSourceBuilder.sort(geoDistanceSort);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX_NAME);
        searchRequest.types(TYPE_NAME);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        List<HospitalVO> hospitalList = new ArrayList<>();
        for (SearchHit hit : hits) {
            Map<String, Object> hospitalMap = hit.getSourceAsMap();
            HospitalVO hospitalVO = JSONObject.parseObject(JSONObject.toJSONString(hospitalMap), HospitalVO.class);
            Map<String, Double> position = (Map<String, Double>) hospitalMap.get("position");
            hospitalVO.setDistance(DistanceHelper.getDistance(request.getLongitude(), request.getLatitude(), position.get("lon"), position.get("lat")));
            hospitalList.add(hospitalVO);
        }
        return hospitalList;
    }
}
