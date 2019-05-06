package com.nb.fly.repository;

import com.nb.fly.model.StallGrabOpen;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liying.fu
 * @description 档口释放仓库
 * @date 2019/5/6 下午11:53
 */
@Repository
public interface StallGrabOpenRepository extends ElasticsearchRepository<StallGrabOpen, Long> {
}
