package cnsr.fjh03.project.dto;

import cnsr.fjh03.project.pojo.Setmeal;
import cnsr.fjh03.project.pojo.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: SetmealDto
 * @Date: 2025/1/12
 * @Time: 13:14
 * @Description:添加自定义描述
 */
@Data
public class SetmealDto extends Setmeal {
    private List<SetmealDish> setmealDishes;
    private String categoryName;
}
