package com.nb.fly.stall;

import com.nb.fly.model.Stall;
import com.nb.fly.request.QueryStallRequest;
import com.nb.fly.service.StallService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author fly
 * @description 档口相关测试
 * @date 2019/5/6 18:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StallTest {

    @Autowired
    private StallService stallService;

    /**
     * 保存档口测试
     */
    @Test
    public void saveTest() {
        Stall stall = new Stall();
        stall.setStallsId(19564445586734734L);
        stall.setStallsName("B5");
        stall.setProjectId(867769668632474L);
        stall.setSigningTime(new Date());
        stallService.save(stall);
    }

    @Test
    public void stallListTest() {
        QueryStallRequest request = new QueryStallRequest();
        request.setStallName("B5");
        List<Stall> stalls = stallService.stallList(request);
        System.out.println(stalls);
    }

    @Test
    public void updateTest() {
        Stall stall = new Stall();
        stall.setStallsId(19564445586734734L);
        stall.setStallsName("B515");
        stallService.update(stall);
    }
}
