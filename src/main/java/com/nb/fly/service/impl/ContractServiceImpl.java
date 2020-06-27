package com.nb.fly.service.impl;

import com.nb.fly.model.Contract;
import com.nb.fly.repository.ContractRepository;
import com.nb.fly.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Fu
 * @description 签约业务
 * @date 2019/5/7 13:45
 */
@Service
public class ContractServiceImpl implements ContractService {


    @Autowired
    private ContractRepository contractRepository;

    /**
     * 保存签约信息
     *
     * @param contract 签约信息
     */
    @Override
    public void save(Contract contract) {
        contractRepository.save(contract);
    }
}
