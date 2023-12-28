package com.mincai.springbootinit.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author limincai
 * 用户修改请求对象
 */
@Data
public class UserUpdateRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;


    private static final long serialVersionUID = 1L;
}
