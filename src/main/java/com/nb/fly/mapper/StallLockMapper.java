package com.nb.fly.mapper;

import com.nb.fly.model.StallLock;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.Fu
 * @description 档口锁定
 * @date 2019/5/7 14:10
 */
@Repository
public interface StallLockMapper {

    List<StallLock> allStallLock();

    List<StallLock> findByUserId(String userId);
}
