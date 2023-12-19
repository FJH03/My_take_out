package com.example.My_take_out.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.My_take_out.pojo.Employee;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: IEmployeeService
 * @Date: 2023/12/5
 * @Time: 15:16
 * @Description:添加自定义描述
 */
public interface EmployeeService extends IService<Employee> {
    Employee login(Employee employee);

    Employee getEmpById(Long id);
    Page page(int page, int pageSize, String name);
}
