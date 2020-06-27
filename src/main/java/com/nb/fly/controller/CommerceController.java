package com.nb.fly.controller;

import com.github.pagehelper.PageInfo;
import com.nb.fly.request.QueryStallRequest;
import com.nb.fly.response.StallListVO;
import com.nb.fly.service.StallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Mr.Fu
 * @description 商务控制器
 * @date 2019/5/6 18:12
 */
@RestController
@RequestMapping("/stall")
public class CommerceController {

    @Autowired
    private StallService stallService;

    /**
     * 获取档口列表
     *
     * @return 档口列表
     */
    @GetMapping
    public PageInfo queryStallList(QueryStallRequest request) {
        return stallService.stallList(request);
    }

    /**
     * 操作单个档口信息
     *
     * @param stallId 档口主键
     * @return 操作结果
     */
    @PostMapping(path = "/{stallId}")
    public String operationStall(@PathVariable Long stallId) {
        stallService.operationStall(stallId);
        return "SUCCESS";
    }

    /**
     * 操作档口信息
     *
     * @return 操作结果
     */
    @PostMapping
    public String operationStallList() {
        stallService.operationStallList();
        return "SUCCESS";
    }

    /**
     * 测试查询全部信息从DB
     *
     * @return 档口信息
     */
    @GetMapping(path = "db")
    public List<StallListVO> queryStallListFromDb() {
        return stallService.queryStallListFromDb();
    }

    /**
     * 测试获取全部信息从es
     *
     * @return 档口信息
     */
    @GetMapping(path = "es")
    public Iterable<StallListVO> queryStallListFromEs() {
        return stallService.queryStallListFromEs();
    }
}
