package com.example.demoserver.controller;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.UrlCleaner;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;


import java.util.Arrays;

@Component
public class MyUrlCleaner implements UrlCleaner {

    @Override
    public String clean(String s) {
        System.out.println(s+"==============");
        String[] split = s.split("/");
        return Arrays.stream(split).map(s2 ->{
            if(NumberUtils.isNumber(s2)){
                return "{number}";
            }else{
                return s2;
            }
        }).reduce((a,b) -> (a+"/"+b)).orElse("");

    }
}
