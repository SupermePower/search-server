package com.nb.fly.controller;

import com.nb.fly.request.QueryHospitalRequest;
import com.nb.fly.request.SaveHospitalRequest;
import com.nb.fly.request.UpdateHospitalRequest;
import com.nb.fly.response.HospitalListVO;
import com.nb.fly.response.ResponseVO;
import com.nb.fly.service.HospitalService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description:
 * @author: Zero
 * @date: 2020/5/21 下午10:05
 */
@Api(tags = "Hospital", description = "Hospital")
@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping
    public ResponseVO create(@RequestBody @Valid SaveHospitalRequest request) throws Exception {
        hospitalService.create(request);
        return new ResponseVO();
    }

    @GetMapping
    public ResponseVO<HospitalListVO> searchHospital(@Validated QueryHospitalRequest request) throws Exception {
        return new ResponseVO<HospitalListVO>().success(hospitalService.searchHospital(request));
    }

    @PutMapping
    public ResponseVO update(@RequestBody @Valid UpdateHospitalRequest request) throws Exception {
        hospitalService.update(request);
        return new ResponseVO();
    }
}
