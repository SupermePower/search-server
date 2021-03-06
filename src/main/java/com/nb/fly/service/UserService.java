package com.nb.fly.service;

import com.nb.fly.response.ResponseVO;

/**
 * @description: 用户业务
 * @author: Mr.Fu
 * @date: 2020/5/30 下午10:56
 */
public interface UserService {

    /**
     * 获取用户信息
     *
     * @param keyword 关键词
     * @return 用户信息
     */
    ResponseVO queryUser(String keyword);

    /**
     * 同义词
     *
     * @param keyword keyword
     * @return 搜索内容
     */
    ResponseVO synonyms(String keyword);
}
