package com.fc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.entity.Employee;

public interface EmployeeService {
    Employee getEmployeeByUsername(String username);

    boolean addEmployee(Employee employee, Long createUserId);

    Page<Employee> findByPage(int page, int pageSize, String name);

    boolean updateById(Employee employee);

    Employee getEmployeeById(Long id);
}
