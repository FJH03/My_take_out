package com.example.My_take_out.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.My_take_out.dto.DishDto;
import com.example.My_take_out.pojo.Dish;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: DishService
 * @Date: 2023/12/9
 * @Time: 18:05
 * @Description:添加自定义描述
 */
public interface DishService extends IService<Dish> {
    Page page(int page, int pageSize, String name);
    List<Dish> getDishById(Dish dish);
    void saveWithFlavor(DishDto dishDto);
    DishDto getByIdWithFlavor(Long id);
    void updateWithFlavors(DishDto dishDto);
    void removeWithFlavors(List<Long> id);
    void changestatu(List<Long> ids, int statu);
}
