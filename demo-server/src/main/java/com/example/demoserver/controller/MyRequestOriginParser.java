package com.example.demoserver.controller;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
/*@Component
public class MyRequestOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        //从请求头中获取参数
        //如果不提供参数则抛出异常
        String origin = httpServletRequest.getHeader("origin");
        if(StringUtils.isEmpty(origin)){
            throw new IllegalArgumentException("origin is null");
        }
        return origin;
    }
}*/
