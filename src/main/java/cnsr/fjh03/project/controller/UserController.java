package cnsr.fjh03.project.controller;

import cnsr.fjh03.project.common.R;
import cnsr.fjh03.project.pojo.User;
import cnsr.fjh03.project.service.UserService;
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
 * @Date: 2024/12/25
 * @Time: 16:12
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

    @PostMapping("/loginout")
    public R<String> loginout() {
        userService.loginout();
        return R.success("登出成功！");
    }

}
