package com.example.My_take_out.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.My_take_out.common.R;
import com.example.My_take_out.dto.DishDto;
import com.example.My_take_out.pojo.Category;
import com.example.My_take_out.pojo.Dish;
import com.example.My_take_out.service.CategoryService;
import com.example.My_take_out.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: DishController
 * @Date: 2023/12/9
 * @Time: 18:40
 * @Description:添加自定义描述
 */
@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page = {}, pageSize = {}", page, pageSize);

        Page pageInfo = new Page(page, pageSize);
        Page dishDtoPage = new Page();

        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<Dish>()
                .like(name != null, Dish::getName, name)
                .orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo, lambdaQueryWrapper);

        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");

        List<Dish> records = pageInfo.getRecords();
        List<DishDto> dishDtos = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);

            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById((categoryId));
            String categoryName = category.getName();
            dishDto.setCategoryName(categoryName);
            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(dishDtos);
        return R.success(dishDtoPage);
    }

    /**
     * 新增菜品
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        log.info("dishDto:{}", dishDto);
        dishService.saveWithFlavor(dishDto);
        return R.success("新增菜品成功！");
    }

    /**
     * 根据id查询菜品信息和对应的口味信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id) {
        log.info("id = {}", id);
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }

    /**
     * 更新菜品信息与对应的口味信息
     * @param dishDto
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto) {
        log.info("dishDto = {}", dishDto);
        dishService.updateWithFlavors(dishDto);
        return R.success("修改成功！");
    }

    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("ids = {}", ids);
        dishService.removeWithFlavors(ids);
        return R.success("1");
    }

    @GetMapping("/list")
    public R<List<Dish>> getDishById(Dish dish) {
        log.info("dish = {}", dish);
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<Dish>()
                .eq(Dish::getStatus, 1)
                .like(dish.getName() != null, Dish::getName, dish.getName())
                .eq(dish.getCategoryId() != null, Dish::getCategoryId , dish.getCategoryId())
                .orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        return R.success(dishService.list(lambdaQueryWrapper));
    }

    @PostMapping("/status/{statu}")
    public R<String> changestatu(@RequestParam List<Long> ids,@PathVariable int statu) {
        log.info("ids = {}", ids);
        LambdaUpdateWrapper lambdaUpdateWrapper = new LambdaUpdateWrapper<Dish>()
                .in(Dish::getId, ids)
                .set(Dish::getStatus, statu);
        dishService.update(lambdaUpdateWrapper);
        return R.success("成功停售对应的菜品！");
    }
}
