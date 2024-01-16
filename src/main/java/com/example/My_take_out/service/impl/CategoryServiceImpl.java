package com.example.My_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.My_take_out.common.CustomException;
import com.example.My_take_out.mapper.CategoryMapper;
import com.example.My_take_out.pojo.Category;
import com.example.My_take_out.pojo.Dish;
import com.example.My_take_out.pojo.Setmeal;
import com.example.My_take_out.service.CategoryService;
import com.example.My_take_out.service.DishService;
import com.example.My_take_out.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: CategoryServiceImpl
 * @Date: 2023/12/8
 * @Time: 18:29
 * @Description:添加自定义描述
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    @Lazy//循环依赖问题
    private DishService dishService;
    @Autowired
    @Lazy//循环依赖问题
    private SetmealService setmealService;

    @Override
    public void remove(Long id) {
        //查询当前分类是否关联菜品
        LambdaQueryWrapper dishLambdaQueryWrapper = new LambdaQueryWrapper<Dish>()
                .eq(Dish::getCategoryId, id);
        Long count1 = dishService.count(dishLambdaQueryWrapper);

        if (count1 > 0) {
            throw new CustomException("当前分类关联了菜品，无法删除");
        }

        LambdaQueryWrapper setmealLambdaQueryWrapper = new LambdaQueryWrapper<Setmeal>()
                .eq(Setmeal::getCategoryId, id);
        Long count2 = setmealService.count(setmealLambdaQueryWrapper);

        if (count2 > 0) {
            throw new CustomException("当前分类关联了套餐，无法删除");
        }

        super.removeById(id);
    }

    @Override
    public Page page(int page, int pageSize) {
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<Category>()
                .orderByDesc(Category::getUpdateTime);

        return this.page(pageInfo, lambdaQueryWrapper);
    }

    @Override
    public List<Category> list(Category category) {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<Category>()
                .eq(category.getType() != null, Category::getType, category.getType())
                .orderByAsc(Category::getSort)
                .orderByDesc(Category::getUpdateTime);
        return this.list(lambdaQueryWrapper);
    }
}
