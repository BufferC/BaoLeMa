package com.fc.common;

import com.fc.exception.CustomException;
import com.fc.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 异常处理方法
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResultVO<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        if(ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return ResultVO.error(msg);
        }

        return ResultVO.error("未知错误");
    }

    /**
     * 异常处理方法
     */
    @ExceptionHandler(CustomException.class)
    public ResultVO<String> customExceptionHandler(CustomException ex){
        return ResultVO.error(ex.getMessage());
    }
}
