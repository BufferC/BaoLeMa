package com.fc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.entity.Employee;
import com.fc.service.EmployeeService;
import com.fc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     */
    @PostMapping("login")
    public ResultVO<Employee> login(@RequestBody Employee employee, HttpSession session) {
        // 获取请求的参数密码
        String password = employee.getPassword();

        // MD5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 根据页面提交的用户名username查询数据库
        Employee emp = employeeService.getEmployeeByUsername(employee.getUsername());

        // 如果没有查询到则返回登录失败结果
        if (emp == null) {
            return ResultVO.error("登录失败");
        }

        // 密码比对，如果不一致则返回登录失败结果
        if (!emp.getPassword().equals(password)) {
            return ResultVO.error("登录失败");
        }

        // 查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (emp.getStatus() == 0) {
            return ResultVO.error("账号已禁用");
        }

        // 登录成功，将员工id存入Session并返回登录成功结果
        session.setAttribute("employee", emp.getId());

        return ResultVO.success(emp);
    }

    /**
     * 员工退出
     */
    @PostMapping("logout")
    public ResultVO<String> logout(HttpSession session) {
        // 清理Session中保存的当前登录员工的id
        session.removeAttribute("employee");

        return ResultVO.success("退出成功");
    }

    /**
     * 新增员工
     */
    @PostMapping
    public ResultVO<String> save(HttpSession session, @RequestBody Employee employee) {
        //获得当前登录用户的id
        Long empId = (Long) session.getAttribute("employee");

        if (employeeService.addEmployee(employee, empId)) {
            return ResultVO.success("新增员工成功");
        }

        return ResultVO.error("新增员工失败");
    }

    /**
     * 员工信息分页查询
     */
    @GetMapping("page")
    public ResultVO<Page<Employee>> page(int page, int pageSize, String name) {
        Page<Employee> pageInfo = employeeService.findByPage(page, pageSize, name);

        return ResultVO.success(pageInfo);
    }

    /**
     * 根据id修改员工信息
     */
    @PutMapping
    public ResultVO<String> update(HttpSession session, @RequestBody Employee employee) {
        Long empId = (Long) session.getAttribute("employee");

        employee.setUpdateUser(empId);
        if (employeeService.updateById(employee)) {
            return ResultVO.success("员工信息修改成功");
        }

        return ResultVO.success("员工信息修改失败");
    }

    /**
     * 根据id查询员工信息
     */
    @GetMapping("/{id}")
    public ResultVO<Employee> getById(@PathVariable("id") Long id) {
        System.out.println("从前端传递过来的id:" + id);

        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResultVO.success(employee);
        }
        return ResultVO.error("没有查询到对应员工信息");
    }
}
