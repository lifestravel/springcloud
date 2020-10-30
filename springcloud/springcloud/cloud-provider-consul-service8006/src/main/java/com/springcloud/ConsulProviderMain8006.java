package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by IntelliJ IDEA.
 *
 * @version 1.0
 * @Author Mypc
 * @Date 2020/10/30
 * @Time 21:15
 */
@SpringBootApplication
//服务发现
@EnableDiscoveryClient
public class ConsulProviderMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulProviderMain8006.class,args);
    }
}
