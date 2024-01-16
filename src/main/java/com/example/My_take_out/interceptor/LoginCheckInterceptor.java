package com.example.My_take_out.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.My_take_out.common.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: LoginCheckInterceptor
 * @Date: 2023/12/5
 * @Time: 17:23
 * @Description:添加自定义描述
 */
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws IOException {
        String url = req.getRequestURI();
        log.info("请求的URL:{}", url);

        Object employee = req.getSession().getAttribute("employee");
        Object user = req.getSession().getAttribute("user");

        if (employee == null && user == null) {
            log.info("请求头为空，返回未登录");
            R<String> error = R.error("NOTLOGIN");
            String notlogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notlogin);
            return false;
        } else {
            if (employee != null) {
                log.info("请求员工为:{}", employee);
            }

            if (user != null) {
                log.info("请求用户为:{}", user);
            }

            return true;
        }
    }
}
