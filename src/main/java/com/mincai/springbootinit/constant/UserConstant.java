package com.mincai.springbootinit.constant;

/**
 * @author limincai
 * 用户常量
 */
public class UserConstant {

    /**
     * 用户注册加密盐
     */
    public static final String USER_REGISTER_SALT = "limincai";


    /**
     * 用户登录态键
     */
    public static final String USER_LOGIN_STATE = "user_login";

    /**
     * 默认角色
     */
    public static final String USER_ROLE = "user";

    /**
     * 管理员角色
     */
    public static final String ADMIN_ROLE = "admin";

    /**
     * 被封号
     */
    public static final String BAN_ROLE = "ban";
}
