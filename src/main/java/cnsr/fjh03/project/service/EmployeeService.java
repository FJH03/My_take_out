package cnsr.fjh03.project.service;

import cnsr.fjh03.project.pojo.Employee;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: EmployeeService
 * @Date: 2024/12/25
 * @Time: 16:17
 * @Description:添加自定义描述
 */
public interface EmployeeService extends IService<Employee> {
    Employee login(Employee employee);

    Employee getEmpById(Long id);
    Page page(int page, int pageSize, String name);
}
