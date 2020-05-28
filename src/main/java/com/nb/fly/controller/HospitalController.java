package com.nb.fly.controller;

import com.nb.fly.request.QueryHospitalRequest;
import com.nb.fly.request.SaveHospitalRequest;
import com.nb.fly.response.HospitalVO;
import com.nb.fly.response.ResponseVO;
import com.nb.fly.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author: Zero
 * @date: 2020/5/21 下午10:05
 */
@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping
    public ResponseVO save(@RequestBody SaveHospitalRequest request) {
        hospitalService.save(request);
        return new ResponseVO();
    }

    @GetMapping
    public ResponseVO<List<HospitalVO>> getHospital(QueryHospitalRequest request) {
        return new ResponseVO<List<HospitalVO>>().success(hospitalService.getHospital(request));
    }
}
