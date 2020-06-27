package com.nb.fly.mapper;

import com.nb.fly.model.Stall;
import com.nb.fly.response.StallListVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.Fu
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

    /**
     * 获取小程序档口列表
     *
     * @return 小程序档口列表
     */
    List<StallListVO> listAppletsStall();

    /**
     * 获取档口信息
     *
     * @param stallId 档口主键
     * @return 档口信息
     */
    StallListVO appletStall(Long stallId);

    /**
     * 获取档口图片信息
     *
     * @param stallId 档口主键
     * @return 档口图片信息
     */
    List<String> querySceneGraph(Long stallId);
}
