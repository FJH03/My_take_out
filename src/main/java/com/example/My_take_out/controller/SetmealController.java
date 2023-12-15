package com.example.My_take_out.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.My_take_out.common.R;
import com.example.My_take_out.dto.SetmealDto;
import com.example.My_take_out.pojo.Category;
import com.example.My_take_out.pojo.Setmeal;
import com.example.My_take_out.service.CategoryService;
import com.example.My_take_out.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: SetmealController
 * @Date: 2023/12/9
 * @Time: 18:46
 * @Description:添加自定义描述
 */
@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page = {}, pageSize = {}", page, pageSize);
        Page pageInfo = new Page(page, pageSize);
        Page setmealDtopage = new Page();

        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<Setmeal>()
                .like(name != null, Setmeal::getName, name)
                .orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo, lambdaQueryWrapper);

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
        return R.success(setmealDtopage);
    }

    /**
     * 新增套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        log.info("setmealDto = {}", setmealDto);
        setmealService.saveWithDish(setmealDto);
        return R.success("添加成功！");
    }

    /**
     * 根据id删除套餐以及对应的菜品信息(不删除菜品本身实体，仅仅删除在该套餐的记录)
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("ids = {}", ids);
        setmealService.removewithDish(ids);
        return R.success("删除成功！");
    }

    /**
     * 根据id获取套餐
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<SetmealDto> get(@PathVariable Long id) {
        log.info("id = {}", id);
        SetmealDto setmealDto = setmealService.getByIdWithDish(id);
        return R.success(setmealDto);
    }

    @PutMapping
    public R<String> update(@RequestBody SetmealDto setmealDto) {
        log.info("setmealDto = {}", setmealDto);
        setmealService.updateWithDishes(setmealDto);
        return R.success("修改成功！");
    }

    @PostMapping("/status/{statu}")
    public R<String> changestatu(@RequestParam List<Long> ids, @PathVariable int statu) {
        log.info("ids = {}", ids);
        LambdaUpdateWrapper lambdaUpdateWrapper = new LambdaUpdateWrapper<Setmeal>()
                .in(Setmeal::getId, ids)
                .set(Setmeal::getStatus, statu);
        setmealService.update(lambdaUpdateWrapper);
        return R.success("成功停售对应的套餐！");
    }
}
