package com.mincai.springbootinit.controller;

import com.mincai.springbootinit.enums.ErrorCode;
import com.mincai.springbootinit.exception.BusinessException;
import com.mincai.springbootinit.model.domain.User;
import com.mincai.springbootinit.service.UserService;
import com.mincai.springbootinit.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @PostMapping("/register")
    public Response<Long> userRegister(String userAccount, String userPassword) {
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(userPassword);
        if ("limincai112313".equals(userAccount)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return Response.ok(user.getId());
    }
}
