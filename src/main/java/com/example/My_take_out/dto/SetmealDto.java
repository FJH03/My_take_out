package com.example.My_take_out.dto;

import com.example.My_take_out.pojo.Setmeal;
import com.example.My_take_out.pojo.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: SetmealDto
 * @Date: 2023/12/12
 * @Time: 8:54
 * @Description:添加自定义描述
 */
@Data
public class SetmealDto extends Setmeal {
    private List<SetmealDish> setmealDishes;
    private String categoryName;
}
