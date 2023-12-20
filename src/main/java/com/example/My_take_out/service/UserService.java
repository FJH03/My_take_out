package com.example.My_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.My_take_out.pojo.User;

import java.util.Map;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: UserService
 * @Date: 2023/12/20
 * @Time: 16:06
 * @Description:添加自定义描述
 */
public interface UserService extends IService<User> {
    boolean sendMsg(User user);

    User login(Map map);
}
