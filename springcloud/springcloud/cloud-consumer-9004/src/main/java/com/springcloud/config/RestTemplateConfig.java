package com.springcloud.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 *
 * @version 1.0
 * @Author Mypc
 * @Date 2020/10/30
 * @Time 18:48
 */
@SpringBootConfiguration
public class RestTemplateConfig {

    @Bean
    //负载均衡器，继续加上这个
    @LoadBalanced
    public RestTemplate getTemplate(){
        return  new RestTemplate();
    }
}
