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
 * @Time 18:53
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerMain9004 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain9004.class,args);
    }
}
