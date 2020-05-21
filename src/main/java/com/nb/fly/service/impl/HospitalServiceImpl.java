package com.nb.fly.service.impl;

import com.nb.fly.helper.DistanceHelper;
import com.nb.fly.idworker.IdWorker;
import com.nb.fly.model.Hospital;
import com.nb.fly.model.Position;
import com.nb.fly.repository.HospitalRepository;
import com.nb.fly.request.QueryHospitalRequest;
import com.nb.fly.request.SaveHospitalRequest;
import com.nb.fly.response.HospitalVO;
import com.nb.fly.service.HospitalService;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void save(SaveHospitalRequest request) {
        Hospital hospital = new Hospital();
        hospital.setId(idWorker.nextId());
        BeanUtils.copyProperties(request, hospital);
        Position position = new Position();
        position.setLat(request.getLatitude());
        position.setLon(request.getLongitude());
        hospital.setPosition(position);
        hospitalRepository.save(hospital);
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
            hospitalVO.setDistance(DistanceHelper.getDistance(request.getLongitude(), request.getLatitude(), hospital.getPosition().getLon(), hospital.getPosition().getLat()));
            hospitalVOS.add(hospitalVO);
        }
        return hospitalVOS;
    }
}
