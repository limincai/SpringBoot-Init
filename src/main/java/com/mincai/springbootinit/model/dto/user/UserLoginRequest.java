package com.mincai.springbootinit.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author limincai
 * 用户登录封装类
 */
@Data
public class UserLoginRequest implements Serializable {

    String userAccount;

    String userPassword;

    private static final long serialVersionUID = 1L;
}
