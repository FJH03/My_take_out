package cnsr.fjh03.project.controller;

import cnsr.fjh03.project.anno.Log;
import cnsr.fjh03.project.common.R;
import cnsr.fjh03.project.pojo.Employee;
import cnsr.fjh03.project.service.EmployeeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: EmployeeController
 * @Date: 2024/12/25
 * @Time: 16:15
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
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee) {
        Employee emp = employeeService.login(employee);
        return R.success(emp);
    }

    /**
     * 员工退出
     * @param req
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest req) {
        //清理Session中保存的当前登录员工的id
        req.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    @Log
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
        return R.success(employeeService.page(page, pageSize, name));
    }

    /**
     * 根据id修改员工信息
     * @param employee
     * @return
     */
    @Log
    @PutMapping
    public R<String> update(@RequestBody Employee employee) {
        log.info("需要修改的员工实体数据{}", employee);
        employeeService.updateById(employee);
        return R.success("修改成功！");
    }

    @GetMapping("/{id}")
    public R<Employee> getEmpById(@PathVariable long id) {
        log.info("id = {}", id);
        return R.success(employeeService.getEmpById(id));
    }
}
