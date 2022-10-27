package com.lcz.base;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;


@Configuration
public class ElasticSearchConfig {
    @Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.cluster.name}")
    private String esName;

    @Bean
    public RestHighLevelClient esClient() throws UnknownHostException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(esHost, esPort, "http")));
        Settings settings = Settings.builder()
                .put("cluster.name", this.esName)
                .put("client.transport.sniff", true)
                .build();
        return client;
    }
}
