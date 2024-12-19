package cnsr.fjh03.project.common;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: CustomException
 * @Date: 2023/12/9
 * @Time: 18:27
 * @Description:添加自定义描述
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
