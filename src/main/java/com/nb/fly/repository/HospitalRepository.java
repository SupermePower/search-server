package com.nb.fly.repository;

import com.nb.fly.model.Hospital;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: Zero
 * @date: 2020/5/21 下午10:09
 */
@Repository
public interface HospitalRepository extends ElasticsearchRepository<Hospital, Long> {
}
