package com.fc.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fc.common.BaseContext;
import com.fc.entity.User;
import com.fc.service.UserService;
import com.fc.util.EmailUtil;
import com.fc.util.ValidateCodeUtils;
import com.fc.vo.LoginVO;
import com.fc.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 发送手机短信验证码
     */
    @PostMapping("sendMsg")
    public ResultVO<String> sendMsg(@RequestBody User user, HttpSession session) {
        //获取手机号
        String phone = user.getPhone();

        if (StringUtils.isNotEmpty(phone)) {
            // 生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}", code);

            // 使用邮件发送获取验证码
            EmailUtil.sendMessage("曹操外卖", phone, code);

            // 需要将生成的验证码保存到Session
            session.setAttribute(phone, code);

            return ResultVO.success("手机验证码短信发送成功");
        }

        return ResultVO.error("短信发送失败");
    }

    /**
     * 移动端用户登录
     */
    @PostMapping("login")
    public ResultVO<User> login(@RequestBody LoginVO loginVO, HttpSession session) {
        log.info(loginVO.toString());

        //获取手机号
        String phone = loginVO.getPhone();

        //获取验证码
        String code = loginVO.getCode();

        //从Session中获取保存的验证码
        Object codeInSession = session.getAttribute(phone);

        //进行验证码的比对（页面提交的验证码和Session中保存的验证码比对）
        if (codeInSession != null && codeInSession.equals(code)) {
            //如果能够比对成功，说明登录成功

            User user = userService.getByPhone(phone);
            if (user == null) {
                //判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());
            return ResultVO.success(user);
        }
        return ResultVO.error("登录失败");
    }

    /**
     * 移动端用户退出
     */
    @PostMapping("logout")
    public ResultVO<String> logout(HttpSession session) {
        BaseContext.setCurrentId(null);
        session.removeAttribute("user");
        return ResultVO.success("退出登录成功");
    }
}
