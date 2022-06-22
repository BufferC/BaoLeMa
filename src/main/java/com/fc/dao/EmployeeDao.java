package com.fc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends BaseMapper<Employee> {
}
