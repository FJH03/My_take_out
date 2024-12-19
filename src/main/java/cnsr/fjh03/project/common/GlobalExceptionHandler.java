package cnsr.fjh03.project.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: GlobalExceptionHandler
 * @Date: 2023/12/7
 * @Time: 20:44
 * @Description:添加自定义描述
 */

@RestControllerAdvice(annotations = {RestController.class})
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 异常处理方法
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R<String> ex(Exception ex) {
        ex.printStackTrace();
        return R.error(ex.getMessage());
    }
}
