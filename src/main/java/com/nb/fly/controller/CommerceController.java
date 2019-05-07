package com.nb.fly.controller;

import com.nb.fly.model.Stall;
import com.nb.fly.request.QueryStallRequest;
import com.nb.fly.service.StallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author fly
 * @description 商务控制器
 * @date 2019/5/6 18:12
 */
@RestController
@RequestMapping("/commerce")
public class CommerceController {

    @Autowired
    private StallService stallService;

    /**
     * 获取档口列表
     * @return 档口列表
     */
    @GetMapping("/stall-list")
    public Page<Stall> queryStallList(QueryStallRequest request) {
        return stallService.stallList(request);
    }
}
