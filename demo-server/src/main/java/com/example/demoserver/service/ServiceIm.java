package com.example.demoserver.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class ServiceIm implements ServiceInterface {
    @SentinelResource("common")
    public String common(String str){
        return str;
    }
}
