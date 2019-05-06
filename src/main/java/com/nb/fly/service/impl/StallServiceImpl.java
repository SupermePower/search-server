package com.nb.fly.service.impl;

import com.nb.fly.model.Stall;
import com.nb.fly.repository.StallRepository;
import com.nb.fly.request.QueryStallRequest;
import com.nb.fly.service.StallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fly
 * @description 档口业务实现
 * @date 2019/5/6 18:22
 */
@Slf4j
@Service
public class StallServiceImpl implements StallService {

    @Autowired
    private StallRepository stallRepository;


    /**
     * 保存档口信息
     *
     * @param stall 档口信息
     */
    @Override
    public void save(Stall stall) {
        Stall saveStall = stallRepository.save(stall);
        log.info("save result -> {}", saveStall);
    }

    /**
     * 获取档口集合
     *
     * @param request 请求参数
     * @return 档口集合
     */
    @Override
    public List<Stall> stallList(QueryStallRequest request) {
        return stallRepository.findByStallsName(request.getStallName());
    }

    /**
     * 修改档口信息
     *
     * @param stall 档口信息
     */
    @Override
    public void update(Stall stall) {
        stallRepository.save(stall);
    }
}
