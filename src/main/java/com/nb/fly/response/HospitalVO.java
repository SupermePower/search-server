package com.nb.fly.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description:
 * @author: Zero
 * @date: 2020/5/22 上午12:45
 */
@Setter
@Getter
@ToString
public class HospitalVO {
    private Long id;
    private String name;
    private String img;
    private String address;
    private Double distance;
}
