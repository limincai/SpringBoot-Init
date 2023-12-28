package com.mincai.springbootinit.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author limincai
 * 用户注册请求类
 */
@Data
public class UserRegisterRequest implements Serializable {

    String userAccount;

    String userPassword;

    String confirmedPassword;

    private static final long serialVersionUID = 1L;
}
