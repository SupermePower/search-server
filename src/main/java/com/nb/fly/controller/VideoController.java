package com.nb.fly.controller;

import com.nb.fly.request.SearchVideoRequest;
import com.nb.fly.response.ResponseVO;
import com.nb.fly.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description:
 * @author: Zero
 * @date: 2020/6/25 下午8:34
 */
@RestController
@RequestMapping(path = "/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping(path = "/video")
    private ResponseVO<Map<String, Object>> queryVideo(String scrollId, String keyword) {
        return videoService.queryVideo(scrollId, keyword);
    }

    @GetMapping
    public ResponseVO<Map<String, Object>> searchVideo(SearchVideoRequest request) throws Exception {
        return videoService.searchVideo(request);
    }
}
