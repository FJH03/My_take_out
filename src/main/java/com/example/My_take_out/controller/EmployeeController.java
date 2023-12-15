package com.example.My_take_out.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.My_take_out.common.R;
import com.example.My_take_out.pojo.Employee;
import com.example.My_take_out.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: EmployeeController
 * @Date: 2023/12/5
 * @Time: 15:13
 * @Description:添加自定义描述
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * @param Req
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest Req,  @RequestBody Employee employee) {
        //将提交的密码进行md5加密处理
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(employeeLambdaQueryWrapper);

        if (emp == null || !emp.getPassword().equals(password)) {
            return R.error("登录失败，用户名或密码错误！");
        }

        //查看员工状态
        if (emp.getStatus() == 0) {
            return R.error("该用户位于黑名单！");
        }

        //登陆成功逻辑
        Req.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    /**
     * 员工退出
     * @param req
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest req){
        //清理Session中保存的当前登录员工的id
        req.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    @PostMapping
    public R<String> save(@RequestBody Employee employee) {
        log.info("新增员工，员工信息：{}", employee);
        String password = DigestUtils.md5DigestAsHex("123456".getBytes());
        employee.setPassword(password);

        employeeService.save(employee);
        return R.success("新增员工成功！");
    }

    /**
     * 员工信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page = {}, pageSize = {}, name = {}", page, pageSize, name);
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<Employee>()
                .like(StringUtils.isNotEmpty(name), Employee::getName, name)
                .orderByDesc(Employee::getUpdateTime);

        //执行查询
        employeeService.page(pageInfo, lambdaQueryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 根据id修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Employee employee) {
        log.info("需要修改的员工实体数据{}", employee);
        employeeService.updateById(employee);
        return R.success("修改成功！");
    }

    @GetMapping("/{id}")
    public R<Employee> getEmpById(@PathVariable long id) {
        log.info("id ={}", id);
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.eq(Employee::getId, id);
        Employee employee = employeeService.getOne(employeeLambdaQueryWrapper);
        return R.success(employee);
    }
}
