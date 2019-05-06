package com.nb.fly.repository;

import com.nb.fly.model.Stall;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fly
 * @description 档口数据仓库
 * @date 2019/5/6 18:14
 */
@Repository
public interface StallRepository extends ElasticsearchRepository<Stall, Long> {

    /**
     * 获取档口集合
     *
     * @param stallName 档口名称
     * @return 档口集合
     */
    List<Stall> findByStallsName(String stallName);
}
