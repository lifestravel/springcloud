package com.springcloud.controller.PaymentController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @version 1.0
 * @Author Mypc
 * @Date 2020/10/30
 * @Time 19:10
 */
@RestController
@Slf4j
public class PaymentController {


    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/zook")
    public String paymentZook(){

        return "springcloud with zookeeper :" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
