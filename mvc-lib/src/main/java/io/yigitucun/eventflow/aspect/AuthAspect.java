package io.yigitucun.eventflow.aspect;

import io.yigitucun.eventflow.exception.GlobalException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.yigitucun.eventflow.security.abstracts.RequireUser;
@Aspect
@Component
public class AuthAspect {


    @Before("@annotation(requireUser)")
    public void checkUser(JoinPoint joinPoint,RequireUser requireUser){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes==null) return;
        HttpServletRequest request = attributes.getRequest();
        String userId = request.getHeader("X-User-Id");
        System.out.println(userId);
        if (userId==null || userId.trim().isEmpty()){
            throw new GlobalException("Not Authorized", HttpStatus.UNAUTHORIZED);
        }
    }
}
