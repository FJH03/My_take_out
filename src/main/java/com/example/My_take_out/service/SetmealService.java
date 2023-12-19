package com.example.My_take_out.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.My_take_out.common.R;
import dto.SetmealDto;
import com.example.My_take_out.pojo.Setmeal;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: SetmealService
 * @Date: 2023/12/9
 * @Time: 18:06
 * @Description:添加自定义描述
 */
public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);

    void removewithDish(List<Long> ids);

    SetmealDto getByIdWithDish(Long id);

    void updateWithDishes(SetmealDto setmealDto);

    Page page(int page, int pageSize, String name);
}
