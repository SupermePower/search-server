package com.nb.fly.service;

import com.nb.fly.request.QueryHospitalRequest;
import com.nb.fly.request.SaveHospitalRequest;
import com.nb.fly.response.HospitalVO;

import java.util.List;

/**
 * @description:
 * @author: Zero
 * @date: 2020/5/21 下午10:08
 */
public interface HospitalService {
    void save(SaveHospitalRequest request);

    List<HospitalVO> getHospital(QueryHospitalRequest request);
}
