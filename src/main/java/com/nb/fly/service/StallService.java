package com.nb.fly.service;

import com.github.pagehelper.PageInfo;
import com.nb.fly.model.Stall;
import com.nb.fly.request.QueryStallRequest;
import com.nb.fly.response.StallListVO;

import java.util.List;

/**
 * @author fly
 * @description 档口业务
 * @date 2019/5/6 18:22
 **/
public interface StallService {

    /**
     * 保存档口信息
     *
     * @param stall 档口信息
     */
    void save(Stall stall);

    /**
     * 获取档口集合
     *
     * @param request 请求参数
     * @return 档口集合
     */
    PageInfo stallList(QueryStallRequest request);

    /**
     * 修改档口信息
     *
     * @param stall 档口信息
     */
    void update(Stall stall);

    /**
     * 删除档口响应对象
     *
     * @param stallId 档口主键
     */
    void deleteStallVO(Long stallId);

    /**
     * 操作单个档口对象
     *
     * @param stallId 档口主键
     */
    void operationStall(Long stallId);

    /**
     * 操作档口信息
     */
    void operationStallList();

    /**
     * 获取档口列表信息
     *
     * @return 档口列表
     */
    List<StallListVO> queryStallListFromDb();

    /**
     * 获取档口列表信息
     *
     * @return 档口列表
     */
    Iterable<StallListVO> queryStallListFromEs();
}
