package com.nb.fly.service.impl;

import com.nb.fly.mapper.StallMapper;
import com.nb.fly.model.Stall;
import com.nb.fly.repository.StallListVORepository;
import com.nb.fly.repository.StallRepository;
import com.nb.fly.request.QueryStallRequest;
import com.nb.fly.response.StallListVO;
import com.nb.fly.service.StallService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    @Autowired
    private StallMapper stallMapper;

    @Autowired
    private StallListVORepository stallListVORepository;


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
    public Page<StallListVO> stallList(QueryStallRequest request) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 模糊搜索
        boolQueryBuilder.must(QueryBuilders.fuzzyQuery("projectName", request.getName()));
        return stallListVORepository.search(boolQueryBuilder, PageRequest.of(1, 4));
    }

    /**
     * 修改档口信息
     *
     * @param stall 档口信息
     */
    @Override
    public void update(Stall stall) {
        Stall updateStall = stallRepository.save(stall);
        log.info("update result -> {}", updateStall);
    }

    /**
     * 删除档口响应对象
     *
     * @param stallId 档口主键
     */
    @Override
    public void deleteStallVO(Long stallId) {
        stallListVORepository.deleteById(stallId);
    }

    /**
     * 操作单个档口对象
     *
     * @param stallId 档口主键
     */
    @Override
    public void operationStall(Long stallId) {
        this.deleteStallVO(stallId);
        StallListVO stallListVO = stallMapper.appletStall(stallId);
        stallListVORepository.save(stallListVO);
    }

    /**
     * 批量操作档口信息
     */
    @Override
    public void operationStallList() {
        stallListVORepository.deleteAll();
        stallMapper.listAppletsStall().forEach(stallListVO -> stallListVORepository.save(stallListVO));
    }
}
