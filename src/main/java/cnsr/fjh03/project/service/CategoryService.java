package cnsr.fjh03.project.service;

import cnsr.fjh03.project.pojo.Category;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: CategoryService
 * @Date: 2025/1/12
 * @Time: 13:11
 * @Description:添加自定义描述
 */
public interface CategoryService extends IService<Category> {
    void remove(Long id);
    Page page(int page, int pageSize);
    List<Category> list(Category category);
}
