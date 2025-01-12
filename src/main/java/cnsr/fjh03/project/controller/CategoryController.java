package cnsr.fjh03.project.controller;

import cnsr.fjh03.project.anno.Log;
import cnsr.fjh03.project.common.R;
import cnsr.fjh03.project.pojo.Category;
import cnsr.fjh03.project.service.CategoryService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: CategoryController
 * @Date: 2025/1/12
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
        Page pageInfo = categoryService.page(page, pageSize);
        return R.success(pageInfo);
    }

    @Log
    @PostMapping
    public R<String> add(@RequestBody Category category) {
        log.info("传入的category:{}", category);
        categoryService.save(category);
        return R.success("添加成功！");
    }

    @Log
    @PutMapping
    public R<String> update(@RequestBody Category category) {
        log.info("传入的category:{}", category);
        categoryService.updateById(category);
        return R.success("修改成功！");
    }

    /**
     * 根据ids删除分类
     *
     * @param ids
     * @return
     */
    @Log
    @DeleteMapping
    public R<String> delete(Long ids) {
        log.info("传进来的ids:{}", ids);
        categoryService.remove(ids);
        return R.success("删除成功！");
    }

    @GetMapping("/list")
    public R<List<Category>> list(Category category) {
        log.info("type:{}", category);
        List categoryList = categoryService.list(category);
        return R.success(categoryList);
    }
}
