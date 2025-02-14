package cnsr.fjh03.project.service;

import cnsr.fjh03.project.pojo.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: OrderService
 * @Date: 2025/2/14
 * @Time: 18:56
 * @Description:添加自定义描述
 */
public interface OrderService extends IService<Orders> {
    void submit(Orders orders);
}
