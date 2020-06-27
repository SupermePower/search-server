package com.nb.fly.service;

import com.nb.fly.request.QueryHospitalRequest;
import com.nb.fly.request.SaveHospitalRequest;
import com.nb.fly.request.UpdateHospitalRequest;
import com.nb.fly.response.HospitalListVO;

/**
 * @description:
 * @author: Zero
 * @date: 2020/5/21 下午10:08
 */
public interface HospitalService {

    HospitalListVO searchHospital(QueryHospitalRequest request) throws Exception;

    void create(SaveHospitalRequest request) throws Exception;

    void update(UpdateHospitalRequest request) throws Exception;
}
