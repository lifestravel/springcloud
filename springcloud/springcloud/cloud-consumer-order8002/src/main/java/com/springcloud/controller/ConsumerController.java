package com.springcloud.controller;

import com.springcloud.pojo.CommonResult;
import com.springcloud.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 *
 * @version 1.0
 * @Author Mypc
 * @Date 2020/9/16
 * @Time 16:46
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    public static final String PAYMENT_URL="http://localhost:8001";

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info(payment.toString());
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create",payment,CommonResult.class);
    }


    @GetMapping("/payment/get/{id}/")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/"+id,CommonResult.class);
    }
}
