package cnsr.fjh03.project.service.impl;

import cnsr.fjh03.project.common.CustomException;
import cnsr.fjh03.project.common.MyfileMethods;
import cnsr.fjh03.project.dto.DishDto;
import cnsr.fjh03.project.mapper.DishMapper;
import cnsr.fjh03.project.pojo.Category;
import cnsr.fjh03.project.pojo.Dish;
import cnsr.fjh03.project.pojo.DishFlavor;
import cnsr.fjh03.project.pojo.SetmealDish;
import cnsr.fjh03.project.service.CategoryService;
import cnsr.fjh03.project.service.DishFlavorService;
import cnsr.fjh03.project.service.DishService;
import cnsr.fjh03.project.service.SetmealDishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: DishServiceImpl
 * @Date: 2025/1/12
 * @Time: 11:07
 * @Description:添加自定义描述
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Value("${my_takeout.path}")
    private String Path;

    @Autowired
    @Lazy//循环依赖问题
    private CategoryService categoryService;
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Page page(int page, int pageSize, String name) {
        Page pageInfo = new Page(page, pageSize);
        Page dishDtoPage = new Page();

        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<Dish>()
                .like(name != null, Dish::getName, name)
                .orderByDesc(Dish::getUpdateTime);
        this.page(pageInfo, lambdaQueryWrapper);

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
        return dishDtoPage;
    }

    @Override
    public List<DishDto> getDishlist(Dish dish) {
        List<DishDto> dishDtos;
        String key = "dish_" + dish.getCategoryId() + "_" + dish.getStatus();
        //先从Redis中获取缓存数据
        dishDtos = (List<DishDto>) redisTemplate.opsForValue().get(key);

        //如果存在，直接返回，无需查询数据库
        if (dishDtos != null) {
            return dishDtos;
        }

        //如果不存在，将从数据库查询到的菜品数据缓存到Redis中

        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<Dish>()
                .eq(Dish::getStatus, 1)
                .like(dish.getName() != null, Dish::getName, dish.getName())
                .eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId())
                .orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = this.list(lambdaQueryWrapper);
        dishDtos = list.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long dishId = item.getId();

            LambdaQueryWrapper querywrapper = new LambdaQueryWrapper<DishFlavor>()
                    .eq(DishFlavor::getDishId, dishId);

            List<DishFlavor> dishFlavorlist = dishFlavorService.list(querywrapper);
            dishDto.setFlavors(dishFlavorlist);
            return dishDto;
        }).collect(Collectors.toList());

        redisTemplate.opsForValue().set(key, dishDtos, 1, TimeUnit.HOURS);

        return dishDtos;
    }

    /**
     * 新增菜品，同时保存对应的口味数据(操作两张表)
     *
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

        //清理Redis缓存
        String key = "dish_" + dishDto.getCategoryId() + "_1";
        redisTemplate.delete(key);
    }

    /**
     * 根据id查询菜品信息和对应的口味信息
     *
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
     *
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

        //清理Redis缓存
        String key = "dish_" + dishDto.getCategoryId() + "_1";
        redisTemplate.delete(key);
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

    /**
     * 批量改变商品的销售状态
     *
     * @param ids
     * @param statu
     */
    @Override
    public void changestatu(List<Long> ids, int statu) {
        LambdaUpdateWrapper lambdaUpdateWrapper = new LambdaUpdateWrapper<Dish>()
                .in(Dish::getId, ids)
                .set(Dish::getStatus, statu);
        this.update(lambdaUpdateWrapper);
        redisTemplate.delete("dish_**");
    }
}
