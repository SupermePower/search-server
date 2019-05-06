package com.nb.fly.service;

import com.nb.fly.model.Stall;
import com.nb.fly.request.QueryStallRequest;

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
    List<Stall> stallList(QueryStallRequest request);

    /**
     * 修改档口信息
     *
     * @param stall 档口信息
     */
    void update(Stall stall);
}
