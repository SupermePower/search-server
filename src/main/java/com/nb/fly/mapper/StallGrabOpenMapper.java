package com.nb.fly.mapper;

import com.nb.fly.model.StallGrabOpen;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fly
 * @description 档口抢占信息
 * @date 2019/5/7 14:39
 */
@Repository
public interface StallGrabOpenMapper {

    /**
     * 获取档口抢占信息
     *
     * @return 档口抢占信息
     */
    List<StallGrabOpen> allStallGrabOpen();
}
