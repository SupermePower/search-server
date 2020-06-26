package com.nb.fly.controller;

import com.nb.fly.request.SearchVideoRequest;
import com.nb.fly.response.ResponseVO;
import com.nb.fly.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @description:
 * @author: Zero
 * @date: 2020/6/25 下午8:34
 */
@Api(tags = "Video", description = "Video")
@RestController
@RequestMapping(path = "/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "TransportClient-search video")
    @GetMapping(path = "/video")
    private ResponseVO<Map<String, Object>> queryVideo(String scrollId, String keyword) {
        return videoService.queryVideo(scrollId, keyword);
    }

    @ApiOperation(value = "RestHighLevelClient-search video")
    @PostMapping
    public ResponseVO<Map<String, Object>> searchVideo(@RequestBody @Valid SearchVideoRequest request) throws Exception {
        return videoService.searchVideo(request);
    }
}
