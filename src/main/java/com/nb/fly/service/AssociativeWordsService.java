package com.nb.fly.service;

import com.nb.fly.response.AssociativeWordsVO;

import java.util.List;

/**
 * @description: 联想词业务
 * @author: Zero
 * @date: 2020/5/28 下午4:28
 */
public interface AssociativeWordsService {

    /**
     * 联想词
     *
     * @param keyWord 联想词
     * @return 联想词
     */
    List<AssociativeWordsVO> associativeWordsService(String keyWord);
}
