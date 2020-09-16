package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by IntelliJ IDEA.
 *
 * @version 1.0
 * @Author Mypc
 * @Date 2020/9/16
 * @Time 16:05
 */
@SpringBootApplication
@EnableEurekaClient
public class ConsumerMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain8002.class,args);
    }
}
