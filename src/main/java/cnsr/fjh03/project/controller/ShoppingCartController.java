package cnsr.fjh03.project.controller;

import cnsr.fjh03.project.common.R;
import cnsr.fjh03.project.pojo.ShoppingCart;
import cnsr.fjh03.project.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: ShoppingCartController
 * @Date: 2025/2/14
 * @Time: 16:15
 * @Description:添加自定义描述
 */
@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {
        log.info("shoppingCart = {}", shoppingCart);
        return R.success(shoppingCartService.add_sub(shoppingCart, '+'));
    }

    @PostMapping("/sub")
    public R<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart) {
        log.info("shoppingCart = {}", shoppingCart);
        return R.success(shoppingCartService.add_sub(shoppingCart, '-'));
    }

    /**
     * 查看购物车
     * @return
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> list() {
        log.info("查看购物车...");
        return R.success(shoppingCartService.showlist());
    }

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    public R<String> clean() {
        shoppingCartService.clear();
        return R.success("成功清空购物车！");
    }
}
