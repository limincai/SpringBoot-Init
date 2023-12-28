package com.mincai.springbootinit.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mincai.springbootinit.constant.UserConstant;
import com.mincai.springbootinit.enums.ErrorCode;
import com.mincai.springbootinit.exception.BusinessException;
import com.mincai.springbootinit.mapper.UserMapper;
import com.mincai.springbootinit.model.domain.User;
import com.mincai.springbootinit.model.dto.user.UserUpdateRequest;
import com.mincai.springbootinit.model.vo.user.UserVO;
import com.mincai.springbootinit.service.UserService;
import com.mincai.springbootinit.utils.RegUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author limincai
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public long userRegister(String userAccount, String userPassword, String confirmedPassword) {
        // 1. 参数校验
        // 账号不能包含特殊字符
        if (RegUtil.hasSpecialChar(userAccount)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号不能包含特殊字符");
        }
        // 密码与确认密码不一致
        if (!userPassword.equals(confirmedPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不能与确认密码一致");
        }
        // 6 < 账号长度 < 16
        if (userAccount.length() < 4 || userAccount.length() > 16) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号必须大于6个字符，少于16个字符");
        }
        // 8 < 用户密码 < 16
        if (userPassword.length() < 8 || userPassword.length() > 16) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码必须大于8个字符，少于16个字符");
        }
        // 2. 账号不能重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        long count = this.count(userQueryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号已存在");
        }
        // 3. 用户密码加密
        String encryptedPassword = DigestUtils.md5DigestAsHex((UserConstant.USER_REGISTER_SALT + userPassword).getBytes());
        // 4. 插入数据到数据库
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptedPassword);
        boolean result = this.save(user);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return user.getId();
    }

    @Override
    public UserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 参数校验
        // 账号不能包含特殊字符
        if (RegUtil.hasSpecialChar(userAccount)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号不能包含特殊字符");
        }
        // 6 < 账号长度 < 16
        if (userAccount.length() < 4 || userAccount.length() > 16) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号必须大于6个字符，少于16个字符");
        }
        // 8 < 用户密码 < 16
        if (userPassword.length() < 8 || userPassword.length() > 16) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码必须大于8个字符，少于16个字符");
        }
        // 2. 用户密码加密后查询数据库
        String encryptedPassword = DigestUtils.md5DigestAsHex((UserConstant.USER_REGISTER_SALT + userPassword).getBytes());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        userQueryWrapper.eq("userPassword", encryptedPassword);
        User user = this.getOne(userQueryWrapper);
        // 用户不存在或账号或密码错误
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或账号或密码错误");
        }
        // 3. 记录用户的登陆态
        UserVO userVO = UserVO.doToVO(user);
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, userVO);
        return userVO;
    }

    @Override
    public Boolean userLogout(HttpServletRequest request) {
        // 移除登录态
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
        return Boolean.TRUE;
    }

    @Override
    public List<UserVO> listUserVO() {
        List<User> userList = this.list();
        return userList.stream().map(UserVO::doToVO).collect(Collectors.toList());
    }

    @Override
    public UserVO userUpdate(UserUpdateRequest userUpdateRequest, HttpServletRequest request) {
        // 1. 获取当前用户
        UserVO currentUserVO = this.getCurrentUserVO(request);
        // 2. 将需要更新用户字段拷贝到当前用户
        BeanUtil.copyProperties(userUpdateRequest, currentUserVO);
        // 3. 根据 id 更新数据库中的用户
        User currentUser = currentUserVO.toUserDO();
        boolean result = this.updateById(currentUser);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        // 4. 更新用户登录态
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, currentUserVO);
        return currentUserVO;
    }

    @Override
    public UserVO getCurrentUserVO(HttpServletRequest request) {
        // 1. 先判断是否已登录
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        if (userObj == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return (UserVO) userObj;
    }
}




