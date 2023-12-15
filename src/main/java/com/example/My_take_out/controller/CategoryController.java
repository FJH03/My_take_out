package com.example.My_take_out.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.My_take_out.common.R;
import com.example.My_take_out.pojo.Category;
import com.example.My_take_out.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: CategoryController
 * @Date: 2023/12/8
 * @Time: 18:26
 * @Description:添加自定义描述
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        log.info("page = {}, pageSize = {}", page, pageSize);
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<Category>()
                .orderByDesc(Category::getUpdateTime);

        categoryService.page(pageInfo, lambdaQueryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping
    public R<String> add(@RequestBody Category category) {
        log.info("传入的category:{}", category);
        categoryService.save(category);
        return R.success("添加成功！");
    }

    @PutMapping
    public R<String> update(@RequestBody Category category) {
    log.info("传入的category:{}", category);
    categoryService.updateById(category);
        return R.success("修改成功！");
    }

    /**
     * 根据ids删除分类
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long ids) {
        log.info("传进来的ids:{}", ids);

        categoryService.remove(ids);
        return R.success("删除成功！");
    }

    @GetMapping("/list")
    public R<List<Category>> list(Category category) {
        log.info("type:{}",category);
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<Category>()
                .eq(Category::getType, category.getType())
                .orderByAsc(Category::getSort)
                .orderByDesc(Category::getUpdateTime);
        List categoryList = categoryService.list(lambdaQueryWrapper);
        return R.success(categoryList);
    }
}
