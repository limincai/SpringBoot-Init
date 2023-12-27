package com.mincai.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mincai.springbootinit.mapper.UserMapper;
import com.mincai.springbootinit.model.domain.User;
import com.mincai.springbootinit.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author limincai
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}




