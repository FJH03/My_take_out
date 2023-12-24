package com.example.My_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.My_take_out.pojo.ShoppingCart;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: ShoppingCartService
 * @Date: 2023/12/23
 * @Time: 16:10
 * @Description:添加自定义描述
 */
public interface ShoppingCartService extends IService<ShoppingCart> {
    ShoppingCart add_sub(ShoppingCart shoppingCart, char c);

    List<ShoppingCart> showlist();

    void clear();
}
