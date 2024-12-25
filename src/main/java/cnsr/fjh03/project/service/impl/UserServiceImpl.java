package cnsr.fjh03.project.service.impl;

import cnsr.fjh03.project.mapper.UserMapper;
import cnsr.fjh03.project.pojo.User;
import cnsr.fjh03.project.service.UserService;
import cnsr.fjh03.project.utils.ValidateCodeUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: UserServiceImpl
 * @Date: 2024/12/25
 * @Time: 16:05
 * @Description:添加自定义描述
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    HttpSession session;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Override
    public boolean sendMsg(User user) {
        String phone = user.getPhone();

        if (StringUtils.isNotEmpty(phone)) {
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code = {}", code);

            //调用短信服务API(略)

            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return true;
        }

        return false;
    }

    @Override
    public User login(Map map) {
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();

        String matchcode = redisTemplate.opsForValue().get(phone);

        if (matchcode != null && matchcode.equals(code)) {
            LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                    .eq(User::getPhone, phone);
            User user = this.getOne(lambdaQueryWrapper);
            if (user == null) {
                user = new User();
                user.setPhone(phone);
                this.save(user);
            }

            session.setAttribute("user", user.getId());
            redisTemplate.delete(phone);
            return user;
        }

        return null;
    }

    @Override
    public void loginout() {
        session.removeAttribute("user");
    }
}
