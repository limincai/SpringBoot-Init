package com.mincai.springbootinit.aop;

import com.mincai.springbootinit.annotation.NeedUserRole;
import com.mincai.springbootinit.constant.UserConstant;
import com.mincai.springbootinit.enums.ErrorCode;
import com.mincai.springbootinit.enums.UserRoleEnum;
import com.mincai.springbootinit.exception.BusinessException;
import com.mincai.springbootinit.model.vo.user.UserVO;
import com.mincai.springbootinit.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author limincai
 * 用户相关 AOP 类
 */
@Aspect
@Component
public class UserAop {

    @Resource
    UserService userService;

    /**
     * 执行拦截
     */
    @Around("@annotation(needUserRole)")
    public Object doInterceptor(ProceedingJoinPoint point, NeedUserRole needUserRole) throws Throwable {
        String needRole = needUserRole.value();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 1. 获取当前用户
        UserVO currentUser = userService.getCurrentUserVO(request);
        String userRole = currentUser.getUserRole();
        // 2. 判断当前用户角色是否拥有权限
        if (StringUtils.isBlank(userRole)) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR);
        }
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(userRole);
        if (userRoleEnum == null) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR);
        }
        // 如果被 ban 直接拒绝
        if (userRole.equals(UserConstant.BAN_ROLE)) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR);
        }
        // 如果当前用户权限不是管理员或者不与所需权限一致
        if (!userRole.equals(UserConstant.ADMIN_ROLE) && !userRole.equals(needRole)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return point.proceed();
    }
}
