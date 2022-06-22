package com.fc.common;

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 */
public class BaseContext {
    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置值
     */
    public static void setCurrentId(Long id){
        THREAD_LOCAL.set(id);
    }

    /**
     * 获取值
     */
    public static Long getCurrentId(){
        return THREAD_LOCAL.get();
    }
}