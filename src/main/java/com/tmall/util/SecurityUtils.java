package com.tmall.util;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;

import com.alibaba.fastjson.JSONObject;
import com.tmall.exception.BadRequestException;

/**
 * 获取当前登录的用户
 * Created by Mr.Li on 2019/08/22
 */
public class SecurityUtils {

    public static UserDetails getUserDetails() {
        UserDetails userDetails = null;
        try {
            userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "登录状态过期");
        }
        return userDetails;
    }

    /**
     * 获取系统用户名称
     * @return 系统用户名称
     */
    public static String getUsername(){
        return getUserDetails().getUsername();
    }

}
