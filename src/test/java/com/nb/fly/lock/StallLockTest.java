package com.nb.fly.lock;

import com.nb.fly.mapper.StallLockMapper;
import com.nb.fly.repository.StallLockRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fly
 * @description 档口锁定测试
 * @date 2019/5/7 14:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StallLockTest {

    @Autowired
    private StallLockRepository stallLockRepository;

    @Autowired
    private StallLockMapper stallLockMapper;

    /**
     * 保存测试
     */
    @Test
    public void saveTest() {
        stallLockMapper.allStallLock().forEach(stallLock -> {
            stallLockRepository.save(stallLock);
        });
    }
}
