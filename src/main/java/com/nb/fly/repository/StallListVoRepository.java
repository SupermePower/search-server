package com.nb.fly.repository;

import com.nb.fly.response.StallListVO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @description: 档口集合
 * @author: Mr.Fu
 * @date: 2019/5/7 下午11:15
 */
@Repository
public interface StallListVoRepository extends ElasticsearchRepository<StallListVO, Long> {
}
