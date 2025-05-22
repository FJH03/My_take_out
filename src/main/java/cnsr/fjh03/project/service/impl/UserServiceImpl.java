package cnsr.fjh03.project.service.impl;

import cnsr.fjh03.project.dto.OrdersDto;
import cnsr.fjh03.project.mapper.UserMapper;
import cnsr.fjh03.project.pojo.OrderDetail;
import cnsr.fjh03.project.pojo.Orders;
import cnsr.fjh03.project.pojo.User;
import cnsr.fjh03.project.service.OrderDetailService;
import cnsr.fjh03.project.service.OrderService;
import cnsr.fjh03.project.service.UserService;
import cnsr.fjh03.project.utils.ValidateCodeUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: UserServiceImpl
 * @Date: 2024/12/25
 * @Time: 16:05
 * @Description:添加自定义描述
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    HttpSession session;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    @Lazy
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;

    @Override
    public boolean sendMsg(User user) {
        String phone = user.getPhone();

        if (StringUtils.isNotEmpty(phone)) {
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code = {}", code);

            //调用短信服务API(略)

            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return true;
        }

        return false;
    }

    @Override
    public User login(Map map) {
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();

        String matchcode = redisTemplate.opsForValue().get(phone);

        if (matchcode != null && matchcode.equals(code)) {
            LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                    .eq(User::getPhone, phone);
            User user = this.getOne(lambdaQueryWrapper);
            if (user == null) {
                user = new User();
                user.setPhone(phone);
                this.save(user);
            }

            session.setAttribute("user", user.getId());
            redisTemplate.delete(phone);
            return user;
        }

        return null;
    }

    @Override
    public void loginout() {
        session.removeAttribute("user");
    }

    @Override
    public Page page(int page, int pageSize) {
        Long id = (Long) session.getAttribute("user");
        //分页构造器对象
        Page<Orders> pageInfo = new Page<>(page,pageSize);
        Page<OrdersDto> pageDto = new Page<>(page,pageSize);
        //构造条件查询对象
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUserId, id);
        //这里是直接把当前用户分页的全部结果查询出来，要添加用户id作为查询条件，否则会出现用户可以查询到其他用户的订单情况
        //添加排序条件，根据更新时间降序排列
        queryWrapper.orderByDesc(Orders::getOrderTime);
        orderService.page(pageInfo,queryWrapper);

        //对OrderDto进行需要的属性赋值
        List<Orders> records = pageInfo.getRecords();
        List<OrdersDto> orderDtoList = records.stream().map((item) ->{
            OrdersDto orderDto = new OrdersDto();
            //此时的orderDto对象里面orderDetails属性还是空 下面准备为它赋值
            Long orderId = item.getId();//获取订单id
            List<OrderDetail> orderDetailList = this.getOrderDetailListByOrderId(orderId);
            BeanUtils.copyProperties(item,orderDto);
            //对orderDto进行OrderDetails属性的赋值
            orderDto.setOrderDetails(orderDetailList);
            return orderDto;
        }).collect(Collectors.toList());
        //使用dto的分页有点难度.....需要重点掌握
        BeanUtils.copyProperties(pageInfo,pageDto,"records");
        pageDto.setRecords(orderDtoList);
        return pageDto;
    }

    //抽离的一个方法，通过订单id查询订单明细，得到一个订单明细的集合
    //这里抽离出来是为了避免在stream中遍历的时候直接使用构造条件来查询导致eq叠加，从而导致后面查询的数据都是null
    public List<OrderDetail> getOrderDetailListByOrderId(Long orderId){
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOrderId, orderId);
        List<OrderDetail> orderDetailList = orderDetailService.list(queryWrapper);
        return orderDetailList;
    }
}
