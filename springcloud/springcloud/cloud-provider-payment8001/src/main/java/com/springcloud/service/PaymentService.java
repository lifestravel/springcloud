package com.springcloud.service;

import com.springcloud.pojo.Payment;

/**
 * Created by IntelliJ IDEA.
 *
 * @version 1.0
 * @Author Mypc
 * @Date 2020/8/24
 * @Time 11:17
 */
public interface PaymentService {
    /**
     * 写
     * @param payment
     * @return
     */
    int create(Payment payment);

    /**
     * 根据id查
     * @param id
     * @return
     */
    Payment getPaymentById(Long id);
}
