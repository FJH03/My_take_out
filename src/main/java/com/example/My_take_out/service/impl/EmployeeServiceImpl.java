package com.example.My_take_out.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.My_take_out.mapper.EmployeeMapper;
import com.example.My_take_out.pojo.Employee;
import com.example.My_take_out.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: IEmployeeServiceImpl
 * @Date: 2023/12/5
 * @Time: 15:16
 * @Description:添加自定义描述
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {}
