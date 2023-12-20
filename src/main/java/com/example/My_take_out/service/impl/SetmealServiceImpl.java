package com.example.My_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.My_take_out.common.CustomException;
import com.example.My_take_out.common.MyfileMethods;
import com.example.My_take_out.pojo.Category;
import com.example.My_take_out.service.CategoryService;
import com.example.My_take_out.dto.SetmealDto;
import com.example.My_take_out.mapper.SetmealMapper;
import com.example.My_take_out.pojo.Setmeal;
import com.example.My_take_out.pojo.SetmealDish;
import com.example.My_take_out.service.SetmealDishService;
import com.example.My_take_out.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: SetmealServiceImpl
 * @Date: 2023/12/9
 * @Time: 18:08
 * @Description:添加自定义描述
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Value("${my_takeout.path}")
    private String Path;
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    @Lazy//循环依赖问题
    private CategoryService categoryService;

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     * @return
     */
    @Transactional
    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐基本信息
        this.save(setmealDto);

        //保存套餐和菜品的关联信息
        List<SetmealDish> list = setmealDto.getSetmealDishes();
        list.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(list);
    }

    /**
     * 根据id删除套餐以及对应的菜品信息(不删除菜品本身实体，仅仅删除在该套餐的记录)
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removewithDish(List<Long> ids) {
        MyfileMethods myfileMethods = new MyfileMethods();

        try {
            for (Long id : ids) {
                // 备份文件
                Setmeal setmeal = this.getById(id);
                String filename = setmeal.getImage();
                myfileMethods.backep(id, Path + filename);
            }

            // 执行数据库删除操作
            this.removeBatchByIds(ids);

            // 删除套餐对应的菜品信息
            LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<SetmealDish>()
                    .in(SetmealDish::getSetmealId, ids);
            setmealDishService.remove(lambdaQueryWrapper);

            // 正常操作完成后，删除备份的文件
            myfileMethods.deletebackep();
        } catch (Exception e) {
            // 发生异常，执行文件回滚操作
            myfileMethods.backepTofile();
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public SetmealDto getByIdWithDish(Long id) {
        Setmeal setmeal = this.getById(id);
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal, setmealDto);
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<SetmealDish>()
                .eq(SetmealDish::getSetmealId, setmeal.getId());

        List<SetmealDish> setmealDishList = setmealDishService.list(lambdaQueryWrapper);
        setmealDto.setSetmealDishes(setmealDishList);
        return setmealDto;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateWithDishes(SetmealDto setmealDto) {
        //更新套餐表基本信息
        this.updateById(setmealDto);
        //清理当前套餐对应的菜品信息
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<SetmealDish>()
                .eq(SetmealDish::getSetmealId, setmealDto.getId());
        setmealDishService.remove(lambdaQueryWrapper);
        //添加当前提交过来的菜品信息
        List<SetmealDish> setmealDishList = setmealDto.getSetmealDishes();
        setmealDishList = setmealDishList.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存菜品信息到setmeal_dish表
        setmealDishService.saveBatch(setmealDishList);
    }

    @Override
    public Page page(int page, int pageSize, String name) {
        Page pageInfo = new Page(page, pageSize);
        Page setmealDtopage = new Page();

        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<Setmeal>()
                .like(name != null, Setmeal::getName, name)
                .orderByDesc(Setmeal::getUpdateTime);
        this.page(pageInfo, lambdaQueryWrapper);

        BeanUtils.copyProperties(pageInfo, setmealDtopage, "records");

        List<Setmeal> setmealList = pageInfo.getRecords();
        List<SetmealDto> setmealDtos = setmealList.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);

            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            String categoryName = category.getName();
            setmealDto.setCategoryName(categoryName);
            return setmealDto;
        }).collect(Collectors.toList());
        setmealDtopage.setRecords(setmealDtos);
        return setmealDtopage;
    }

    @Override
    public void changestu(List<Long> ids, int statu) {
        LambdaUpdateWrapper lambdaUpdateWrapper = new LambdaUpdateWrapper<Setmeal>()
                .in(Setmeal::getId, ids)
                .set(Setmeal::getStatus, statu);
        this.update(lambdaUpdateWrapper);
    }
}
