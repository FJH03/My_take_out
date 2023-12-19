package dto;

import com.example.My_take_out.pojo.Dish;
import com.example.My_take_out.pojo.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: DishDto
 * @Date: 2023/12/11
 * @Time: 13:02
 * @Description:添加自定义描述
 */
@Data
public class DishDto extends Dish {
    private List<DishFlavor> flavors = new ArrayList<>();
    private String categoryName;
    private int copies;
}
