package com.nb.fly.mapper;

import com.nb.fly.response.MerchantListVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author Mr.Fu
 * @description 商户信息
 * @date 2019/5/8 11:23
 */
@Repository
public interface MerchantMapper {

    /**
     * 根据商户id获取商户信息
     *
     * @param merchantIdSet 商户id
     * @return 列表信息
     */
    List<MerchantListVO> listMerchantByMerchantId(@Param("merchantIdSet") Set<Long> merchantIdSet);

    /**
     * 根据商户id获取商户信息
     *
     * @param merchantId 商户id
     * @return 列表信息
     */
    MerchantListVO merchantById(@Param("merchantId") Long merchantId);

    /**
     * 获取商户信息
     *
     * @return 商户信息
     */
    List<MerchantListVO> merchantList();
}
