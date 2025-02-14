package cnsr.fjh03.project.service;

import cnsr.fjh03.project.pojo.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: ShoppingCartService
 * @Date: 2025/2/14
 * @Time: 16:10
 * @Description:添加自定义描述
 */
public interface ShoppingCartService extends IService<ShoppingCart> {
    ShoppingCart add_sub(ShoppingCart shoppingCart, char c);

    List<ShoppingCart> showlist();

    void clear();
}
