package com.nb.fly.service;

import com.nb.fly.model.Contract;

/**
 * @author fly
 * @description 签约业务
 * @date 2019/5/7 10:22
 */
public interface ContractService {

    /**
     * 保存签约信息
     *
     * @param contract 签约信息
     */
    void save(Contract contract);
}
