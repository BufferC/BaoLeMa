package com.fc.vo;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回给前端的vo对象
 * @param <T> 泛型
 */
@Data
public class ResultVO<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map<String, Object> map = new HashMap<>(); //动态数据

    public static <T> ResultVO<T> success(T object) {
        ResultVO<T> r = new ResultVO<>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> ResultVO<T> error(String msg) {
        ResultVO<T> r = new ResultVO<>();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public ResultVO<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
