package com.fc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.dao.EmployeeDao;
import com.fc.entity.Employee;
import com.fc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 使用username查询数据库
     */
    @Override
    public Employee getEmployeeByUsername(String username) {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, username);
        return employeeDao.selectOne(queryWrapper);
    }

    @Override
    public boolean addEmployee(Employee employee, Long createUserId) {
        // 设置初始密码123456，需要进行md5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        return employeeDao.insert(employee) > 0;
    }

    @Override
    public Page<Employee> findByPage(int page, int pageSize, String name) {
        //构造分页构造器
        Page<Employee> pageInfo = new Page<>(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行查询
        employeeDao.selectPage(pageInfo, queryWrapper);

        return pageInfo;
    }

    @Override
    public boolean updateById(Employee employee) {
        return employeeDao.updateById(employee) > 0;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeDao.selectById(id);
    }
}
