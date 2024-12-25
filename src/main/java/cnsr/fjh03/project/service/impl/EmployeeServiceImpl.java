package cnsr.fjh03.project.service.impl;

import cnsr.fjh03.project.mapper.EmployeeMapper;
import cnsr.fjh03.project.pojo.Employee;
import cnsr.fjh03.project.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: EmployeeServiceImpl
 * @Date: 2024/12/25
 * @Time: 16:18
 * @Description:添加自定义描述
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Autowired
    HttpServletRequest Req;

    @Override
    public Employee login(Employee employee) {
        //将提交的密码进行md5加密处理
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = this.getOne(employeeLambdaQueryWrapper);

        if (emp == null || !emp.getPassword().equals(password) || emp.getStatus() == 0) {
            return null;
        }

        //登陆成功逻辑
        Req.getSession().setAttribute("employee", emp.getId());
        return emp;
    }

    @Override
    public Employee getEmpById(Long id) {
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.eq(Employee::getId, id);
        return this.getOne(employeeLambdaQueryWrapper);
    }

    @Override
    public Page page(int page, int pageSize, String name) {
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<Employee>()
                .like(StringUtils.isNotEmpty(name), Employee::getName, name)
                .orderByDesc(Employee::getUpdateTime);

        //执行查询
        return this.page(pageInfo, lambdaQueryWrapper);
    }
}
