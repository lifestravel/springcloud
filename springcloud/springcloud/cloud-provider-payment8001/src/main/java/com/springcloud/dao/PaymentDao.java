package com.springcloud.dao;

import com.springcloud.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 *
 * @version 1.0
 * @Author Mypc
 * @Date 2020/7/20
 * @Time 17:53
 */
@Mapper
@Repository
public interface PaymentDao {
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
     Payment getPaymentById(@Param("id") Long id);
}
