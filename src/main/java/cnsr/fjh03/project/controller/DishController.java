package cnsr.fjh03.project.controller;

import cnsr.fjh03.project.anno.Log;
import cnsr.fjh03.project.common.R;
import cnsr.fjh03.project.dto.DishDto;
import cnsr.fjh03.project.pojo.Dish;
import cnsr.fjh03.project.service.DishService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: DishController
 * @Date: 2025/1/12
 * @Time: 18:40
 * @Description:添加自定义描述
 */
@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page = {}, pageSize = {}", page, pageSize);
        Page dishDtoPage = dishService.page(page, pageSize, name);
        return R.success(dishDtoPage);
    }

    /**
     * 新增菜品
     * @param dishDto
     * @return
     */
    @Log
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
    @Log
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto) {
        log.info("dishDto = {}", dishDto);
        dishService.updateWithFlavors(dishDto);
        return R.success("修改成功！");
    }

    @Log
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("ids = {}", ids);
        dishService.removeWithFlavors(ids);
        return R.success("删除成功!");
    }

    @GetMapping("/list")
    public R<List<DishDto>> getDishlist(Dish dish) {
        log.info("dishDto = {}", dish);
        return R.success(dishService.getDishlist(dish));
    }

    /**
     * 批量改变商品的销售状态
     * @param ids
     * @param statu
     */
    @Log
    @PostMapping("/status/{statu}")
    public R<String> changestatu(@RequestParam List<Long> ids,@PathVariable int statu) {
        log.info("ids = {}, statu = {}", ids, statu);
        dishService.changestatu(ids, statu);
        return R.success("成功改变状态！");
    }
}
