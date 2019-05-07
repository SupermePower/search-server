package com.nb.fly.mapper;

import com.nb.fly.model.Contract;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fly
 * @description 签约信息
 * @date 2019/5/7 13:37
 */
@Repository
public interface ContractMapper {
    /**
     * 获取全部签约信息
     *
     * @return 签约信息
     */
    List<Contract> allContract();
}
