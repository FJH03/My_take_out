package cnsr.fjh03.project.service.impl;

import cnsr.fjh03.project.mapper.ShoppingCartMapper;
import cnsr.fjh03.project.pojo.ShoppingCart;
import cnsr.fjh03.project.service.ShoppingCartService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: ShoppingCartServiceImpl
 * @Date: 2025/2/14
 * @Time: 16:12
 * @Description:添加自定义描述
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Autowired
    HttpSession session;

    @Override
    public ShoppingCart add_sub(ShoppingCart shoppingCart, char c) {
        Long id = (Long) session.getAttribute("user");
        shoppingCart.setUserId(id);

        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, id);

        if (dishId != null) {
            //添加到购物车的是菜品
            queryWrapper.eq(ShoppingCart::getDishId, dishId);

        } else {
            //添加到购物车的是套餐
            queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }

        ShoppingCart cartServiceOne = this.getOne(queryWrapper);

        if (cartServiceOne != null) {
            //如果已经存在，就在原来数量基础上加一
            Integer number = cartServiceOne.getNumber();
            if (c == '+') {
                cartServiceOne.setNumber(number + 1);
            } else if (c == '-' && cartServiceOne.getNumber() > 1) {
                cartServiceOne.setNumber(number - 1);
            }

            this.updateById(cartServiceOne);
        } else {
            //如果不存在，则添加到购物车，数量默认就是一
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            this.save(shoppingCart);
            cartServiceOne = shoppingCart;
        }

        return cartServiceOne;
    }

    @Override
    public List<ShoppingCart> showlist() {
        Long id = (Long) session.getAttribute("user");

        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<ShoppingCart>()
                .eq(ShoppingCart::getUserId, id)
                .orderByAsc(ShoppingCart::getCreateTime);

        List<ShoppingCart> list = this.list(lambdaQueryWrapper);

        return list;
    }

    @Override
    public void clear() {
        Long id = (Long) session.getAttribute("user");

        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<ShoppingCart>()
                .eq(ShoppingCart::getUserId, id);

        this.remove(lambdaQueryWrapper);
    }

}
