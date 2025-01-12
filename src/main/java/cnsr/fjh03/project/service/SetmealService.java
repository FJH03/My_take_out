package cnsr.fjh03.project.service;

import cnsr.fjh03.project.dto.DishDto;
import cnsr.fjh03.project.dto.SetmealDto;
import cnsr.fjh03.project.pojo.Setmeal;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: SetmealService
 * @Date: 2025/1/12
 * @Time: 18:06
 * @Description:添加自定义描述
 */
public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);

    void removewithDish(List<Long> ids);

    SetmealDto getByIdWithDish(Long id);

    void updateWithDishes(SetmealDto setmealDto);

    Page page(int page, int pageSize, String name);

    void changestu(List<Long> ids, int statu);

     List<Setmeal> mylist(Setmeal setmeal);

    List<DishDto> getDishBySetmealId(Long id);
}
