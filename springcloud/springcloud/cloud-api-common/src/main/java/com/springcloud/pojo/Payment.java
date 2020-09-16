package com.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 *
 * @version 1.0
 * @Author Mypc
 * @Date 2020/7/20
 * @Time 17:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
