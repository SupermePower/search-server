package com.nb.fly.mapper;

import com.nb.fly.model.Stall;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fly
 * @description 档口数据库映射
 * @date 2019/5/7 11:21
 */
@Repository
public interface StallMapper {

    /**
     * 获取全部档口信息
     *
     * @return 档口信息
     */
    List<Stall> allStalls();
}
