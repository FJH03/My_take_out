package cnsr.fjh03.project.service;

import cnsr.fjh03.project.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: UserService
 * @Date: 2024/12/25
 * @Time: 16:04
 * @Description:添加自定义描述
 */
public interface UserService extends IService<User> {
    boolean sendMsg(User user);

    User login(Map map);

    void loginout();
}
