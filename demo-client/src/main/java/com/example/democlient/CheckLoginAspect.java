package com.example.democlient;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class CheckLoginAspect {

/*    @Around("@annotation(checkLogin)")
    public Object check(ProceedingJoinPoint point,CheckLogin checkLogin){
        System.out.println("注解的值:" + checkLogin.value());*/
     @Around(value = "@annotation(com.example.democlient.CheckLogin)")
     public Object check(ProceedingJoinPoint point) throws Throwable {
        Object target = point.getTarget().getClass().getName();
        System.out.println("调用者+"+target);
        //通过joinPoint.getArgs()获取Args参数
        Object[] args = point.getArgs();//2.传参  
        System.out.println("2.传参:----"+args[0]);

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader("x-token");

        if(false){
            throw new RuntimeException("返回自定义异常");
        }

        return point.proceed();

     }
}
