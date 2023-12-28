package com.mincai.springbootinit.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mincai.springbootinit.model.domain.User;
import com.mincai.springbootinit.model.dto.user.UserUpdateRequest;
import com.mincai.springbootinit.model.vo.user.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author limincai
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount       注册账号
     * @param userPassword      注册密码
     * @param confirmedPassword 确认密码
     * @return 用户成功注册后的 id
     */
    long userRegister(String userAccount, String userPassword, String confirmedPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @return 返回登录成功后的用户展示层对象
     */
    UserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户注销
     *
     * @return 是否成功注销
     */
    Boolean userLogout(HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @return 返回当前登录用户
     */
    UserVO getCurrentUserVO(HttpServletRequest request);

    /**
     * 获取所有用户展示层对象
     */
    List<UserVO> listUserVO();

    /**
     * 用户更新
     *
     * @param userUpdateRequest 用户更新请求封装类
     * @return 返回更新成功后的用户展示层对象
     */
    UserVO updateUser(UserUpdateRequest userUpdateRequest, HttpServletRequest request);
}
