package com.mincai.springbootinit.model.vo.user;

import cn.hutool.core.bean.BeanUtil;
import com.mincai.springbootinit.model.domain.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @author limincai
 * 用户展示层对象
 */
@Data
public class UserVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String userAccount;

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

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    public static UserVO doToVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        return userVO;
    }

    public User toUserDO() {
        User user = new User();
        BeanUtil.copyProperties(this, user);
        return user;
    }


    private static final long serialVersionUID = 1L;
}
