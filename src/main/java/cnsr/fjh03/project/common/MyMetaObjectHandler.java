package cnsr.fjh03.project.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: MyMetaObjectHandler
 * @Date: 2023/12/8
 * @Time: 17:26
 * @Description:添加自定义描述
 */

/**
 * 自定义原数据对象处理器
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    HttpServletRequest req;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]...");
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());

        Long empId = (Long) req.getSession().getAttribute("employee");
        Long userId = (Long) req.getSession().getAttribute("user");

        if (empId == null) {
            metaObject.setValue("createUser", userId);
            metaObject.setValue("updateUser", userId);
        } else {
            metaObject.setValue("createUser", empId);
            metaObject.setValue("updateUser", empId);
        }
        log.info(metaObject.toString());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]...");

        metaObject.setValue("updateTime", LocalDateTime.now());

        Long empId = (Long) req.getSession().getAttribute("employee");
        Long userId = (Long) req.getSession().getAttribute("user");

        if (empId == null) {
            metaObject.setValue("updateUser", userId);
        } else {
            metaObject.setValue("updateUser", empId);
        }

        log.info(metaObject.toString());
    }
}
