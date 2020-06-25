package com.nb.fly.controller;

import com.nb.fly.response.ResponseVO;
import com.nb.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户Controller
 * @author: Zero
 * @date: 2020/5/30 下午10:54
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "{keyword}")
    public ResponseVO queryUser(@PathVariable String keyword) {
        return userService.queryUser(keyword);
    }

    @GetMapping(path = "/synonyms")
    public ResponseVO synonyms(String keyword) {
        return userService.synonyms(keyword);
    }
}
