package com.nb.fly.contract;

import com.nb.fly.mapper.ContractMapper;
import com.nb.fly.service.ContractService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author fly
 * @description 签约测试
 * @date 2019/5/7 13:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractTest {

    @Autowired
    private ContractService contractService;

    @Autowired
    private ContractMapper contractMapper;

    /**
     * 保存签约，从mysql中获取
     */
    @Test
    public void saveTest() {
        contractMapper.allContract().forEach(contract -> {
            contractService.save(contract);
        });
    }
}
