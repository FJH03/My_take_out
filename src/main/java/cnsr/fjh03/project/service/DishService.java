package cnsr.fjh03.project.service;

import cnsr.fjh03.project.dto.DishDto;
import cnsr.fjh03.project.pojo.Dish;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: DishService
 * @Date: 2025/1/12
 * @Time: 13:12
 * @Description:添加自定义描述
 */
public interface DishService extends IService<Dish> {
    Page page(int page, int pageSize, String name);
    List<DishDto> getDishlist(Dish dish);
    void saveWithFlavor(DishDto dishDto);
    DishDto getByIdWithFlavor(Long id);
    void updateWithFlavors(DishDto dishDto);
    void removeWithFlavors(List<Long> id);
    void changestatu(List<Long> ids, int statu);
}

