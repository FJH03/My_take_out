package cnsr.fjh03.project.aop;

import cnsr.fjh03.project.pojo.OperateLog;
import cnsr.fjh03.project.service.OperateLogService;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: LogAspect
 * @Date: 2023/12/18
 * @Time: 10:30
 * @Description:添加自定义描述
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    OperateLogService operateLogService;

    @Around("@annotation(cnsr.fjh03.project.anno.Log)")
    public Object reccordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Long employee = (Long) httpServletRequest.getSession().getAttribute("employee");
        Long user = (Long) httpServletRequest.getSession().getAttribute("user");

        String className = joinPoint.getTarget().getClass().getName();

        String methonName = joinPoint.getSignature().getName();

        Object args[] = joinPoint.getArgs();
        String methodParms = Arrays.toString(args);

        Long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        String returnValue = JSON.toJSONString(result);
        Long end = System.currentTimeMillis();

        Long costTime = end - begin;

        LocalDateTime now = LocalDateTime.now();

        OperateLog operateLog = new OperateLog( null,  null , now, className, methonName, methodParms, returnValue, costTime);

        if (employee != null) {
            operateLog.setOperateUser(employee);
        } else if (user != null) {
            operateLog.setOperateUser(user);
        }
        operateLogService.save(operateLog);
        return result;
    }
}
