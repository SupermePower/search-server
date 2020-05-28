package com.nb.fly.controller;

import com.nb.fly.response.AssociativeWordsVO;
import com.nb.fly.response.ResponseVO;
import com.nb.fly.service.AssociativeWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 联想词
 * @author: Zero
 * @date: 2020/5/28 下午4:05
 */
@RestController
@RequestMapping("/associative-words")
public class AssociativeWordsController {

    @Autowired
    private AssociativeWordsService associativeWordsService;

    @GetMapping(path = "/{keyWord}")
    public ResponseVO<List<AssociativeWordsVO>> queryAssociativeWords(@PathVariable String keyWord) {
        List<AssociativeWordsVO> associativeWordsList = associativeWordsService.associativeWordsService(keyWord);
        return new ResponseVO<List<AssociativeWordsVO>>().success(associativeWordsList);
    }
}
