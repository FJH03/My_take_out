package com.example.My_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.My_take_out.common.CustomException;
import com.example.My_take_out.common.MyfileMethods;
import com.example.My_take_out.dto.DishDto;
import com.example.My_take_out.mapper.DishMapper;
import com.example.My_take_out.pojo.Dish;
import com.example.My_take_out.pojo.DishFlavor;
import com.example.My_take_out.pojo.SetmealDish;
import com.example.My_take_out.service.DishFlavorService;
import com.example.My_take_out.service.DishService;
import com.example.My_take_out.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: DishServiceImpl
 * @Date: 2023/12/9
 * @Time: 18:07
 * @Description:添加自定义描述
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Value("${my_takeout.path}")
    private String Path;
    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 新增菜品，同时保存对应的口味数据(操作两张表)
     * @param dishDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到菜品表
        this.save(dishDto);

        Long dishId = dishDto.getId();
        List<DishFlavor> flavorList = dishDto.getFlavors();

        flavorList = flavorList.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        //保存菜品的口味信息到口味表
        dishFlavorService.saveBatch(flavorList);
    }

    /**
     * 根据id查询菜品信息和对应的口味信息
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //查询菜品基本信息
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<DishFlavor>()
                .eq(DishFlavor::getDishId, dish.getId());

        List<DishFlavor> flavors = dishFlavorService.list(lambdaQueryWrapper);
        dishDto.setFlavors(flavors);
        return dishDto;
    }

    /**
     * 更新菜品信息与对应的口味信息
     * @param dishDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateWithFlavors(DishDto dishDto) {
        //更新dish表基本信息
        this.updateById(dishDto);
        //清理当前菜品对应的口味数据
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<DishFlavor>()
                .eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(lambdaQueryWrapper);
        //添加当前提交过来的口味数据
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存菜品的口味信息到口味表
        dishFlavorService.saveBatch(flavors);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeWithFlavors(List<Long> ids) {

        for (Long id : ids) {
            LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<SetmealDish>()
                    .eq(SetmealDish::getDishId, id);
            long count = setmealDishService.count(lambdaQueryWrapper);

            LambdaQueryWrapper lambdaQueryWrapper1 = new LambdaQueryWrapper<Dish>()
                    .eq(Dish::getId, id);
            Dish dish = this.getOne(lambdaQueryWrapper1);

            if (count > 0 || dish.getStatus() == 1) {
                throw new CustomException("您所选的菜品其中有的关联了套餐或其处于在售状态，请重新勾选!");
            }
        }

        MyfileMethods myfileMethods = new MyfileMethods();

        try {
            for (Long id : ids) {
                // 备份文件
                Dish dish = this.getById(id);
                String filename = dish.getImage();
                myfileMethods.backep(id, Path + filename);
            }

            // 执行数据库删除操作
            this.removeBatchByIds(ids);

            // 删除菜品对应的口味信息
            if (CollectionUtils.isNotEmpty(ids)) {
                LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<DishFlavor>()
                        .in(DishFlavor::getDishId, ids);
                dishFlavorService.remove(lambdaQueryWrapper);
            }

            // 正常操作完成后，删除备份的文件
            myfileMethods.deletebackep();
        } catch (Exception e) {
            // 发生异常，执行文件回滚操作
            myfileMethods.backepTofile();
            throw new CustomException(e.getMessage());
        }
    }
}
