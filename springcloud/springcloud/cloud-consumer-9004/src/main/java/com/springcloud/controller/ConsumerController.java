package com.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @version 1.0
 * @Author Mypc
 * @Date 2020/10/30
 * @Time 18:56
 */
@RestController
@Slf4j
public class ConsumerController {

    /**
     * 自带的RPC
     */
    @Resource
    RestTemplate restTemplate;

    /**
     * 用微服务名称代替url
     */
    public static final String URL = "http://cloud-provider-service";

    @RequestMapping("/consumer/payment/zk")
    public String paymentInfo(){

        String result = restTemplate.getForObject(URL+"/payment/zook",String.class);
        return result;
    }

}
