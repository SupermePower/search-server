package com.nb.fly.repository;

import com.nb.fly.model.StallLock;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mr.Fu
 * @description 档口锁定数据仓库
 * @date 2019/5/6 下午11:51
 */
@Repository
public interface StallLockRepository extends ElasticsearchRepository<StallLock, Long> {
}
