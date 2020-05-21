package com.nb.fly.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description:
 * @author: Zero
 * @date: 2020/5/21 下午10:07
 */
@Setter
@Getter
@ToString
public class SaveHospitalRequest {
    private String name;
    private String address;
    private Double longitude;
    private Double latitude;
}
