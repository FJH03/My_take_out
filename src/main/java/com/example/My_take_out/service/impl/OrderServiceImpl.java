package com.example.My_take_out.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.My_take_out.mapper.OrderMapper;
import com.example.My_take_out.pojo.Orders;
import com.example.My_take_out.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: OrderServiceImpl
 * @Date: 2023/12/9
 * @Time: 18:57
 * @Description:添加自定义描述
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
}
