package com.example.My_take_out.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.My_take_out.common.R;
import com.example.My_take_out.pojo.Orders;
import com.example.My_take_out.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: OrdersController
 * @Date: 2023/12/9
 * @Time: 18:58
 * @Description:添加自定义描述
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        log.info("page = {}, pageSize = {}", page, pageSize);
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<Orders>()
                .orderByDesc(Orders::getOrderTime);
        orderService.page(pageInfo, lambdaQueryWrapper);
        return R.success(pageInfo);
    }
}
