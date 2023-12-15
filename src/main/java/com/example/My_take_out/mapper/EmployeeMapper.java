package com.example.My_take_out.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.My_take_out.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Created with Intellij IDEA Ultimate 2022.02.03 正式旗舰版
 * @Author: 2113042621-冯佳和
 * @ClassName: IEmployeeMapper
 * @Date: 2023/12/5
 * @Time: 15:15
 * @Description:添加自定义描述
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {}
