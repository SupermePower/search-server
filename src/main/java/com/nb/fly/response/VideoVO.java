package com.nb.fly.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: video view object
 * @author: Mr.Fu
 * @date: 2020/6/25 下午8:35
 */
@Setter
@Getter
@ToString
public class VideoVO {
    private Long videoId;
    private String videoName;
}
