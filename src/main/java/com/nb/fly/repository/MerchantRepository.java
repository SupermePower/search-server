package com.nb.fly.repository;

import com.nb.fly.response.MerchantListVO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fly
 * @description 商户数据仓库
 * @date 2019/5/8 11:28
 */
@Repository
public interface MerchantRepository extends ElasticsearchRepository<MerchantListVO, Long> {
}
