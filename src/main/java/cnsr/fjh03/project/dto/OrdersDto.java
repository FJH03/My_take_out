package cnsr.fjh03.project.dto;

import cnsr.fjh03.project.pojo.OrderDetail;
import cnsr.fjh03.project.pojo.Orders;
import lombok.Data;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: OrdersDto
 * @Date: 2025/5/22
 * @Time: 11:24
 * @Description:添加自定义描述
 */
@Data
public class OrdersDto extends Orders {

    // 用户名
    private String userName;

    // 手机号
    private String phone;

    // 地址
    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;

}
