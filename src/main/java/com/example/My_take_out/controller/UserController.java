package com.example.My_take_out.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.My_take_out.common.R;
import com.example.My_take_out.pojo.User;
import com.example.My_take_out.service.UserService;
import com.example.My_take_out.utils.ValidateCodeUtils;
import jakarta.servlet.http.HttpSession;
import jogamp.nativewindow.windows.MARGINS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: UserController
 * @Date: 2023/12/20
 * @Time: 16:09
 * @Description:添加自定义描述
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user) {
        log.info("user = {}", user);

        if (userService.sendMsg(user)) {
            return R.success("短信发送成功！");
        }

        return R.error("短信发送失败！");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map) {
        log.info(map.toString());
        User user = userService.login(map);
        return R.success(user);
    }

}
