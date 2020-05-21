package com.nb.fly.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description:
 * @author: Zero
 * @date: 2020/5/21 下午10:47
 */
@Setter
@Getter
@ToString
public class QueryHospitalRequest {
    private Double longitude;
    private Double latitude;
}
