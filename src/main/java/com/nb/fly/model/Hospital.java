package com.nb.fly.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

/**
 * @description:
 * @author: Mr.Fu
 * @date: 2020/5/21 下午1:37
 */
@Setter
@Getter
@ToString
@Document(indexName = "hospital", type = "hospital")
public class Hospital {
    @Id
    private Long id;
    private String name;
    private String img;
    private String address;
    @GeoPointField
    private Position position;
}
