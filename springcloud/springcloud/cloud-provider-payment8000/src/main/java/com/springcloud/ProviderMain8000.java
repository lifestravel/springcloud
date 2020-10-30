package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by IntelliJ IDEA.
 *
 * @version 1.0
 * @Author Mypc
 * @Date 2020/10/29
 * @Time 15:50
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderMain8000 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderMain8000.class, args);
    }
}
