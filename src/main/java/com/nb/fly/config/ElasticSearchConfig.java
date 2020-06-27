package com.nb.fly.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * @description: ES配置
 * @author: Mr.Fu
 * @date: 2020/5/28 下午4:59
 */
@Configuration
@Slf4j
public class ElasticSearchConfig {

    @Bean
    public TransportClient transportClient() {
        log.info("Elasticsearch start init ...");
        TransportClient transportClient = null;
        try {
            // 配置信息
            Settings esSetting = Settings.builder()
                    // 集群名字
                    .put("cluster.name", "video-search")
                    // 增加线程池个数，暂时设为5
                    .put("thread_pool.search.size", 5)
                    .build();
            //配置信息Settings自定义
            transportClient = new PreBuiltTransportClient(esSetting);
            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("39.107.225.50"), 9300);
            transportClient.addTransportAddresses(transportAddress);
        } catch (Exception e) {
            log.error("Elasticsearch TransportClient create error!!!", e);
        }
        return transportClient;
    }
}
