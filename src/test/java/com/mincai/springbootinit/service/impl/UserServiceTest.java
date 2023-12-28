package com.mincai.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mincai.springbootinit.model.domain.User;
import com.mincai.springbootinit.model.vo.user.UserVO;
import com.mincai.springbootinit.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author limincai
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    UserService userService;

    @Test
    void userRegister() {
        String userAccount = "limincai";
        String userPassword = "limincai";
        String confirmedPassword = "limincai";
        long l = userService.userRegister(userAccount, userPassword, confirmedPassword);
        Assertions.assertTrue(l > 0);
        System.out.println(l);
    }

    @Test
    void userVO() {
        QueryWrapper queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("userAccount", "limincai");
        User user = userService.getOne(queryWrapper);
        UserVO vo = UserVO.doToVO(user);
        System.out.println(vo);
    }
}