package com.example.My_take_out.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.My_take_out.pojo.Category;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: CategoryService
 * @Date: 2023/12/8
 * @Time: 18:27
 * @Description:添加自定义描述
 */
public interface CategoryService extends IService<Category> {
    void remove(Long id);
    Page page(int page, int pageSize);
    List<Category> list(Category category);
}
