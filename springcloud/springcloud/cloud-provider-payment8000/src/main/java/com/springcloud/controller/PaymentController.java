package com.springcloud.controller;

import com.springcloud.pojo.CommonResult;
import com.springcloud.pojo.Payment;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @version 1.0
 * @Author Mypc
 * @Date 2020/7/20
 * @Time 18:57
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * @RequestBody一定要加，否则消费者调用不能封装数据
     * @param payment
     * @return
     */
    @PostMapping("/create")
    public CommonResult commonResult(@RequestBody Payment payment){
        log.info(payment.toString());
        int result = paymentService.create(payment);
        log.info("****插入结果********"+result);
        if (result > 0){
            return new CommonResult(200,"插入数据库成功,serport:"+serverPort,result);
        }else {
            return new CommonResult(500,"插入数据库失败,serport:"+serverPort);
        }
    }

    /**
     * id占位符，记得用{}包住
      * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("***查询结果****" + payment);

        if (null != payment){
            return new CommonResult(200,"查询成功(热启动成功~),serverport:"+serverPort,payment);
        }else {
            return new CommonResult(500,"没有对应记录,serverport:"+serverPort);
        }
    }
}
