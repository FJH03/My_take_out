package cnsr.fjh03.project.controller;

import cnsr.fjh03.project.anno.Log;
import cnsr.fjh03.project.common.R;
import cnsr.fjh03.project.dto.DishDto;
import cnsr.fjh03.project.dto.SetmealDto;
import cnsr.fjh03.project.pojo.Setmeal;
import cnsr.fjh03.project.service.SetmealService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: SetmealController
 * @Date: 2025/1/12
 * @Time: 18:46
 * @Description:添加自定义描述
 */
@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page = {}, pageSize = {}", page, pageSize);
        return R.success(setmealService.page(page, pageSize, name));
    }

    /**
     * 新增套餐
     * @param setmealDto
     * @return
     */
    @Log
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
    @Log
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

    @Log
    @PutMapping
    public R<String> update(@RequestBody SetmealDto setmealDto) {
        log.info("setmealDto = {}", setmealDto);
        setmealService.updateWithDishes(setmealDto);
        return R.success("修改成功！");
    }

    @Log
    @PostMapping("/status/{statu}")
    public R<String> changestatu(@RequestParam List<Long> ids, @PathVariable int statu) {
        log.info("ids = {}", ids);
        setmealService.changestu(ids, statu);
        return R.success("成功停售对应的套餐！");
    }

    @GetMapping("/list")
    public R<List<Setmeal>> mylist(Setmeal setmeal) {
        log.info("setmeal = {}", setmeal);
        return R.success(setmealService.mylist(setmeal));
    }

    @GetMapping("/dish/{id}")
    public R<List<DishDto>> getDishBySetmealId(@PathVariable Long id) {
        log.info("id = {}", id);
        return R.success(setmealService.getDishBySetmealId(id));
    }
}
