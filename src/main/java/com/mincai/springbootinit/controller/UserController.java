package com.mincai.springbootinit.controller;

import com.mincai.springbootinit.annotation.NeedUserRole;
import com.mincai.springbootinit.constant.UserConstant;
import com.mincai.springbootinit.enums.ErrorCode;
import com.mincai.springbootinit.exception.BusinessException;
import com.mincai.springbootinit.model.dto.user.UserLoginRequest;
import com.mincai.springbootinit.model.dto.user.UserRegisterRequest;
import com.mincai.springbootinit.model.dto.user.UserUpdateRequest;
import com.mincai.springbootinit.model.vo.user.UserVO;
import com.mincai.springbootinit.service.UserService;
import com.mincai.springbootinit.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author limincai
 * 用户接口
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    UserService userService;

    /**
     * 用户注册接口
     *
     * @param userRegisterRequest 用户注册封装类
     * @return 成功注册后的 id
     */
    @PostMapping("/register")
    public Response<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String confirmedPassword = userRegisterRequest.getConfirmedPassword();
        // 不能为空
        if (StringUtils.isAnyBlank(userAccount, userPassword, confirmedPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号、密码、确认密码不能为空");
        }
        return Response.ok(userService.userRegister(userAccount, userPassword, confirmedPassword));
    }

    /**
     * 用户登录接口
     *
     * @param userLoginRequest 用户登录封装类
     * @return 返回登录成功后的用户展示层对象
     */
    @PostMapping("/login")
    public Response<UserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号与密码不能为空");
        }
        return Response.ok(userService.userLogin(userAccount, userPassword, request));
    }

    @GetMapping("/logout")
    @NeedUserRole(UserConstant.USER_ROLE)
    public Response<Boolean> userLogout(HttpServletRequest request) {
        return Response.ok(userService.userLogout(request));
    }

    /**
     * 获取当前登录用户
     */
    @GetMapping("/current")
    @NeedUserRole(UserConstant.USER_ROLE)
    public Response<UserVO> getCurrentUser(HttpServletRequest request) {
        return Response.ok(userService.getCurrentUserVO(request));
    }

    /**
     * 获取所有用户展示层对象
     */
    @GetMapping("/list/vo")
    @NeedUserRole(UserConstant.ADMIN_ROLE)
    public Response<List<UserVO>> listUserVO() {
        return Response.ok(userService.listUserVO());
    }

    /**
     * 用户更新
     */
    @GetMapping("/update")
    @NeedUserRole(UserConstant.USER_ROLE)
    public Response<UserVO> updateUser(UserUpdateRequest userUpdateRequest, HttpServletRequest request) {
        if (userUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return Response.ok(userService.updateUser(userUpdateRequest, request));
    }
}
