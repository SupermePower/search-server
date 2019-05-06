package com.nb.fly.repository;

import com.nb.fly.model.Contract;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liying.fu
 * @description 签约数据仓库
 * @date 2019/5/6 下午11:38
 */
@Repository
public interface ContractRepository extends ElasticsearchRepository<Contract, Long> {
}
