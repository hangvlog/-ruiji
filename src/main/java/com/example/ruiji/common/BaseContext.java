package com.example.ruiji.common;

/**
 * Created by Enzo Cotter on 2023/4/18.
 */

/**
 * 基于ThreadLocal封装的工具类，用于保存和获取用户登录的id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal  = new ThreadLocal<>();
    public static void setCurrentId(Long currentId){
        threadLocal.set(currentId);
    }
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
