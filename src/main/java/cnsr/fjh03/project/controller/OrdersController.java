package cnsr.fjh03.project.controller;

import cnsr.fjh03.project.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cnsr.fjh03.project.common.R;
import cnsr.fjh03.project.pojo.Orders;
import cnsr.fjh03.project.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: OrdersController
 * @Date: 2025/2/14
 * @Time: 15:58
 * @Description:添加自定义描述
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    /**
     * 后端管理查询查询订单
     */
    @GetMapping("/page")
    public R<Page<Orders>> page(int page, int pageSize, String number,
                                @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss") Date beginTime,
                                @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss") Date endTime) {
        log.info("beginTime={},endTime={}",beginTime,endTime);
        // 构建订单分页对象
        Page<Orders> pageInfo = new Page<>(page, pageSize);
        // 构造条件
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper();
        // 模糊查询根据订单号或者时间间隔查询
        // SELECT * FROM Order where number like ? or order_time between ? and ?
        queryWrapper.like(StringUtils.isNotEmpty(number), Orders::getNumber, number);
        if (beginTime != null){
            queryWrapper.between(Orders::getOrderTime,beginTime,endTime);
        }
        // 时间降序
        queryWrapper.orderByDesc(Orders::getOrderTime);
        // 封装为分页对象
        orderService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {
        log.info("order = {}", orders);
        orderService.submit(orders);
        return R.success("提交成功！");
    }

    /**
     * 订单状态修改
     * @param orders
     * @return
     */
    @PutMapping()
    public R<String> orderStatus(@RequestBody Orders orders){
        // 修改状态
        orderService.updateById(orders);
        return R.success("修改成功");
    }

    /**
     * 用户端展示自己的订单分页查询
     * @param page
     * @param pageSize
     * @return
     * 遇到的坑：原来分页对象中的records集合存储的对象是分页泛型中的对象，里面有分页泛型对象的数据
     * 开始的时候我以为前端只传过来了分页数据，其他所有的数据都要从本地线程存储的用户id开始查询，
     * 结果就出现了一个用户id查询到 n个订单对象，然后又使用 n个订单对象又去查询 m 个订单明细对象，
     * 结果就出现了评论区老哥出现的bug(嵌套显示数据....)
     * 正确方法:直接从分页对象中获取订单id就行，问题大大简化了......
     */
    @GetMapping("/userPage")
    public R<Page> page(int page, int pageSize) {
        return R.success(userService.page(page, pageSize));
    }
}
